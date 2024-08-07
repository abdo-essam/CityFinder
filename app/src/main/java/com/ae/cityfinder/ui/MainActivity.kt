package com.ae.cityfinder.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ae.cityfinder.R
import com.ae.cityfinder.ui.adapter.CityAdapter
import com.ae.cityfinder.viewmodel.CityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CityViewModel
    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[CityViewModel::class.java]

        val cityRecyclerView: RecyclerView = findViewById(R.id.cityRecyclerView)
        val searchEditText: EditText = findViewById(R.id.searchEditText)

        // Initialize city adapter with empty list
        cityAdapter = CityAdapter(emptyList()) { city ->
            val gmmIntentUri = Uri.parse("geo:${city.coord.lat},${city.coord.lon}?q=${city.coord.lat},${city.coord.lon}(${city.name})")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        cityRecyclerView.adapter = cityAdapter
        cityRecyclerView.layoutManager = LinearLayoutManager(this)

        // Observe changes in the "cities" LiveData from the view model
        viewModel.cities.observe(this, Observer { cities ->
            // Update adapter with new list of cities
            cityAdapter = CityAdapter(cities) { city ->
                val gmmIntentUri = Uri.parse("geo:${city.coord.lat},${city.coord.lon}?q=${city.coord.lat},${city.coord.lon}(${city.name})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
            cityRecyclerView.adapter = cityAdapter
        })

        // Add text watcher to listen for changes in the search edit text
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filterCities(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}