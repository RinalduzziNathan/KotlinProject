package com.example.androidkotlinproject.ui.marvel

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidkotlinproject.ui.RecyclerView.CustomAdapter
import com.example.androidkotlinproject.databinding.FragmentDashboardBinding

class MarvelFragment : Fragment() {

    private lateinit var marvelViewModel: MarvelViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        marvelViewModel =
            ViewModelProvider(this).get(MarvelViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        marvelViewModel.recyclerLiveData().observe(viewLifecycleOwner, Observer {
            var recyclerView = binding.rvcharacters
            recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
            val adapter = CustomAdapter(it) {
                val intent = Intent(activity, DetailMarvelCard::class.java).apply {
                     putExtra(EXTRA_MESSAGE, it.id.toString())
                }
                startActivity(intent)
            }


            recyclerView.adapter = adapter

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}