package com.sasfmlzr.mathoptimization.di.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import com.sasfmlzr.mathoptimization.architecture.AndroidViewModelFactory
import com.sasfmlzr.mathoptimization.architecture.ViewModelKey
import com.sasfmlzr.mathoptimization.di.base.FragmentScope
import com.sasfmlzr.mathoptimization.fragment.*
import dagger.multibindings.IntoMap

@Module
internal abstract class FragmentVMFactoryModule {
    @Binds
    @FragmentScope
    internal abstract fun bindViewModelAndroidFactory(factory: AndroidViewModelFactory):
            ViewModelProvider.AndroidViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(DihotomyFragmentVM::class)
    internal abstract fun bindDihotomyVM(VM: DihotomyFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FibonachiFragmentVM::class)
    internal abstract fun bindFibonachiVM(VM: FibonachiFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FunctionFragmentVM::class)
    internal abstract fun bindFunctionVM(VM: FunctionFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnimodalFragmentVM::class)
    internal abstract fun bindUnimodalVM(VM: UnimodalFragmentVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ZolotoFragmentVM::class)
    internal abstract fun bindZolotoVM(VM: ZolotoFragmentVM): ViewModel
}
