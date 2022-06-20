package com.studioartagonist.todoapppranto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.studioartagonist.todoapppranto.databinding.FragmentNewToDoBinding
import com.studioartagonist.todoapppranto.databinding.FragmentToDoListBinding


class ToDoListFragment : Fragment() {

    private lateinit var binding : FragmentToDoListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(inflater,container,false)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_toDoListFragment_to_newToDoFragment2)
        }


        return binding.root

    }

}