package com.rishabhkumar.notesapp.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rishabhkumar.notesapp.R
import com.rishabhkumar.notesapp.databinding.FragmentEditNotesBinding
import com.rishabhkumar.notesapp.model.Notes
import com.rishabhkumar.notesapp.viewmodel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        getData()
        priorityChange()



        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }


        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.edtEditTitle.text.toString()
        val subTitle = binding.edtEditSubTitle.text.toString()
        val notes = binding.edtEditNotes.text.toString()
        val notesCurrentDate: String = getCurrentDate()

        val data = Notes(
            oldNotes.data.id,
            title = title,
            subtitle = subTitle,
            notes = notes,
            date = notesCurrentDate.toString(),
            priority = priority
        )
        viewModel.updateNotes(data)

        Toast.makeText(requireContext(), "Note updated.", Toast.LENGTH_SHORT).show()
        //finishing the fragment after creation of note
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    private fun getCurrentDate(): String {
        val c = Calendar.getInstance().time
        println("Current time => $c")
        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate = df.format(c)

        return formattedDate
    }

    //function for retrieving the data from home fragment
    private fun getData() {
        binding.edtEditTitle.setText(oldNotes.data.title)
        binding.edtEditSubTitle.setText(oldNotes.data.subtitle)
        binding.edtEditNotes.setText(oldNotes.data.notes)

        when (oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pGreenEdit.setImageResource(R.drawable.done_icon)
                binding.pRedEdit.setImageResource(0)
                binding.pYellowEdit.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pRedEdit.setImageResource(R.drawable.done_icon)
                binding.pGreenEdit.setImageResource(0)
                binding.pYellowEdit.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pYellowEdit.setImageResource(R.drawable.done_icon)
                binding.pGreenEdit.setImageResource(0)
                binding.pRedEdit.setImageResource(0)
            }
        }
    }

    private fun priorityChange() {
        //when someone wants to change priority of task or notes
        binding.pGreenEdit.setOnClickListener {
            priority = "1"
            binding.pGreenEdit.setImageResource(R.drawable.done_icon)
            binding.pRedEdit.setImageResource(0)
            binding.pYellowEdit.setImageResource(0)
        }
        binding.pRedEdit.setOnClickListener {
            priority = "2"
            binding.pRedEdit.setImageResource(R.drawable.done_icon)
            binding.pGreenEdit.setImageResource(0)
            binding.pYellowEdit.setImageResource(0)
        }
        binding.pYellowEdit.setOnClickListener {
            priority = "3"
            binding.pYellowEdit.setImageResource(R.drawable.done_icon)
            binding.pGreenEdit.setImageResource(0)
            binding.pRedEdit.setImageResource(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.iconMenuDelete) {
            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)
            bottomSheet.show()

            val txtViewNo = bottomSheet.findViewById<TextView>(R.id.txtNo)
            val txtViewYes = bottomSheet.findViewById<TextView>(R.id.txtYes)

            txtViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                Navigation.findNavController(it!!)
                    .navigate(R.id.action_editNotesFragment_to_homeFragment)
            }

            txtViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}