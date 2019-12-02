package com.sasfmlzr.mathoptimization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseFragment
import com.sasfmlzr.mathoptimization.databinding.FragmentFunctionBinding
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector

class FunctionFragment : BaseFragment<FunctionFragmentVM,
        FragmentFunctionBinding>(FunctionFragmentVM::class) {

    override fun getLayoutId() = R.layout.fragment_function

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel

        binding.mainPager.adapter = FragmentAdapter(this)

        return binding.root
    }
}