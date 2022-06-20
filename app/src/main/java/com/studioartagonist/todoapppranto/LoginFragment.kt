package com.studioartagonist.todoapppranto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studioartagonist.todoapppranto.databinding.FragmentLoginBinding
import com.studioartagonist.todoapppranto.entities.UserModel
import com.studioartagonist.todoapppranto.prefdata.LoginPreference


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var preference: LoginPreference
    //private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.loginBtn.setOnClickListener {
            val email = binding.emailInputET.text.toString()
            val password = binding.passwordInputET.text.toString()
        }
        binding.regBtn.setOnClickListener {
            val email = binding.emailInputET.text.toString()
            val password = binding.passwordInputET.text.toString()

            val user = UserModel(
                email = email, password = password
            )
        }

        return binding.root
    }
}