package com.example.quizapp

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quizapp.databinding.FragmentQuestionSetBinding

class QuestionSet : Fragment(), View.OnClickListener {
    private var _binding: FragmentQuestionSetBinding? = null
    private lateinit var flQuestionSet: FrameLayout
    private val binding get() = _binding!!
    private var username: String? = null
    private var count = 1
    private var currentSelection: Int? = null
    private var correctAnswers = 0
    private val questions = listOfQuestions()

    private val mapOfLayout2Tv = mapOf(
        R.id.tv_option1 to R.id.a,
        R.id.tv_option2 to R.id.b,
        R.id.tv_option3 to R.id.c,
        R.id.tv_option4 to R.id.d)

    private val mapOfId2Id2 = mapOf(
        R.id.tv_option1 to R.id.indicator1,
        R.id.tv_option2 to R.id.indicator2,
        R.id.tv_option3 to R.id.indicator3,
        R.id.tv_option4 to R.id.indicator4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(Object.USERNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionSetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flQuestionSet = binding.flQuestionSet

        binding.tvOption1.setOnClickListener(this)
        binding.tvOption2.setOnClickListener(this)
        binding.tvOption3.setOnClickListener(this)
        binding.tvOption4.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

        updateUi()

    }

    /** Prepares the list of question objects and returns a shorter list*/
    private fun listOfQuestions():List<Question>{
        return Object.allQuestionsList().shuffled().take(5)
    }

    /** Handles the display of the image and options*/
    private fun updateUi(){

        binding.imgVehicleLogo.setImageResource(questions[count-1].vehicleLogo)
        binding.a.text = questions[count-1].answers[0]
        binding.b.text = questions[count-1].answers[1]
        binding.c.text = questions[count-1].answers[2]
        binding.d.text = questions[count-1].answers[3]

        binding.progressbar.progress = count
        binding.pbText.text = "$count/${binding.progressbar.max}"

        if (count == questions.size)
            binding.btnSubmit.text = getString(R.string.finish)
        else
            binding.btnSubmit.text = getString(R.string.submit)

        isClickable(true)
        disableIndicators()
    }

    private fun disableIndicators(){
        for (i in mapOfId2Id2.values){
            flQuestionSet.findViewById<TextView>(i).visibility = View.GONE
        }
    }

    /** Handles the onclick event of textViews*/

    override fun onClick(v: View?) {

        when(v?.id){

            R.id.tv_option1 -> {
                binding.tvOption1.background = ContextCompat.getDrawable(requireContext(), R.drawable.tv_border_selected)
                binding.a.setTypeface(binding.a.typeface, Typeface.BOLD)
                currentSelection = R.id.tv_option1
                setTextViewsBgUnselected(false)
            }

            R.id.tv_option2 -> {
                binding.tvOption2.background = ContextCompat.getDrawable(requireContext(), R.drawable.tv_border_selected)
                binding.b.setTypeface(binding.b.typeface, Typeface.BOLD)
                currentSelection = R.id.tv_option2
                setTextViewsBgUnselected(false)
            }

            R.id.tv_option3 -> {
                binding.tvOption3.background = ContextCompat.getDrawable(requireContext(), R.drawable.tv_border_selected)
                binding.c.setTypeface(binding.c.typeface, Typeface.BOLD)
                currentSelection = R.id.tv_option3
                setTextViewsBgUnselected(false)
            }

            R.id.tv_option4 -> {
                binding.tvOption4.background = ContextCompat.getDrawable(requireContext(), R.drawable.tv_border_selected)
                binding.d.setTypeface(binding.d.typeface, Typeface.BOLD)
                currentSelection = R.id.tv_option4
                setTextViewsBgUnselected(false)
            }

            R.id.btn_submit -> {
                if (currentSelection == null){
                    count++

                    when{
                        count <= questions.size -> {
                            updateUi()
                            setTextViewsBgUnselected(true)
                        }

                        else -> {
                            val toResultFragment =
                                QuestionSetDirections.actionQuestionSetToResultFragment(
                                    username = username!!,
                                    totalQuestions = questions.size,
                                    correctAnswers = correctAnswers
                                )
                            v.findNavController().navigate(toResultFragment)
//                            val intent = Intent(this, ResultActivity::class.java)
//                            intent.putExtra(Object.USERNAME, username)
//                            intent.putExtra(Object.TOTAL_QUESTIONS, questions.size)
//                            intent.putExtra(Object.CORRECT_ANSWERS, correctAnswers)
//                            startActivity(intent)
//                            finish()
                        }
                    }
                }
                else{
                    firstPart()

                    if (count == questions.size){
                        binding.btnSubmit.text = getString(R.string.finish)
                    }
                }

            }

        }


    }

    private fun firstPart() {
        val optionId = mapOfLayout2Tv[currentSelection]
        val optionTv = flQuestionSet.findViewById<TextView>(optionId!!)
        val indicator = mapOfId2Id2[currentSelection]
        val indicatorTv = flQuestionSet.findViewById<TextView>(indicator!!)
        val correctAnswer = questions[count-1].correctAnswer
        val csLayout = flQuestionSet.findViewById<LinearLayoutCompat>(currentSelection!!)

        // Display incorrect answer
        if (optionTv.text.toString() != correctAnswer) {
            csLayout.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.tv_border_red)
            indicatorTv.background = ContextCompat.getDrawable(requireContext(), R.drawable.dot_indicator_red)
            indicatorTv.visibility = View.VISIBLE
        }
        else
            correctAnswers++

        // Display the correct answer
        for (items in getTextViews()) {
            val view = flQuestionSet.findViewById<TextView>(items)
            if (view.text.toString() == correctAnswer) {
                val indi = flQuestionSet.findViewById<TextView>(mapOfId2Id2[returnTextView2Layout(view.id).id]!!)
                returnTextView2Layout(view.id).background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.tv_border_green)
                indi.background = ContextCompat.getDrawable(requireContext(), R.drawable.dot_indicator_green)
                indi.visibility = View.VISIBLE
            }
        }

        if (count == questions.size)
            binding.btnSubmit.text = getString(R.string.finish)
        else
            binding.btnSubmit.text = getString(R.string.next)

        isClickable(false)
        currentSelection = null
    }


    /**
     * Modifies the clickable state of textviews
     */
    private fun isClickable(bool: Boolean){
        for (i in getLayouts()){
            val v = flQuestionSet.findViewById<LinearLayoutCompat>(i)
            v.isClickable = bool
        }
    }

    /**
     * Sets other options to unselected state, except for the selected option
     */

    private fun setTextViewsBgUnselected(clearAll: Boolean){
        if (!clearAll){
            for (tv in getLayouts()){
                if (currentSelection == tv ){
                    continue
                }
                flQuestionSet.findViewById<LinearLayoutCompat>(tv).background = ContextCompat.getDrawable(requireContext(), R.drawable.tv_border)
            }

            for (i in mapOfLayout2Tv){
                if (currentSelection == i.key){
                    continue
                }
                flQuestionSet.findViewById<TextView>(i.value).typeface = Typeface.DEFAULT
            }
        }
        else{
            for (tv in getLayouts()){
                flQuestionSet.findViewById<LinearLayoutCompat>(tv).background = ContextCompat.getDrawable(requireContext(), R.drawable.tv_border)
            }
            for (i in mapOfLayout2Tv){
                flQuestionSet.findViewById<TextView>(i.value).typeface = Typeface.DEFAULT
            }
        }

    }
    /** Returns a list (of IDs) of all the textview options */
    private fun getLayouts(): List<Int>{
        return listOf(R.id.tv_option1, R.id.tv_option2, R.id.tv_option3, R.id.tv_option4)
    }

    private fun getTextViews(): List<Int>{
        return listOf(R.id.a, R.id.b, R.id.c, R.id.d)
    }

//    private fun returnLayoutToTextView(id: Int): TextView{
//        return findViewById(mapOfLayout2Tv[id]!!)
//    }

    private fun returnTextView2Layout(id: Int): LinearLayoutCompat {
        var layout = 0
        for (i in mapOfLayout2Tv){
            if (i.value == id){
                layout = i.key
            }
        }
        return flQuestionSet.findViewById(layout)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}