package com.studioartagonist.todoapppranto

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.studioartagonist.todoapppranto.databinding.FragmentToDoListBinding
import com.studioartagonist.todoapppranto.prefdata.LoginPreference
import com.studioartagonist.todoapppranto.viewmodels.TodoViewModel
import com.studioartagonist.todoapppranto.adapters.TodoAdapter
import kotlinx.coroutines.launch


class TodoListFragment : Fragment() {
    private lateinit var binding: FragmentToDoListBinding
    private lateinit var preference: LoginPreference
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.todo_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_logout -> {
                lifecycle.coroutineScope.launch {
                    preference.setLoginStatus(status = false, 0L, requireContext())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(inflater, container, false)
        preference = LoginPreference(requireContext())
        val adapter = TodoAdapter{model, action ->

        }
        binding.todoRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.todoRV.adapter = adapter
        preference.isLoggedInFlow
            .asLiveData()
            .observe(viewLifecycleOwner){
                if (!it) {
                    findNavController().navigate(R.id.action_toDoListFragment_to_loginFragment)
                }
            }

        preference.userIdFlow.asLiveData()
            .observe(viewLifecycleOwner) {
                todoViewModel.getTodoByUserId(it).observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            }


        binding.newTodoFab.setOnClickListener {
            findNavController().navigate(R.id.action_toDoListFragment_to_newToDoFragment)
        }
        return binding.root
    }
}