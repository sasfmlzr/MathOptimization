package com.sasfmlzr.mathoptimization.fragment

import android.os.Bundle
import android.text.InputType
import android.text.InputType.TYPE_NUMBER_FLAG_SIGNED
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.lifecycle.lifecycleScope
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseFragment
import com.sasfmlzr.mathoptimization.architecture.OnSwipeTouchListener
import com.sasfmlzr.mathoptimization.databinding.FragmentDihotomyBinding
import com.sasfmlzr.mathoptimization.databinding.FragmentFunctionDichotomyBinding
import com.sasfmlzr.mathoptimization.di.core.FragmentComponent
import com.sasfmlzr.mathoptimization.di.core.Injector
import kotlinx.android.synthetic.main.fragment_function.*
import kotlinx.android.synthetic.main.fragment_function_dichotomy.*
import kotlinx.android.synthetic.main.view_edit_text.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//TEMP CLASS
class FunctionDichotomyDFragment : BaseFragment<FunctionDichotomyFragmentVM,
        FragmentFunctionDichotomyBinding>(FunctionDichotomyFragmentVM::class) {

    private lateinit var animationJob: Job

    override fun getLayoutId() = R.layout.fragment_function_dichotomy

    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel

        binding.intervalMin.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER + TYPE_NUMBER_FLAG_SIGNED)
        binding.intervalMax.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER + TYPE_NUMBER_FLAG_SIGNED)
        binding.epsilum.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER + TYPE_NUMBER_FLAG_SIGNED)
        binding.ldop.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER + TYPE_NUMBER_FLAG_SIGNED)

        showAnimation()
        childFragmentManager.setupForAccessibility()
        binding.scroll.setOnTouchListener(specificGestureListener)
        return binding.root
    }

    private fun sendData() {
        try {
            val function = binding.enterFunction.edit_text.text.toString()
            val intervalMin = binding.intervalMin.text.toString().toDouble()
            val intervalMax = binding.intervalMax.text.toString().toDouble()
            val epsilon = binding.epsilum.text.toString().toDouble()
            val ldop = binding.ldop.text.toString().toDouble()
            val isMax = binding.maxx.isChecked



            childFragmentManager.beginTransaction()
                .replace(
                   binding.root.id,
                    DihotomyFragment.newInstance(
                        function,
                        intervalMin,
                        intervalMax,
                        epsilon,
                        ldop,
                        isMax
                    ),
                    null
                )
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit()
        } catch (e: Exception) {

        }
    }

    private fun FragmentManager.setupForAccessibility() {
        addOnBackStackChangedListener {
            val lastFragmentWithView = parentFragmentManager.fragments.last { it.isVisible }

            for (fragment in parentFragmentManager.fragments) {
                if (fragments.size!=0 && fragment == lastFragmentWithView) {
                    if(fragment is FunctionDichotomyDFragment) {
                        fragment.container?.visibility = View.GONE
                    }
                } else {
                    if(fragment is FunctionDichotomyDFragment) {
                        fragment.container?.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun showAnimation() {
        binding.arrowStart.visibility = View.VISIBLE
        binding.arrowMiddle.visibility = View.VISIBLE
        binding.arrowEnd.visibility = View.VISIBLE

        animationJob = lifecycleScope.launch(Dispatchers.Main + Job()) {
            while (true) {
                binding.arrowStart.alpha = 0.5f
                binding.arrowMiddle.alpha = 0.5f
                binding.arrowEnd.alpha = 0.5f
                delay(200)
                binding.arrowStart.alpha = 0.8f
                binding.arrowMiddle.alpha = 0.5f
                binding.arrowEnd.alpha = 0.5f
                delay(200)
                binding.arrowStart.alpha = 0.5f
                binding.arrowMiddle.alpha = 0.8f
                binding.arrowEnd.alpha = 0.5f
                delay(200)
                binding.arrowStart.alpha = 0.5f
                binding.arrowMiddle.alpha = 0.5f
                binding.arrowEnd.alpha = 0.8f
                delay(400)
            }
        }
    }

    private fun hideAnimation() {
        binding.arrowStart.visibility = View.GONE
        binding.arrowMiddle.visibility = View.GONE
        binding.arrowEnd.visibility = View.GONE
    }

    private val specificGestureListener by lazy {
        object : OnSwipeTouchListener(context!!) {
            override fun onSwipeTop() {
                hideAnimation()
                sendData()
            }
        }
    }
}