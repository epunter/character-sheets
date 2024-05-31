package com.ethanpunter.charactersheets.stats

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.BR

class PrimaryStat(statName: String, statValue: Int) :
    BaseObservable() {

    @Bindable
    var statName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.statName)
        }

    @Bindable
    var statValue: Int = 0
        set(value) {
            field = value
            statBonus = calcStatBonus()
            notifyPropertyChanged(BR.statValue)
        }

    @Bindable
    var statBonus: Int = 0
        private set(value) {
            field = value
            statBonusString = createStatBonusString(field)
            notifyPropertyChanged(BR.statBonus)
        }

    @Bindable
    var statBonusString: String = ""
        private set(value) {
            field = value
            notifyPropertyChanged(BR.statBonusString)
        }

    init {
        this.statName = statName
        this.statValue = statValue
    }

    private fun calcStatBonus(): Int {
        return (statValue - 10) / 2
    }

    private fun createStatBonusString(bonus: Int): String {
        var bonusString = ""
        bonusString += if (bonus >= 0) {
            "+"
        } else {
            "-"
        }
        bonusString += bonus.toString()
        return bonusString
    }

}