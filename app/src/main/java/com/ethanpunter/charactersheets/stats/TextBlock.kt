package com.ethanpunter.charactersheets.stats

import android.content.Context
import android.graphics.Point
import android.text.InputType
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.databinding.Bindable
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.databinding.TextBlockBinding

class TextBlock(
    override var id: Long = 0L,
    override val position: Point,
    override val editable: Boolean = true,
    label: String,
    value: String
) : Stat() {

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
        val binding = TextBlockBinding.inflate(inflater)
        binding.stat = this
        return binding.root
    }

    override fun edit(context: Context) {
        if (editable) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Edit Stat: $textLabel")

            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_TEXT
            input.text = SpannableStringBuilder(textValue)
            builder.setView(input)

            builder.setPositiveButton(
                "OK"
            ) { _, _ ->
                textValue = input.text.toString()
                notifyListeners()
            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.cancel() }

            builder.show()
        }
    }
}