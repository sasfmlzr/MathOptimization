package com.sasfmlzr.mathoptimization.di.core

import dagger.Subcomponent
import com.sasfmlzr.mathoptimization.di.base.FragmentScope
import com.sasfmlzr.mathoptimization.fragment.*

@FragmentScope
@Subcomponent(modules = [FragmentModule::class, FragmentVMFactoryModule::class])
interface FragmentComponent {
    fun inject(fragment: DihotomyFragment)
    fun inject(fragment: FibonachiFragment)
    fun inject(fragment: FunctionFragment)
    fun inject(fragment: FunctionDichotomyFragment)
    fun inject(fragment: UnimodalFragment)
    fun inject(fragment: ZolotoFragment)
    fun inject(fragment: FunctionFragmentOld)

}
