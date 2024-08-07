package com.ae.cityfinder.repository

import android.content.Context
import com.ae.cityfinder.model.City
import com.ae.cityfinder.util.Trie
import com.google.gson.Gson
import java.io.IOException


/**
 * This class manages the loading and searching of city data using a Trie data structure.
 * Context object used to access resources (likely assets directory).
 */
class CityRepository(private val context: Context) {
    // Trie instance used to store and efficiently search city data based on names.
    private val trie = Trie()

    init {
        try {
            // Initialize Trie with city data from JSON file and convert it to lowercase for case insensitive
            loadCities().forEach { city ->
                trie.insert(city.name.lowercase(),city)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Searches for cities with names that start with the provided prefix (case-insensitive).
     *
     * @param prefix The prefix to search for.
     * @return A list of City objects whose names start with the prefix, or an empty list if no matches are found.
     */
    fun getCitiesWithPrefix(prefix: String): List<City> {
        return trie.search(prefix.lowercase())
    }

    /**
     * Loads city data from a JSON file located in the assets directory (named "cities.json").
     *
     * @return A list of City objects parsed from the JSON file.
     * @throws IOException If there's an error reading the JSON file.
     */
    private fun loadCities(): List<City> {
        val jsonString = context.assets.open("cities.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, Array<City>::class.java).toList()
    }
}
