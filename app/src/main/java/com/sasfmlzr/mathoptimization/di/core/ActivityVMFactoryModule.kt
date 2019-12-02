package com.sasfmlzr.mathoptimization.di.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sasfmlzr.mathoptimization.activity.MainActivityVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.sasfmlzr.mathoptimization.architecture.AndroidViewModelFactory
import com.sasfmlzr.mathoptimization.architecture.ViewModelKey
import com.sasfmlzr.mathoptimization.di.base.ActivityScope

@Module
internal abstract class ActivityVMFactoryModule {
    @Binds
    @ActivityScope
    internal abstract fun bindViewModelAndroidFactory(factory: AndroidViewModelFactory):
            ViewModelProvider.AndroidViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    internal abstract fun bindVM(VM: MainActivityVM): ViewModel
}
