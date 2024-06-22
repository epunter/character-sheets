package com.ethanpunter.charactersheets.stats

import android.content.Context
import android.graphics.Point
import android.text.InputType
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.databinding.AbilityScoreBinding


class AbilityScore(
    override var id: Long = 0L,
    override val position: Point,
    override val editable: Boolean = true,
    statName: String,
    statVal: String,
    val useModifier: Boolean = true
) : BasicStat(id, position, editable, statName, statVal, null) {

    private val tag = AbilityScore::class.java.simpleName

    init {
        calcAdditional()
        this.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (propertyId) {
                    BR.statValue -> calcAdditional()
                }
            }
        })
    }

    private fun calcAdditional() {
        try {
            if (useModifier) {
                statAdditional = createAbilityBonusString(calculateAbilityBonus())
            }
        } catch (e: NumberFormatException) {
            Log.e(tag, "Attempted to calculate modifier from non-integer value ", e)
            statAdditional = null
        }
    }

    private fun calculateAbilityBonus(): Int {
        return (Integer.valueOf(statValue).minus(10)).div(2)
    }

    private fun createAbilityBonusString(bonus: Int): String {
        var bonusString = ""
        if (bonus >= 0) {
            bonusString += "+"
        }
        bonusString += bonus.toString()
        return bonusString
    }

    override fun getView(inflater: LayoutInflater): View {
        val binding = AbilityScoreBinding.inflate(inflater)
        binding.stat = this
        return binding.root
    }

    override fun edit(context: Context) {
        if (editable) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Edit Stat: $statName")

            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            input.text = SpannableStringBuilder(statValue)
            builder.setView(input)

            builder.setPositiveButton(
                "OK"
            ) { _, _ -> statValue = input.text.toString() }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.cancel() }

            builder.show()
        }
    }
}