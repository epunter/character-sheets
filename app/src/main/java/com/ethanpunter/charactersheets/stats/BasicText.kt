package com.ethanpunter.charactersheets.stats

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.databinding.LabelledTextBinding

class BasicText(override val position: Int, label: String, value: String) : Stat() {

    @Bindable
    var textLabel: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textLabel)
        }

    @Bindable
    var textValue: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textValue)
        }

    init {
        this.textLabel = label
        this.textValue = value
    }

    override fun getView(inflater: LayoutInflater): View {
        val binding = LabelledTextBinding.inflate(inflater)
        binding.stat = this
        return binding.root
    }
}