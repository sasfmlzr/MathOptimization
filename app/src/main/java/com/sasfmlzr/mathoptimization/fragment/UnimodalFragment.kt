package com.sasfmlzr.mathoptimization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseFragment
import com.sasfmlzr.mathoptimization.databinding.FragmentDihotomyBinding
import com.sasfmlzr.mathoptimization.databinding.FragmentUnimodalBinding
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector
import com.sasfmlzr.mathoptimization.matchParser.MatchParser

class UnimodalFragment :  BaseFragment<UnimodalFragmentVM,
        FragmentUnimodalBinding>(UnimodalFragmentVM::class) {

    override fun getLayoutId() = R.layout.fragment_unimodal

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    var textView9: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
   
        val functionText = arguments?.getString("function_text")?:RuntimeException() as String
        textView9!!.text = ""
        val formula1 = arrayOf(functionText)
        val p = MatchParser()
        val i = 0
        var j: Int
        var x = 0.0
        var fy1: Double
        var fy2: Double
        var fy3: Double
        var fy4 = 123123123.0
        p.setVariable("x", x)
        try {
            j = -30
            do {
                x = j.toDouble()
                p.setVariable("x", x)
                fy1 = p.parse(formula1[i])
                x = j + 1.toDouble()
                p.setVariable("x", x)
                fy2 = p.parse(formula1[i])
                x = j + 2.toDouble()
                p.setVariable("x", x)
                fy3 = p.parse(formula1[i])
                if (fy1 >= fy2 && fy2 <= fy3) {
                    fy4 = fy2
                    textView9!!.text =
                        textView9!!.text.toString() + "Примерная точка минимума [" + (j + 1) + "]=" + fy4 + "\n"
                }
                if (fy1 <= fy2 && fy2 >= fy3) {
                    fy4 = fy2
                    textView9!!.text =
                        textView9!!.text.toString() + "Примерная точка максимума [" + (j + 1) + "]=" + fy4 + "\n"
                }
                j = j + 1
            } while (j != 30)
            if (fy4 == 123123123.0) textView9!!.text =
                textView9!!.text.toString() + "Функция не унимодальна\n"
            textView9!!.text = textView9!!.text.toString() + "\n\n\nПодробное решение:\n\n\n"
            j = -30
            while (j <= 30) {
                x = j.toDouble()
                p.setVariable("x", x)
                fy1 = p.parse(formula1[i])
                textView9!!.text =
                    textView9!!.text.toString() + "Функция F(x)[" + j + "]=" + fy1 + "\n"
                j = j + 1
            }
            textView9!!.text = textView9!!.text.toString() + "\n\n\n"
        } catch (e: Exception) {
            System.err.println("Error while parsing '" + formula1[i] + "' with message: " + e.message)
        }

        return binding.root
    }
}