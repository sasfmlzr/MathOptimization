package com.sasfmlzr.mathoptimization.di.core

import android.app.Application
import android.content.Context
import com.sasfmlzr.mathoptimization.architecture.AndroidLogger
import com.sasfmlzr.mathoptimization.architecture.Logger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MainApplication) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application = application

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    internal fun logger(androidLogger: AndroidLogger): Logger = androidLogger
}
