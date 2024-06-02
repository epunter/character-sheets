package com.ethanpunter.charactersheets.stats

import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import com.ethanpunter.charactersheets.databinding.DecoratedStatBinding

class DecoratedStat(position: Point, name: String, value: String, private val decoration: Int) :
    BasicStat(position, name, value, null) {

    override val customHeight: Int = LinearLayout.LayoutParams.WRAP_CONTENT

    override fun getView(inflater: LayoutInflater): View {
        val binding = DecoratedStatBinding.inflate(inflater)
        val decorationDrawable = AppCompatResources.getDrawable(binding.root.context, decoration)
        binding.statModifierContainer.background = decorationDrawable
        binding.stat = this

        return binding.root
    }


}