package com.sasfmlzr.mathoptimization.di.core

object Injector {

    lateinit var component: FullApplicationComponent

    fun prepare(application: MainApplication) {
        component = DaggerFullApplicationComponent.builder()
                .applicationModule(ApplicationModule(application))
                .build()
    }

    fun applicationComponent() = component
    fun navigationComponent() = component.navigationComponent()
    fun viewComponent() = component.viewComponent()
}
