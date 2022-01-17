package com.example.androidkotlinproject.ui.marvel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinproject.data.prefs.Prefs
import com.example.androidkotlinproject.ui.RecyclerView.CustomAdapter
import com.example.androidkotlinproject.databinding.FragmentDashboardBinding

class MarvelFragment : Fragment() {

    private lateinit var marvelViewModel: MarvelViewModel
    private var _binding: FragmentDashboardBinding? = null
    var recyclerView : RecyclerView? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listFav : MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        marvelViewModel =
            ViewModelProvider(requireActivity()).get(MarvelViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        marvelViewModel.recyclerLiveData().observe(viewLifecycleOwner, Observer {
            recyclerView = binding.rvcharacters
            recyclerView!!.layoutManager = LinearLayoutManager(activity?.applicationContext)
            val adapter = CustomAdapter(it) { marvelCharacter,id ->
                if(marvelCharacter != null){
                    val intent = Intent(activity, DetailMarvelCard::class.java).apply {
                        putExtra(EXTRA_MESSAGE, marvelCharacter.id.toString())
                    }
                    startActivity(intent)
                }else if(id!=null){
                        val prefs = Prefs(requireContext())
                        if (prefs != null) {
                            listFav = prefs.myStringArray.toMutableList()
                            if(listFav.contains(id)){
                                listFav.remove(id)
                            }else{
                                listFav.add(id)
                            }
                         prefs.myStringArray = listFav.toTypedArray()
                        }
                    }
                }
            recyclerView!!.adapter = adapter

        })

        return root
    }

    override fun onResume() {
        super.onResume()
        if(recyclerView != null ){
            recyclerView!!.adapter?.notifyDataSetChanged()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}