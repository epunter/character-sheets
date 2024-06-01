package com.ethanpunter.charactersheets.stats

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.databinding.AbilityScoreBinding

abstract class BasicStat(
    override val position: Int,
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

    override fun getView(inflater: LayoutInflater): View {
        val binding = AbilityScoreBinding.inflate(inflater)
        binding.stat = this
        return binding.root
    }
}