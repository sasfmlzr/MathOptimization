package com.sasfmlzr.mathoptimization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseFragment
import com.sasfmlzr.mathoptimization.databinding.FragmentFunctionBinding
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector

class FunctionFragment : BaseFragment<FunctionFragmentVM,
        FragmentFunctionBinding>(FunctionFragmentVM::class) {

    override fun getLayoutId() = R.layout.fragment_function

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    private val viewPagerCallback: ViewPager2.OnPageChangeCallback by lazy {
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                when (position) {
                    0 -> {
                        if (positionOffset > 0.5) {
                            markFibonacciAsActive()
                        } else {
                            markDichotomyAsActive()
                        }
                    }
                    1 -> {
                        if (positionOffset > 0.5) {
                            markGoldenAsActive()
                        } else {
                            markFibonacciAsActive()
                        }
                    }
                    2 -> {
                        markGoldenAsActive()
                    }
                }

                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel

        binding.mainPager.adapter = FragmentAdapter(this)

        binding.dichotomy.setOnClickListener {
            binding.mainPager.setCurrentItem(0, true)
            markDichotomyAsActive()
        }

        binding.fibonacci.setOnClickListener {
            binding.mainPager.setCurrentItem(1, true)
            markFibonacciAsActive()
        }

        binding.golden.setOnClickListener {
            binding.mainPager.setCurrentItem(2, true)
            markGoldenAsActive()
        }

        binding.mainPager.registerOnPageChangeCallback(viewPagerCallback)
        return binding.root
    }

    private fun markDichotomyAsActive() {
        binding.dichotomy.setTextColor(resources.getColor(R.color.selectedText, null))
        binding.fibonacci.setTextColor(resources.getColor(R.color.unselectedText, null))
        binding.golden.setTextColor(resources.getColor(R.color.unselectedText, null))
    }

    private fun markFibonacciAsActive() {
        binding.dichotomy.setTextColor(resources.getColor(R.color.unselectedText, null))
        binding.fibonacci.setTextColor(resources.getColor(R.color.selectedText, null))
        binding.golden.setTextColor(resources.getColor(R.color.unselectedText, null))
    }

    private fun markGoldenAsActive() {
        binding.dichotomy.setTextColor(resources.getColor(R.color.unselectedText, null))
        binding.fibonacci.setTextColor(resources.getColor(R.color.unselectedText, null))
        binding.golden.setTextColor(resources.getColor(R.color.selectedText, null))
    }

    override fun onDestroyView() {
        binding.mainPager.unregisterOnPageChangeCallback(viewPagerCallback)
        super.onDestroyView()
    }
}