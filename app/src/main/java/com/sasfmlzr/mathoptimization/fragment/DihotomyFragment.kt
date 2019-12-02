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
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector
import com.sasfmlzr.mathoptimization.matchParser.MatchParser
import java.lang.RuntimeException

class DihotomyFragment :  BaseFragment<DihotomyFragmentVM,
        FragmentDihotomyBinding>(DihotomyFragmentVM::class){
    
    override fun getLayoutId() = R.layout.fragment_dihotomy

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)
    
    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel

        val function_text = arguments?.getString("function_text")?:throw RuntimeException()
        val interval0 = arguments?.getString("interval0")?:throw RuntimeException()
        val interval1 = arguments?.getString("interval1")?:throw RuntimeException()
        val epsilum = arguments?.getString("epsilum")?:throw RuntimeException()
        val ldop = arguments?.getString("ldop")?:throw RuntimeException()
        val maxx = arguments?.getString("maxx")?:throw RuntimeException()
        var int0 = java.lang.Double.valueOf(interval0)
        var int1 = java.lang.Double.valueOf(interval1)
        val eps = java.lang.Double.valueOf(epsilum)
        val ld = java.lang.Double.valueOf(ldop)
        val k = 0
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
        var L_itog: Double
        var okruglenie: Double
        val formula1 = arrayOf(function_text)
        val p = MatchParser()
        p.setVariable("x", x)
        var j = 0
        var jj = 0
        val i = 0
        try {
            do {
                y = (int0 + int1 - eps) / 2
                z = (int0 + int1 + eps) / 2
                okruglenie = Math.round(y * 1000).toDouble() / 1000
                binding.textView3!!.text =
                    binding.textView3!!.text.toString() + "\n" + "y[" + j + "]=" + okruglenie
                okruglenie = Math.round(z * 1000).toDouble() / 1000
                binding.textView3!!.text =
                    binding.textView3!!.text.toString() + "\n" + "z[" + j + "]=" + okruglenie
                println("y[$j] = $y")
                println("z[$j] = $z")
                jj = jj + 2
                x = y
                p.setVariable("x", x)
                vivod = formula1[i] + "=" + p.parse(formula1[i])
                okruglenie =
                    Math.round(p.parse(formula1[i]) * 1000).toDouble() / 1000
                binding.textView3!!.text =
                    binding.textView3!!.text.toString() + "\n" + "F[y" + j + "]" + formula1[i] + "=" + okruglenie
                println(vivod + "=" + p.parse(formula1[i]))
                fy = p.parse(formula1[i])
                x = z
                p.setVariable("x", x)
                okruglenie =
                    Math.round(p.parse(formula1[i]) * 1000).toDouble() / 1000
                binding.textView3!!.text =
                    binding.textView3!!.text.toString() + "\n" + "F[z" + j + "]" + formula1[i] + "=" + okruglenie
                fz = p.parse(formula1[i])
                println("$fy=$fz")
                if (maxx == "false") {
                    if (fy < fz) int1 = z else int0 = y
                } else {
                    if (fy > fz) int1 = z else int0 = y
                }
                L_itog = Math.abs(int1 - int0)
                okruglenie = Math.round(L_itog * 1000).toDouble() / 1000
                binding.textView3!!.text =
                    binding.textView3!!.text.toString() + "\n" + "L[" + jj + "] = " + okruglenie + "\n"
                println("L[$jj] = $L_itog")
                if (ld < L_itog) binding.textView3!!.text =
                    binding.textView3!!.text.toString() + "\n" + "k = " + (j + 1)
                j = j + 1
                if (j > 10) break
            } while (L_itog > ld)
            x = (int0 + int1) / 2
            binding.textView3!!.text = binding.textView3!!.text.toString() + "Функция F(x)=" + function_text + "\n"
            binding.textView3!!.text =
                binding.textView3!!.text.toString() + "Количество вычислений равно " + jj + "\n"
            binding.textView3!!.text =
                binding.textView3!!.text.toString() + "Количество итераций равно " + (j - 1) + "\n"
            binding.textView3!!.text =
                binding.textView3!!.text.toString() + "Функция на отрезке [" + int0 + "," + int1 + "]" + "\n"
            if (maxx == "false") {
                binding.textView3!!.text =
                    binding.textView3!!.text.toString() + "имеет точку минимума приблизительно \nравную " + x + "\n"
            } else {
                binding.textView3!!.text =
                    binding.textView3!!.text.toString() + "имеет точку максимума приблизительно \nравную " + x + "\n"
            }
            p.setVariable("x", x)
            binding.textView3!!.text = binding.textView3!!.text.toString() + "Функция F(x)=" + p.parse(
                formula1[i]
            ) + "\n"
            binding.textView3!!.text = binding.textView3!!.text.toString() + "\n\n\n\n"
        } catch (e: Exception) {
            System.err.println("Error while parsing '" + formula1[i] + "' with message: " + e.message)
        }
        return binding.root
    }
}