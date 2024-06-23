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
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.databinding.TextBlockBinding
import com.ethanpunter.charactersheets.views.adapters.TextBlockAdapter

class TextBlock(
    override var id: Long = 0L,
    override val position: Point,
    override val editable: Boolean = true,
    label: String,
    value: MutableList<String>
) : Stat() {

    override val customHeight = LinearLayout.LayoutParams.WRAP_CONTENT

    @Bindable
    var textLabel: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textLabel)
        }

    @Bindable
    var textValues: MutableList<String> = ArrayList()
        set(value) {
            field = value
            notifyPropertyChanged(BR.textValues)
        }

    init {
        this.textLabel = label
        this.textValues = value
    }

    override fun getView(inflater: LayoutInflater): View {
        val binding = TextBlockBinding.inflate(inflater)
        val adapter = TextBlockAdapter(this)
        binding.blockText.layoutManager = object:LinearLayoutManager(inflater.context) {
            override fun canScrollVertically() = false
        }
        binding.blockText.adapter = adapter
        binding.stat = this
        return binding.root
    }

    override fun edit(context: Context) {
        if (editable) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Edit Stat: $textLabel")

            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton(
                "OK"
            ) { _, _ ->
                textValues.add(input.text.toString())
                notifyPropertyChanged(BR.textValues)
                notifyListeners()
            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.cancel() }

            builder.show()
        }
    }

    fun editEntry(context: Context, pos: Int) {
        if (editable) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Edit Stat: $textLabel")

            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_TEXT
            input.text = SpannableStringBuilder(textValues[pos])
            builder.setView(input)

            builder.setPositiveButton(
                "OK"
            ) { _, _ ->
                textValues[pos] = input.text.toString()
                notifyPropertyChanged(BR.textValues)
                notifyListeners()
            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.cancel() }

            builder.show()
        }
    }

    fun deleteEntry(pos: Int) {
        if (editable) {
            textValues.removeAt(pos)
            notifyPropertyChanged(BR.textValues)
            notifyListeners()
        }
    }
}