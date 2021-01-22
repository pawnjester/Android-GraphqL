package com.example.androidgraphql

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.androidgraphql.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var countriesAdapter: CountriesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        lifecycleScope.launchWhenResumed {
            val response = try {
                Apollo.setupApolloClient().query(CountryListQuery()).await()
            } catch (e: ApolloException) {
                Timber.d(e)
                return@launchWhenResumed
            }
            val countries = response.data?.countries

            if (countries != null && !response.hasErrors()) {
                countriesAdapter.setCountriesData(countries)
            }

        }
    }

    private fun setupRecyclerView() {
        binding.rvCountries.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )
        binding.rvCountries.adapter = countriesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}