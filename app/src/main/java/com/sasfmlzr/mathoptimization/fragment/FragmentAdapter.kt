package com.sasfmlzr.mathoptimization.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.RuntimeException

class FragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){

    var items = listOf<Int>()

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> FunctionDichotomyFragment()
            1 -> FunctionDichotomyFibonacciFragment()
            2 -> FunctionDichotomyGoldenFragment()
            else -> throw RuntimeException("Fragment $position doesn't exist")
        }
    }
}