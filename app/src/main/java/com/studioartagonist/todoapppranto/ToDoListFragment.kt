package com.studioartagonist.todoapppranto

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.studioartagonist.todoapppranto.databinding.FragmentNewToDoBinding
import com.studioartagonist.todoapppranto.databinding.FragmentToDoListBinding
import com.studioartagonist.todoapppranto.prefdata.LoginPreference
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ToDoListFragment : Fragment() {
    private lateinit var preference: LoginPreference
    private lateinit var binding : FragmentToDoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.todo_list_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_logout ->{
                lifecycle.coroutineScope.launch {
                    preference.setLoginStatus(status = false,0L,requireContext())
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preference = LoginPreference(requireContext())
        preference.isLoggedInFlow
            .asLiveData()
            .observe(viewLifecycleOwner){
            if (!it){
                findNavController().navigate(R.id.action_toDoListFragment_to_loginFragment)

            }
        }

        binding = FragmentToDoListBinding.inflate(inflater,container,false)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_toDoListFragment_to_newToDoFragment2)
        }


        return binding.root

    }

}