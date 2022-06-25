package com.rishabhkumar.notesapp.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rishabhkumar.notesapp.R
import com.rishabhkumar.notesapp.databinding.FragmentHomeBinding
import com.rishabhkumar.notesapp.model.Notes
import com.rishabhkumar.notesapp.ui.Adapter.NotesAdapter
import com.rishabhkumar.notesapp.viewmodel.NotesViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter : NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            layoutCall(notesList)
        }

        binding.txtHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                layoutCall(notesList)
            }
        }
        binding.txtMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                layoutCall(notesList)
            }
        }
        binding.txtLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                layoutCall(notesList)
            }
        }

        binding.btnAllNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                layoutCall(notesList)
            }
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
            //this id = action_homeFragment_to_createNotesFragment comes from nav graph
        }
        return binding.root
    }

    private fun layoutCall(notesList: List<Notes>) {
        oldMyNotes = notesList as ArrayList<Notes>
        binding.recyclerViewAllNotes.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adapter = NotesAdapter(requireContext(), notesList)
        binding.recyclerViewAllNotes.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes title here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFiltering(newText)
                return true
            }

        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(p0:String?) {
        val newFilteredList = arrayListOf<Notes>()
        for(i in oldMyNotes){
            if(i.title.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}