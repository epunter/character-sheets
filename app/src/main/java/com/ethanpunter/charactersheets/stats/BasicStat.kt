package com.ethanpunter.charactersheets.stats

import android.content.Context
import android.graphics.Point
import android.text.InputType
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.databinding.AbilityScoreBinding

abstract class BasicStat(
    override var id: Long = 0L,
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

    override fun edit(context: Context) {
        if (editable) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Edit Stat: $statName")

            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED
            input.text = SpannableStringBuilder(statValue)
            builder.setView(input)

            builder.setPositiveButton(
                "OK"
            ) { _, _ ->
                statValue = input.text.toString()
                notifyListeners()
            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.cancel() }

            builder.show()
        }
    }
}