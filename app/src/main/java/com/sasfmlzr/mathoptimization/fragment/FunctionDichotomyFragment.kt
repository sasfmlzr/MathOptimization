package com.sasfmlzr.mathoptimization.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseFragment
import com.sasfmlzr.mathoptimization.databinding.FragmentFunctionBinding
import com.sasfmlzr.mathoptimization.databinding.FragmentFunctionDichotomyBinding
import com.sasfmlzr.mathoptimization.databinding.FragmentFunctionOldBinding
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector
import kotlinx.android.synthetic.main.view_edit_text.view.*

class FunctionDichotomyFragment : BaseFragment<FunctionDichotomyFragmentVM,
        FragmentFunctionDichotomyBinding>(FunctionDichotomyFragmentVM::class) {

    override fun getLayoutId() = R.layout.fragment_function_dichotomy

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel

        binding.enterFunction.edit_text.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                binding.enterFunction.showError("Something is wrong")
            } else {
                binding.enterFunction.hideError()
            }
        }

        binding.intervalMin.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER)
        binding.intervalMax.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER)
        binding.epsilum.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER)
        binding.ldop.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER)

        return binding.root
    }

    private fun sendData(){
        //val function = binding.enterFunction.text
        val intervalMin = binding.intervalMin
        val intervalMax = binding.intervalMax
        val epsilum = binding.epsilum
        val ldop = binding.ldop
        val isMax = binding.maxx

    }
}