package com.rishabhkumar.notesapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.rishabhkumar.notesapp.R
import com.rishabhkumar.notesapp.databinding.FragmentHomeBinding
import com.rishabhkumar.notesapp.ui.Adapter.NotesAdapter
import com.rishabhkumar.notesapp.viewmodel.NotesViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.getNotes().observe(viewLifecycleOwner,{ notesList ->
            binding.recyclerViewAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
            binding.recyclerViewAllNotes.adapter = NotesAdapter(requireContext(),notesList)
        })


        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
            //this id = action_homeFragment_to_createNotesFragment comes from nav graph
        }
        return binding.root
    }

}