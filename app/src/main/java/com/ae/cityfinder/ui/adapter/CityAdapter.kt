package com.ae.cityfinder.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ae.cityfinder.R
import com.ae.cityfinder.model.City

/**
 * Constructor that takes the list of cities and the click listener callback as arguments.
 *
 * @param cities The list of City objects to be displayed.
 * @param onItemClick The callback function to be invoked when a city item is clicked.
 */
class CityAdapter(private val cities: List<City>, private val onItemClick: (City) -> Unit) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    /**
     * Constructor that takes the View inflated from the layout resource.
     *
     * @param itemView The inflated View representing a single city item.
     */
    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: TextView = itemView.findViewById(R.id.cityName)
        private val cityCoord: TextView = itemView.findViewById(R.id.cityCoord)

        /**
         * Binds the City object data to the TextView elements in the ViewHolder.
         *
         * @param city The City object containing information to be displayed.
         */
        fun bind(city: City) {
            "${city.name}, ${city.country}".also { cityName.text = it }
            "Lon: ${city.coord.lon}, Lat: ${city.coord.lat}".also { cityCoord.text = it }
            itemView.setOnClickListener { onItemClick(city) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        // Inflate the layout for a single city item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        // Bind city data to the ViewHolder for the current position
        holder.bind(cities[position])
    }

    // Return the total number of cities in the list
    override fun getItemCount() = cities.size
}