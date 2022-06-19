package ru.jandroid.mysql_cities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.*
import org.json.JSONObject
import ru.jandroid.mysql_cities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CountryAdapter.Listener {
    lateinit var binding: ActivityMainBinding
    private val adapter = CountryAdapter(this)
    private var countrytList = ArrayList<Country>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonUpdate.setOnClickListener { loadCountriesByRetrofit() }

        recyclerInit()
        loadCountriesByRetrofit()
    }

    override fun oClick(country: Country, index: Int) {
        showToast(country.title_ru)
    }

    override fun oClickMore(country: Country) {
        showToast(country.country_id.toString())
    }

    // RECYCLER ADAPTER
    private  fun recyclerInit(){
        binding.apply {
            recyclerViewCountries.layoutManager = GridLayoutManager(this@MainActivity,1)
            recyclerViewCountries.adapter=adapter
            //rcView.setHasFixedSize(true) //фиксируем размер Item
        }
    }

    // JSON с помощью Retrofit и Coroutines
    private fun loadCountriesByRetrofit() {
        countrytList.clear()

        /*GlobalScope.launch(Dispatchers.IO) {
            val response = restCountriesApi.getCountryByName()

            withContext(Dispatchers.Main){
                // Основной поток для интерфейса
                countrytList.addAll(response.countries)
                countrytList.sortBy { it.country_id }
                adapter.fillCountry(countrytList)
            }
        }*/

        lifecycleScope.launch {
            val response = restCountriesApi.getAllCountry()

            // Основной поток для интерфейса
            countrytList.addAll(response.countries)
            countrytList.sortBy { it.country_id }
            adapter.fillCountry(countrytList)

                //Log.d("MyLog", response.countries.get(0).title_ru)

        }
    }

    // JSON с помощью Volley
    private fun loadCountriesByVolley() {
        showToast("Загрузка")
        val stringRequest = StringRequest(Request.Method.GET, (EndPoints.URL_ROOT+EndPoints.URL_GET_COUNTRIES),
            { response ->

                val obj = JSONObject(response)
                val objArray = obj.getJSONArray("countries")

                countrytList.clear()
                for (i in 0 until objArray.length()) {
                    val objectCountries = objArray.getJSONObject(i)
                    val country = Country(
                        objectCountries.getInt("country_id"),
                        objectCountries.getString("title_ru"),
                    )
                    countrytList.add(country)
                }
                countrytList.sortBy { it.country_id }
                adapter.fillCountry(countrytList)

            },
            {
                Log.d("MyLog", "Ошибка Volly: ${it.toString()}")
            })

        // Создаём очередь
        val queue = Volley.newRequestQueue(this)
        // Добавляем в очередь
        queue.add(stringRequest)
    }
}
