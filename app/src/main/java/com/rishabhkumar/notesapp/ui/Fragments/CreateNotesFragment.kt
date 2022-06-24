package com.rishabhkumar.notesapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rishabhkumar.notesapp.R
import com.rishabhkumar.notesapp.databinding.FragmentCreateNotesBinding

class CreateNotesFragment : Fragment() {

    lateinit var binding:FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(inflater, container,false)
        return binding.root
    }

}