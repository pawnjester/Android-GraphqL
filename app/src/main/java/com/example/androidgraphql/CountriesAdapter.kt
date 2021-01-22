package com.example.androidgraphql

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidgraphql.databinding.ItemCountryBinding
import javax.inject.Inject

class CountriesAdapter @Inject constructor() : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    private val listOfCountries = mutableListOf<CountryListQuery.Country>()

    fun setCountriesData(items: List<CountryListQuery.Country>) {
        listOfCountries.clear()
        listOfCountries.addAll(items)
        notifyDataSetChanged()
    }

    inner class CountriesViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CountryListQuery.Country) {
            binding.countryName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val binding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfCountries.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(listOfCountries[position])
    }
}