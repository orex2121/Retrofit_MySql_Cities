package ru.jandroid.mysql_cities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.jandroid.mysql_cities.databinding.CountryItemBinding

class CountryAdapter(val listener:Listener):RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    var countryList=ArrayList<Country>()

    //ViewHolder Class
    class CountryHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding= CountryItemBinding.bind(item)
        fun bind(country:Country, listener:Listener) = with(binding){
            tvIdCountry.text=country.country_id.toString()
            tvTitleCountry.text=country.title_ru
            itemView.setOnClickListener {
                listener.oClick(country, position)
            }
            buttonMore.setOnClickListener {
                listener.oClickMore(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryHolder(view)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(countryList[position], listener)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    // ----------------------------- ОБРАБОТКА НАЖАТИЙ
/*    //Добавляем элемент поштучно
    fun addCountry(country:Country){
        countryList.add(country)
        notifyDataSetChanged()
    }

    //Удаляем элемент
    fun deleteCountry(country:Country){
        countryList.remove(country)
        notifyDataSetChanged()
    }*/

    //Обновляет элемент
/*    fun updateCountry(country:Country, index : Int){
        countryList[index].country_id = country.country_id
        countryList[index].title_ru = country.title_ru
        notifyDataSetChanged()
    }*/

    //наполняем элементами при загрузке
    fun fillCountry(countryListFromMain:ArrayList<Country>){
        countryList = countryListFromMain
        notifyDataSetChanged()
    }

    //-------------- ИНТЕРФЕЙС ДЛЯ СЛУШАТЕЛЕЙ
    interface Listener{
        fun oClick(country:Country, index: Int)
        fun oClickMore(country:Country)
    }
}