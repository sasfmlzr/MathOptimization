package com.sasfmlzr.mathoptimization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseFragment
import com.sasfmlzr.mathoptimization.databinding.FragmentDihotomyBinding
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector
import com.sasfmlzr.mathoptimization.matchParser.MatchParser
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class DihotomyFragment : BaseFragment<DihotomyFragmentVM,
        FragmentDihotomyBinding>(DihotomyFragmentVM::class) {

    override fun getLayoutId() = R.layout.fragment_dihotomy

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    companion object {
        const val FUNCTION_KEY = "FUNCTION_KEY"
        const val INTERVAL_MIN_KEY = "INTERVAL_MIN_KEY"
        const val INTERVAL_MAX_KEY = "INTERVAL_MAX_KEY"
        const val EPSILON_KEY = "EPSILON_KEY"
        const val LDOP_KEY = "LDOP_KEY"
        const val MAX_KEY = "MAX_KEY"

        fun newInstance(
            function: String,
            intervalMin: Double,
            intervalMax: Double,
            epsilon: Double,
            ldop: Double,
            isMax: Boolean
        ): DihotomyFragment {
            return DihotomyFragment().apply {
                arguments = bundleOf(
                    Pair(FUNCTION_KEY, function),
                    Pair(INTERVAL_MIN_KEY, intervalMin),
                    Pair(INTERVAL_MAX_KEY, intervalMax),
                    Pair(EPSILON_KEY, epsilon),
                    Pair(LDOP_KEY, ldop),
                    Pair(MAX_KEY, isMax)
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel

        val functionText = arguments?.getString(FUNCTION_KEY)!!
        val maxx = arguments?.getBoolean(MAX_KEY)!!
        var int0 = arguments?.getDouble(INTERVAL_MIN_KEY)!!
        var int1 = arguments?.getDouble(INTERVAL_MAX_KEY)!!
        val eps = arguments?.getDouble(EPSILON_KEY)!!
        val ld = arguments?.getDouble(LDOP_KEY)!!

        var x: Double
        var y: Double
        var z: Double
        //  Double x;
        y = (int0 + int1 - eps) / 2
        x = y
        binding.textView3!!.text = ""
        // делаем парсинг формулы
        var vivod: String
        var fy: Double
        var fz: Double
        var lItog: Double
        var okruglenie: Double
        val formula1 = arrayOf(functionText)
        val p = MatchParser()
        p.setVariable("x", x)
        var j = 0
        var jj = 0
        val i = 0

        val resultStr = StringBuilder()
        try {
            do {
                y = (int0 + int1 - eps) / 2
                z = (int0 + int1 + eps) / 2
                okruglenie = (y * 1000).roundToInt().toDouble() / 1000
                resultStr.append("\ny[$j]=$okruglenie")

                okruglenie = (z * 1000).roundToLong().toDouble() / 1000

                resultStr.append("\nz[$j]=$okruglenie")
                println("y[$j] = $y")
                println("z[$j] = $z")
                jj += 2
                x = y
                p.setVariable("x", x)
                vivod = formula1[i] + "=" + p.parse(formula1[i])
                okruglenie =
                    (p.parse(formula1[i]) * 1000).roundToInt().toDouble() / 1000
                resultStr.append("\nF[y$j]${formula1[i]}=$okruglenie")

                println(vivod + "=" + p.parse(formula1[i]))
                fy = p.parse(formula1[i])
                x = z
                p.setVariable("x", x)
                okruglenie =
                    (p.parse(formula1[i]) * 1000).roundToInt().toDouble() / 1000
                resultStr.append("\nF[z$j]${formula1[i]}=$okruglenie")
                fz = p.parse(formula1[i])
                println("$fy=$fz")
                if (!maxx) {
                    if (fy < fz) int1 = z else int0 = y
                } else {
                    if (fy > fz) int1 = z else int0 = y
                }
                lItog = abs(int1 - int0)
                okruglenie = (lItog * 1000).roundToInt().toDouble() / 1000
                resultStr.append("\nL[$jj] = $okruglenie\n")
                println("L[$jj] = $lItog")
                resultStr.append("\n" + "k = " + (j + 1))
                j += 1
                if (j > 10) break
            } while (lItog > ld)
            x = (int0 + int1) / 2
            resultStr.append("\nФункция F(x)=$functionText\n")
            resultStr.append("Количество вычислений равно $jj\n")
            resultStr.append("Количество итераций равно " + (j - 1) + "\n")
            resultStr.append("Функция на отрезке [$int0,$int1]\n")
            if (!maxx) {
                resultStr.append("имеет точку минимума приблизительно \nравную $x\n")
            } else {
                resultStr.append("имеет точку максимума приблизительно \nравную $x\n")
            }
            p.setVariable("x", x)
            resultStr.append("Функция F(x)=" + p.parse(
                formula1[i]
            ) + "\n\n\n\n")

            binding.textView3!!.text = resultStr.toString()
        } catch (e: Exception) {
            System.err.println("Error while parsing '" + formula1[i] + "' with message: " + e.message)
        }
        return binding.root
    }
}