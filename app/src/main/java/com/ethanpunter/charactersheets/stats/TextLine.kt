package com.ethanpunter.charactersheets.stats

import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.databinding.LabelledTextBinding

class TextLine(override val position: Point, label: String, value: String) : Stat() {

    override val customHeight = LinearLayout.LayoutParams.WRAP_CONTENT

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