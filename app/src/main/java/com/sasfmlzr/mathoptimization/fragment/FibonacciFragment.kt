package com.sasfmlzr.mathoptimization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseFragment
import com.sasfmlzr.mathoptimization.databinding.FragmentFibonacciBinding
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector
import com.sasfmlzr.mathoptimization.matchParser.MatchParser
import java.lang.RuntimeException

class FibonacciFragment:  BaseFragment<FibonacciFragmentVM,
        FragmentFibonacciBinding>(FibonacciFragmentVM::class) {

    override fun getLayoutId() = R.layout.fragment_fibonacci

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    fun fibb(n: Int): Double {
        var f: Int
        var f1 = 1
        var f2 = 0
        for (i in 1..n) {
            f = f1 + f2
            f1 = f2
            f2 = f
        }
        return f2.toDouble()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel

        val functionText = arguments?.getString("function_text")?:throw RuntimeException()
        val interval0 = arguments?.getString("interval0")?:throw RuntimeException()
        val interval1 = arguments?.getString("interval1")?:throw RuntimeException()
        // String iteration = arguments?.getString("iteration");
        val maxx = arguments?.getString("maxx")?:throw RuntimeException()
        var int0 = java.lang.Double.valueOf(interval0)
        var int1 = java.lang.Double.valueOf(interval1)
        var iter = 0
        val epsilum = arguments?.getString("epsilum")?:throw RuntimeException()
        val ldop = arguments?.getString("ldop")?:throw RuntimeException()
        val eps = java.lang.Double.valueOf(epsilum)
        val ld = java.lang.Double.valueOf(ldop)
        val k = 0
        var i: Int
        val fn: Int
        val n: Int
        var x: Double
        var y: Double
        var z: Double
        binding.textView6!!.text = ""
        var vivod: String
        var fy: Double
        var fz: Double
        var L_itog: Double
        var okruglenie: Double
        fn = Math.ceil(Math.abs((int1 - int0) / ld)).toInt()
        binding.textView6!!.text = binding.textView6!!.text.toString() + "Fn=" + fn + "\n"
        i = 1
        do {
            i = i + 1
        } while (fibb(i) < fn)
        binding.textView6!!.text = binding.textView6!!.text.toString() + "N=" + (i - 1) + "\n"
        n = i - 1
        iter = i
        i = 0
        val formula1 = arrayOf(functionText)
        val p = MatchParser()
        var j = -1
        var jj = 2
        try {
            do {
                j = j + 1
                y = int0 + (int1 - int0) * (fibb(iter - 2 - j) / fibb(iter - j))
                z = int0 + (int1 - int0) * (fibb(iter - 1 - j) / fibb(iter - j))
                okruglenie = Math.round(y * 1000).toDouble() / 1000
                binding.textView6!!.text = binding.textView6!!.text.toString() + "\n" + "y[" + j + "]=" + okruglenie
                okruglenie = Math.round(z * 1000).toDouble() / 1000
                binding.textView6!!.text = binding.textView6!!.text.toString() + "\n" + "z[" + j + "]=" + okruglenie
                println("y[$j] = $y")
                println("z[$j] = $z")
                x = y
                p.setVariable("x", x)
                vivod = formula1[i] + "=" + p.parse(formula1[i])
                okruglenie =
                    Math.round(p.parse(formula1[i]) * 1000).toDouble() / 1000
                binding.textView6!!.text =
                    binding.textView6!!.text.toString() + "\n" + "F[y" + j + "]" + formula1[i] + "=" + okruglenie
                println(vivod + "=" + p.parse(formula1[i]))
                fy = p.parse(formula1[i])
                x = z
                p.setVariable("x", x)
                okruglenie =
                    Math.round(p.parse(formula1[i]) * 1000).toDouble() / 1000
                binding.textView6!!.text =
                    binding.textView6!!.text.toString() + "\n" + "F[z" + j + "]" + formula1[i] + "=" + okruglenie + "\n"
                fz = p.parse(formula1[i])
                println("$fy=$fz")
                jj = jj + 1
                if (j == n - 2) {
                    binding.textView6!!.text = binding.textView6!!.text.toString() + "K=n-3 " + "\n"
                    z = z + eps
                    okruglenie = Math.round(y * 1000).toDouble() / 1000
                    binding.textView6!!.text =
                        binding.textView6!!.text.toString() + "\n" + "y[" + j + "]=" + okruglenie
                    okruglenie = Math.round(z * 1000).toDouble() / 1000
                    binding.textView6!!.text =
                        binding.textView6!!.text.toString() + "\n" + "z[" + j + "]=" + okruglenie
                    x = y
                    p.setVariable("x", x)
                    vivod = formula1[i] + "=" + p.parse(formula1[i])
                    okruglenie =
                        Math.round(p.parse(formula1[i]) * 1000).toDouble() / 1000
                    binding.textView6!!.text =
                        binding.textView6!!.text.toString() + "\n" + "F[y" + j + "]" + formula1[i] + "=" + okruglenie
                    println(vivod + "=" + p.parse(formula1[i]))
                    fy = p.parse(formula1[i])
                    x = z
                    p.setVariable("x", x)
                    okruglenie =
                        Math.round(p.parse(formula1[i]) * 1000).toDouble() / 1000
                    binding.textView6!!.text =
                        binding.textView6!!.text.toString() + "\n" + "F[z" + j + "]" + formula1[i] + "=" + okruglenie + "\n"
                    fz = p.parse(formula1[i])
                    if (maxx == "false") {
                        if (fy < fz) int1 = z else int0 = y
                    } else {
                        if (fy > fz) int1 = z else int0 = y
                    }
                    break
                }
                if (maxx == "false") {
                    if (fy < fz) int1 = z else int0 = y
                    binding.textView6!!.text =
                        binding.textView6!!.text.toString() + "Функция на отрезке [" + int0 + "," + int1 + "]" + "\n"
                } else {
                    if (fy > fz) int1 = z else int0 = y
                    binding.textView6!!.text =
                        binding.textView6!!.text.toString() + "Функция на отрезке [" + int0 + "," + int1 + "]" + "\n"
                }
                L_itog = Math.abs(int1 - int0)
                okruglenie = Math.round(L_itog * 1000).toDouble() / 1000
                binding.textView6!!.text =
                    binding.textView6!!.text.toString() + "\n" + "L[" + jj + "] = " + okruglenie + "\n"
                println("L[$jj] = $L_itog")
                binding.textView6!!.text = binding.textView6!!.text.toString() + "\n" + "k = " + j + "\n"
            } while (L_itog > ld)
            x = (int0 + int1) / 2
            binding.textView6!!.text = binding.textView6!!.text.toString() + "Функция F(x)=" + functionText + "\n"
            binding.textView6!!.text =
                binding.textView6!!.text.toString() + "Количество вычислений равно " + (jj - 1) + "\n"
            binding.textView6!!.text =
                binding.textView6!!.text.toString() + "Количество итераций равно " + (j - 1) + "\n"
            binding.textView6!!.text =
                binding.textView6!!.text.toString() + "Функция на отрезке [" + int0 + "," + int1 + "]" + "\n"
            if (maxx == "false") {
                binding.textView6!!.text =
                    binding.textView6!!.text.toString() + "имеет точку минимума приблизительно \nравную " + x + "\n"
            } else {
                binding.textView6!!.text =
                    binding.textView6!!.text.toString() + "имеет точку максимума приблизительно \nравную " + x + "\n"
            }
            p.setVariable("x", x)
            binding.textView6!!.text = binding.textView6!!.text.toString() + "Функция F(x)=" + p.parse(
                formula1[i]
            ) + "\n"
            binding.textView6!!.text = binding.textView6!!.text.toString() + "\n\n\n\n"
        } catch (e: Exception) {
            System.err.println("Error while parsing '" + formula1[i] + "' with message: " + e.message)
        }

        return binding.root
    }
}