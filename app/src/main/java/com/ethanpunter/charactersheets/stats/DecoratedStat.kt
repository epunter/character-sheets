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
import androidx.appcompat.content.res.AppCompatResources
import com.ethanpunter.charactersheets.databinding.DecoratedStatBinding

class DecoratedStat(
    override var id: Long = 0L,
    override val position: Point,
    override val editable: Boolean = true,
    name: String,
    value: String,
    val decoration: Int
) : BasicStat(id, position, editable, name, value, null) {

    override val customHeight: Int = LinearLayout.LayoutParams.WRAP_CONTENT

    override fun getView(inflater: LayoutInflater): View {
        val binding = DecoratedStatBinding.inflate(inflater)
        val decorationDrawable = AppCompatResources.getDrawable(binding.root.context, decoration)
        binding.statModifierContainer.background = decorationDrawable
        binding.stat = this

        return binding.root
    }
}