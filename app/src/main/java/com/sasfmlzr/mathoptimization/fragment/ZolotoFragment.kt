package com.sasfmlzr.mathoptimization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseFragment
import com.sasfmlzr.mathoptimization.databinding.FragmentZolotoBinding
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector
import com.sasfmlzr.mathoptimization.matchParser.MatchParser
import kotlin.math.roundToInt

class ZolotoFragment : BaseFragment<ZolotoFragmentVM,
        FragmentZolotoBinding>(ZolotoFragmentVM::class) {

    override fun getLayoutId() = R.layout.fragment_zoloto

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        
        val PHI = (1 + Math.sqrt(5.0)) / 2
        
        val function_text = arguments?.getString("function_text")?:throw RuntimeException()
        val interval0 = arguments?.getString("interval0")?:throw RuntimeException("interval0 cannot be null")
        val interval1 = arguments?.getString("interval1")?:throw RuntimeException("interval1 cannot be null")
        val ldop = arguments?.getString("ldop")?:throw RuntimeException("ldop cannot be null")
        val maxx = arguments?.getString("maxx")?:throw RuntimeException()
        var int0 = interval0.toDouble()
        var int1 = interval1.toDouble()
        val ld = ldop.toDouble()
        val k = 0
        var x: Double
        var y: Double
        var z: Double
        binding.textView5!!.text = ""
        var vivod: String
        var fy: Double
        var fz: Double
        var L_itog: Double
        var okruglenie: Double
        val formula1 = arrayOf(function_text)
        val p = MatchParser()
        var j = 0
        var jj = 2
        val i = 0
        try {
            do {
                y = int1 - (int1 - int0) / PHI
                z = int0 + (int1 - int0) / PHI
                okruglenie = Math.round(y * 1000).toDouble() / 1000
                binding.textView5!!.text = binding.textView5!!.text.toString() + "\n" + "y[" + j + "]=" + okruglenie
                okruglenie = Math.round(z * 1000).toDouble() / 1000
                binding.textView5!!.text = binding.textView5!!.text.toString() + "\n" + "z[" + j + "]=" + okruglenie
                println("y[$j] = $y")
                println("z[$j] = $z")
                x = y
                p.setVariable("x", x)
                vivod = formula1[i] + "=" + p.parse(formula1[i])
                okruglenie =
                    (p.parse(formula1[i]) * 1000).roundToInt().toDouble() / 1000
                binding.textView5!!.text =
                    binding.textView5!!.text.toString() + "\n" + "F[y" + j + "]" + formula1[i] + "=" + okruglenie
                println(vivod + "=" + p.parse(formula1[i]))
                fy = p.parse(formula1[i])
                x = z
                p.setVariable("x", x)
                okruglenie =
                    (p.parse(formula1[i]) * 1000).roundToInt().toDouble() / 1000
                binding.textView5!!.text =
                    binding.textView5!!.text.toString() + "\n" + "F[z" + j + "]" + formula1[i] + "=" + okruglenie
                fz = p.parse(formula1[i])
                println("$fy=$fz")
                if (maxx == "false") {
                    if (fy < fz) int1 = z else int0 = y
                } else {
                    if (fy > fz) int1 = z else int0 = y
                }
                L_itog = Math.abs(int1 - int0)
                okruglenie = Math.round(L_itog * 1000).toDouble() / 1000
                binding.textView5!!.text =
                    binding.textView5!!.text.toString() + "\n" + "L[" + jj + "] = " + okruglenie + "\n"
                println("L[$jj] = $L_itog")
                if (ld < L_itog) binding.textView5!!.text =
                    binding.textView5!!.text.toString() + "\n" + "k = " + (j + 1)
                j = j + 1
                jj = jj + 1
                if (j > 10) break
            } while (L_itog > ld)
            x = (int0 + int1) / 2
            binding.textView5!!.text = binding.textView5!!.text.toString() + "Функция F(x)=" + function_text + "\n"
            binding.textView5!!.text =
                binding.textView5!!.text.toString() + "Количество вычислений равно " + (jj - 1) + "\n"
            binding.textView5!!.text =
                binding.textView5!!.text.toString() + "Количество итераций равно " + (j - 1) + "\n"
            binding.textView5!!.text =
                binding.textView5!!.text.toString() + "Функция на отрезке [" + int0 + "," + int1 + "]" + "\n"
            if (maxx == "false") {
                binding.textView5!!.text =
                    binding.textView5!!.text.toString() + "имеет точку минимума приблизительно \nравную " + x + "\n"
            } else {
                binding.textView5!!.text =
                    binding.textView5!!.text.toString() + "имеет точку максимума приблизительно \nравную " + x + "\n"
            }
            p.setVariable("x", x)
            binding.textView5!!.text = binding.textView5!!.text.toString() + "Функция F(x)=" + p.parse(
                formula1[i]
            ) + "\n"
            binding.textView5!!.text = binding.textView5!!.text.toString() + "\n\n\n\n"
        } catch (e: Exception) {
            System.err.println("Error while parsing '" + formula1[i] + "' with message: " + e.message)
        }

        return binding.root
    }
}