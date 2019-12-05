package com.sasfmlzr.mathoptimization.activity

import android.os.Bundle
import androidx.navigation.Navigation
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.architecture.BaseActivity
import com.sasfmlzr.mathoptimization.databinding.ActivityMainBinding
import com.sasfmlzr.mathoptimization.di.core.ActivityComponent

class MainActivity(override val layoutId: Int = R.layout.activity_main) :
    BaseActivity<MainActivityVM, ActivityMainBinding>(MainActivityVM::class) {

    override fun inject(component: ActivityComponent) = component.inject(this)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
}
