package com.example.androidkotlinproject.ui.fav

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinproject.data.prefs.Prefs
import com.example.androidkotlinproject.databinding.FragmentNotificationsBinding
import com.example.androidkotlinproject.ui.RecyclerView.CustomAdapter
import com.example.androidkotlinproject.ui.marvel.DetailMarvelCard
import com.example.androidkotlinproject.ui.marvel.MarvelViewModel
import fr.iem.model.MarvelCharacter

class FavFragment : Fragment() {

    private lateinit var favViewModel: MarvelViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var adapter: CustomAdapter
    var recyclerView: RecyclerView? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listFav: MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favViewModel =
            ViewModelProvider(requireActivity()).get(MarvelViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favViewModel.recyclerLiveData().observe(viewLifecycleOwner, Observer {

            val prefs = Prefs(requireContext())
            listFav = prefs.myStringArray.toMutableList()
            var listVarCard = mutableListOf<MarvelCharacter>()
            it.data.results.forEach {
                if (listFav.contains(it.id.toString())) {
                    //garder que les favoris
                    listVarCard.add(it)

                }
            }
            //remplacer la liste de marvelCharacter à afficher
            it.data.results = listVarCard
            recyclerView = binding.favrecycler

            recyclerView?.layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = CustomAdapter(it) { marvelCharacter, id ->
                if (marvelCharacter != null) {
                    val intent = Intent(activity, DetailMarvelCard::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, marvelCharacter.id.toString())
                    }
                    startActivity(intent)
                } else if (id != null) {
                    val prefs = Prefs(requireContext())

                        listFav = prefs.myStringArray.toMutableList()
                        listFav.remove(id)
                        prefs.myStringArray = listFav.toTypedArray()




                }
            }

            recyclerView!!.adapter = adapter


        })

        return root
    }

    override fun onResume() {
        super.onResume()
        if (recyclerView != null) {
            recyclerView?.adapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}