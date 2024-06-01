package com.ethanpunter.charactersheets.views

import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ethanpunter.charactersheets.R
import com.ethanpunter.charactersheets.data.Character
import com.ethanpunter.charactersheets.databinding.CharacterSheetBinding
import kotlin.math.ceil

const val TEXT_COL_COUNT = 2
const val ABILITY_COL_COUNT = 3

class CharacterSheetFragment : Fragment() {

    lateinit var character: Character

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CharacterSheetBinding.inflate(inflater)

        val attributesList = character.getAllAttributes(inflater)
        attachHeader(attributesList[0], binding)
        attachViews(
            attributesList.subList(1, attributesList.size),
            binding,
            TEXT_COL_COUNT,
            height = LinearLayout.LayoutParams.WRAP_CONTENT
        )
        attachViews(
            views = character.abilityScores.map { it.getView(inflater) },
            binding = binding,
            colCount = ABILITY_COL_COUNT,
            height = ((Resources.getSystem().displayMetrics.widthPixels / ABILITY_COL_COUNT) * 1.25).toInt()
        )

        return binding.root
    }

    private fun attachHeader(header: View, binding: CharacterSheetBinding) {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val txtParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            200
        )
        header.layoutParams = params
        val headerView = header.findViewById<TextView>(R.id.txtEntry)
        headerView.layoutParams = txtParams
        headerView.typeface = resources.getFont(R.font.draconis)

        binding.characterSheetContainer.addView(header)

    }

    private fun attachViews(
        views: List<View>,
        binding: CharacterSheetBinding,
        colCount: Int,
        width: Int = Resources.getSystem().displayMetrics.widthPixels / colCount,
        height: Int = (Resources.getSystem().displayMetrics.widthPixels / colCount)
    ) {
        val rows = ceil(views.size / (colCount.toFloat())).toInt()

        val params = LinearLayout.LayoutParams(
            width,
            height
        )

        for (i in 0 until rows) {
            val rowView = LinearLayout(context)
            rowView.orientation = LinearLayout.HORIZONTAL
            rowView.gravity = Gravity.CENTER
            for (j in i * colCount until (i * colCount) + colCount) {
                try {
                    val view = views[j]
                    view.layoutParams = params
                    rowView.addView(view)
                } catch (e: IndexOutOfBoundsException) {
                    // do nothing
                }
            }
            binding.characterSheetContainer.addView(rowView)
        }
    }
}