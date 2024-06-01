package com.ethanpunter.charactersheets.stats

class AbilityScore(
    override val position: Int,
    statName: String,
    private val statVal: Int,
    useModifier: Boolean = true
) : BasicStat(position, statName, statVal.toString(), null) {

    init {
        if (useModifier) {
            statAdditional = createAbilityBonusString(calculateAbilityBonus())
        }
    }

    private fun calculateAbilityBonus(): Int {
        return (statVal - 10) / 2
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