package com.sasfmlzr.mathoptimization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseFragment
import com.sasfmlzr.mathoptimization.databinding.FragmentFunctionBinding
import com.sasfmlzr.mathoptimization.databinding.FragmentFunctionOldBinding
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector

class FunctionFragmentOld : BaseFragment<FunctionFragmentVM,
        FragmentFunctionOldBinding>(FunctionFragmentVM::class) {

    override fun getLayoutId() = R.layout.fragment_function_old

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel

        binding.dihotomy!!.setOnClickListener(onClickListener)
        binding.zoloto!!.setOnClickListener(onClickListener)
        binding.unimod!!.setOnClickListener(onClickListener)
        binding.fibonachi!!.setOnClickListener(onClickListener)

        return binding.root
    }

    val onClickListener = object : View.OnClickListener {
        override fun onClick(b: View) {
          /*  when (b.id) {
                R.id.zoloto -> {
                    val intent1 = Intent(this, ZolotoFragment::class.java)
                    intent1.putExtra("function_text", functionText!!.text.toString())
                    intent1.putExtra("interval0", interval0!!.text.toString())
                    intent1.putExtra("interval1", interval1!!.text.toString())
                    intent1.putExtra("ldop", ldop!!.text.toString())
                    intent1.putExtra("lname1", functionText!!.text.toString())
                    intent1.putExtra("maxx", maxx!!.isChecked.toString())
                    startActivity(intent1)
                }
                R.id.dihotomy -> {
                    val intent = Intent(this, Dihotomy::class.java)
                    intent.putExtra("function_text", functionText!!.text.toString())
                    intent.putExtra("interval0", interval0!!.text.toString())
                    intent.putExtra("interval1", interval1!!.text.toString())
                    intent.putExtra("epsilum", epsilum!!.text.toString())
                    intent.putExtra("ldop", ldop!!.text.toString())
                    intent.putExtra("lname1", functionText!!.text.toString())
                    intent.putExtra("maxx", maxx!!.isChecked.toString())
                    startActivity(intent)
                }
                R.id.fibonachi -> {
                    val intent2 = Intent(this, FibonachiFragment::class.java)
                    intent2.putExtra("function_text", functionText!!.text.toString())
                    intent2.putExtra("interval0", interval0!!.text.toString())
                    intent2.putExtra("interval1", interval1!!.text.toString())
                    intent2.putExtra("lname1", functionText!!.text.toString())
                    intent2.putExtra("epsilum", epsilum!!.text.toString())
                    intent2.putExtra("ldop", ldop!!.text.toString())
                    intent2.putExtra("maxx", maxx!!.isChecked.toString())
                    startActivity(intent2)
                }
                R.id.unimod -> {
                    val intent3 = Intent(this, UnimodalFragment::class.java)
                    intent3.putExtra("function_text", functionText!!.text.toString())
                    startActivity(intent3)
                }
            }*/
        }
    }
}