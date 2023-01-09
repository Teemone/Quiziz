package com.example.quizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quizapp.databinding.FragmentWelcomeBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private var editText: TextInputEditText? = null
    private var btnContinue: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText = binding.etName
        btnContinue = binding.btnContinue

        btnContinue?.setOnClickListener {
            if (binding.etName.text.isNullOrEmpty()){
                Snackbar.make(binding.flWelcome, "Please enter a name first", Snackbar.LENGTH_SHORT).show()
            }
            else{
                val text = binding.etName.text?.toString()
                val actionNavigateToNextFragment = WelcomeFragmentDirections.
                actionWelcomeFragmentToQuestionSet(username = text!!)
                view.findNavController().navigate(actionNavigateToNextFragment)

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}