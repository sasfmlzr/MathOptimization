package com.sasfmlzr.mathoptimization.di.core

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class
])
interface FullApplicationComponent {
    fun viewComponent(): FragmentComponent
    fun navigationComponent(): ActivityComponent
    fun inject(mainApplication: MainApplication)
}
