package com.sasfmlzr.mathoptimization.di.core

import com.sasfmlzr.mathoptimization.di.base.FragmentScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.channels.BroadcastChannel

@Module
class FragmentModule {
    @Provides
    @FragmentScope
    internal fun broadcastChannel() = BroadcastChannel<PresenterModel>(1)
}
