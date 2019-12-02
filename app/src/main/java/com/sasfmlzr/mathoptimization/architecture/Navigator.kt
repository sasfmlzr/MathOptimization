package com.sasfmlzr.mathoptimization.architecture

import androidx.navigation.NavController
import androidx.navigation.NavDirections

class Navigator(private val router: NavController,
                private val logger: Logger
) {

    fun navigateStartToMovieFragment() {
      //  val direction = StartFragmentDirections.actionShowMovieFragment()
      //  navigate(direction)
    }


    
    private fun navigate(direction: NavDirections) {
        try {
            logger.d(router.currentDestination.toString())
            router.navigate(direction)
        } catch (e: RuntimeException) {
            logger.e("Already attached")
        }
    }
}
