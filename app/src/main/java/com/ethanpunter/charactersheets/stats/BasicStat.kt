package com.ethanpunter.charactersheets.stats

import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.databinding.AbilityScoreBinding

abstract class BasicStat(
    override val position: Point,
    override val editable: Boolean = true,
    statName: String,
    statValue: String,
    statAdditionalValue: String?
) : Stat() {

    @Bindable
    var statName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.statName)
        }

    @Bindable
    var statValue: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.statValue)
        }

    @Bindable
    var statAdditional: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.statAdditional)
        }

    init {
        this.statName = statName
        this.statValue = statValue
        this.statAdditional = statAdditionalValue
    }
}