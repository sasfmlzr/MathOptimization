package com.sasfmlzr.mathoptimization.matchparserrun

import com.sasfmlzr.mathoptimization.matchParser.MatchParser
import com.sasfmlzr.mathoptimization.matchParser.MatchParserOld

/**
 * Created by sasfm on 25.09.2016.
 */
object MatchParserRun {
    @JvmStatic
    fun main(args: Array<String>) {
        val formulas = arrayOf(
            "2+2*2",
            "2+X*2",
            "sin(90)+4-cos(0)",
            "2--4",
            "2**3*5-----7",
            "3.5.6-2",
            "",
            "y",
            "-2+1",
            "(-1)-1",
            "-X-1",
            "(-X)-1",
            "(-X-1",
            "-X)-1",
            "-1-(-X)",
            "-1--X",
            "X-1"
        )

        val p = MatchParser()
        p.setVariable("X", -1.0)
        println("------------New parser started-----------")
        for (formula in formulas) {
            try {
                println(formula + "=" + p.parse(formula))
            } catch (e: Exception) {
                System.err.println("Error while parsing '" + formula + "' with message: " + e.message)
            }
        }
        println("------------New parser finished-----------")

        val oldp = MatchParserOld()
        oldp.setVariable("X", -1.0)
        println("------------Old parser started-----------")
        for (formula in formulas) {
            try {
                println(formula + "=" + oldp.parse(formula))
            } catch (e: Exception) {
                System.err.println("Error while parsing '" + formula + "' with message: " + e.message)
            }
        }
        println("------------Old parser finished-----------")
    }
}