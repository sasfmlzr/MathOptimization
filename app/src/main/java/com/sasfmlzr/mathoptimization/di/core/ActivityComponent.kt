package com.sasfmlzr.mathoptimization.di.core

import com.sasfmlzr.mathoptimization.activity.MainActivity
import dagger.Subcomponent
import com.sasfmlzr.mathoptimization.di.base.ActivityScope

@ActivityScope
@Subcomponent (modules = [ActivityVMFactoryModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}
