package com.rishabhkumar.notesapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rishabhkumar.notesapp.R
import com.rishabhkumar.notesapp.databinding.FragmentCreateNotesBinding
import com.rishabhkumar.notesapp.model.Notes
import com.rishabhkumar.notesapp.viewmodel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(inflater, container, false)

        binding.pGreen.setImageResource(R.drawable.done_icon)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.done_icon)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "2"
            binding.pRed.setImageResource(R.drawable.done_icon)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "3"
            binding.pYellow.setImageResource(R.drawable.done_icon)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.btnCreateSaveNotes.setOnClickListener {
            createNotes(it)
        }



        return binding.root
    }


    private fun createNotes(it: View?) {
        val title = binding.edtCreateTitle.text.toString()
        val subTitle = binding.edtCreateSubTitle.text.toString()
        val notes = binding.edtCreateNotes.text.toString()
        val notesCurrentDate: String = getCurrentDate()

        val data = Notes(
            null,
            title = title,
            subtitle = subTitle,
            notes = notes,
            date = notesCurrentDate.toString(),
            priority = priority

        )
        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Notes created.",Toast.LENGTH_SHORT).show()

    }

    private fun getCurrentDate() : String{
        val c = Calendar.getInstance().time
        println("Current time => $c")
        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate = df.format(c)

        return formattedDate
    }
}