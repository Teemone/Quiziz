package com.example.quizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quizapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var username: String? = null
    private var totalQuestions: Int? = null
    private var correctAnswers: Int? = null
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(Object.USERNAME)
            totalQuestions = it.getInt(Object.TOTAL_QUESTIONS)
            correctAnswers = it.getInt(Object.CORRECT_ANSWERS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val congratulations = "Congratulations"
        val betterLuck = "Better luck next time"

        if (correctAnswers!!<=1) {
            binding.tvCongratsName.text = getString(R.string.congratulations, betterLuck, username)
            binding.ivTrophy.setImageResource(R.drawable.sad_face)
        }
        else {
            binding.tvCongratsName.text =
                getString(R.string.congratulations, congratulations, username)
            binding.ivTrophy.setImageResource(R.drawable.trophy_2)

        }
        binding.tvScore.text = getString(R.string.your_score_is, correctAnswers, totalQuestions)
        binding.btnFinish.setOnClickListener {
            val toWelcomeFragment = ResultFragmentDirections
                .actionResultFragmentToWelcomeFragment()
            view.findNavController().navigate(toWelcomeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}