package com.example.androidmaster.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivityDetailSuperHeroBinding
import com.example.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

//Para persistencia de datos utilizaremos DataStore, tendremos que añadir su dependencia

//Crearemos una funcion de extension
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings") //Nombre de la base de datos
//IMPORTANTE QUE EL PREFERENCE A IMPORTAR SEA ESTE: import androidx.datastore.preferences.core.Preferences

class SettingsActivity : AppCompatActivity() {

    //Creamos el binding
    private lateinit var binding: ActivitySettingsBinding

    //Vamos a crear unas constantes para las claves de la base de datos
    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_DARK_MODE = "key_dark_mode"
        const val KEY_VIBRATION = "key_vibration"
        const val KEY_BLUETOOTH = "key_bluetooth"
    }

    //Crearemos una variable para controlar el flow
    private var firstTime:Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para recuperar la informacion almacenada haremos uso de los flows, los cuales nos
        //proporcionan una comunicacion constante con los valores de manera que cuando se
        //modifique algun dato podremos saberlo en el instante

        //Al estar constantemente moviendo informacion es necesario realizarlo dentro de una corrutina
        CoroutineScope(Dispatchers.IO).launch{
            //La informacion del flow la "collecteamos", lo que conseguimos con el boolean firstTime, es
            //que los valores iniciales se establezcan solo una vez cuando accedemos a la actividad, es importante para
            //evitar actualizaciones repetidas de la interfaz de usuario
            getSettings().filter { firstTime }.collect{settingsModel ->
                if(settingsModel!=null){
                    //Aqui lo que haremos sera actualizar los valores cada vez que accedamos a esta activity
                    //Pero es IMPORTANTE QUE ESTO SE REALICE EN EL HILO PRINCIPAL
                    runOnUiThread{
                        binding.rsVolumen.setValues(settingsModel.volume.toFloat())
                        binding.swDarkMode.isChecked = settingsModel.darkMode
                        binding.swBluetooth.isChecked = settingsModel.bluetooth
                        binding.swVibrationMode.isChecked = settingsModel.vibration
                        firstTime = !firstTime
                    }

                }
            }
        }

        initUI()

    }

    private fun initUI() {

        //Llamamos al rangeSlider
        binding.rsVolumen.addOnChangeListener { _, value, _ ->
            Log.i("CarlosVL", "El valor es $value")
            //Debemos iniciar una corrutina para llamar al metodo saveVolumen
            CoroutineScope(Dispatchers.IO).launch {
                //el rangeSlider devuelve un float, de manera que tenemos que hacer uso de toInt()
                saveVolumen(value.toInt())
            }
        }

        //Llamamos a los switch
        //El metodo setOnCheckedChangeListener nos da dos parametros, el boton, que no nos interesa, y el boolean que nos dice si es true o false
        binding.swDarkMode.setOnCheckedChangeListener { _, isChecked ->

            //Llamamos a los metodos de activar o desactivar el modo oscuro dependiendo de si "isCheked" es true o false
            if(isChecked){
                enableDarkMode()
            }else{
                disableDarkMode()
            }

            //Iniciamos la corrutina
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, isChecked)
            }
        }

        //Repetimos para el resto de switch
        binding.swBluetooth.setOnCheckedChangeListener { _, isChecked ->
            //Iniciamos la corrutina
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH, isChecked)
            }
        }

        binding.swVibrationMode.setOnCheckedChangeListener { _, isChecked ->
            //Iniciamos la corrutina
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATION, isChecked)
            }
        }

    }

    //Creamos una funcion para guardar datos en la dataStore

    //Guardar el volumen
    private suspend fun saveVolumen(value: Int) {

        //Es necesario que .edit se llame dentro de una corrutina, de manera que en la funcion indicamos suspend
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    //Funcion para guardar los checks de los switch
    //Recibe un boolean porque los switch devuelven verdadero o falso, tambien recibe la key porque hay mas de un switch
    private suspend fun saveOptions(key: String, value: Boolean) {

        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }

    }

    //Funcion para recuperar la info de los ajustes
    private fun getSettings(): Flow<SettingsModel> {

        //Para devolver todos los valores que tenemos (3 booleans y 1 int)
        //encapsularemos la informacion en un objeto data Class, de esta forma
        //el metodo getSettings devolvera un flow de esa data class

        return dataStore.data.map { preferences ->
            SettingsModel(
                //Con el operador elvis ?: indicaremos el valor que devolvera en caso de que sea nulo
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: true
            )

            //de esta forma la primera vez que entremos, si no hemos guardado nada, estaran guardados estos
            //valores por defecto

        }

    }

    //Crearemos dos funciones para activar y desactivar el modo oscuro
    private fun enableDarkMode(){
        //Importar el del DELEGATE
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }


}