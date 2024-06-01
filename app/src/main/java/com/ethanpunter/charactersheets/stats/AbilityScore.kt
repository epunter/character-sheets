package com.ethanpunter.charactersheets.stats

import android.graphics.Point
import android.util.Log

class AbilityScore(
    override val position: Point,
    statName: String,
    private val statVal: String,
    useModifier: Boolean = true
) : BasicStat(position, statName, statVal, null) {

    private val tag = AbilityScore::class.java.simpleName

    init {
        try {
            if (useModifier) {
                statAdditional = createAbilityBonusString(calculateAbilityBonus())
            }
        } catch (e: NumberFormatException) {
            Log.e(tag, "Attempted to calculate modifier from non-integer value ", e)
        }
    }

    private fun calculateAbilityBonus(): Int {
        return (Integer.valueOf(statVal).minus(10)).div(2)
    }

    private fun createAbilityBonusString(bonus: Int): String {
        var bonusString = ""
        if (bonus >= 0) {
            bonusString += "+"
        }
        bonusString += bonus.toString()
        return bonusString
    }
}