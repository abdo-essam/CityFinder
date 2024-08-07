package com.ae.cityfinder.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ae.cityfinder.model.City
import com.ae.cityfinder.repository.CityRepository


/**
 * This class manages city data retrieval and exposes it as LiveData for UI observation
 * @param application The Application object used for context.
 */
class CityViewModel(application: Application) : AndroidViewModel(application) {
    // Repository to fetch city data
    private val repository = CityRepository(application)
    /**
     * MutableLiveData holding the list of City objects. This serves as the source of truth
     * for city data observed by the UI.
     */
    private val _cities = MutableLiveData<List<City>>()
    /**
     * Publicly accessible LiveData representing the list of City objects. This allows the UI
     * to observe changes to the city data.
     */
    val cities: LiveData<List<City>> get() = _cities

    init {
        // Load all cities on initialization
        // TODO:  Display these cities in a scrollable list, in alphabetical order (city first, country after).
        _cities.value = repository.getCitiesWithPrefix("")
    }

    /**
     * Filters the city data based on the provided query string (prefix).
     *
     * @param query The user-entered query string to filter cities by.
     */
    fun filterCities(query: String) {
        _cities.value = repository.getCitiesWithPrefix(query)
    }
}
