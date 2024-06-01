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
import com.ethanpunter.charactersheets.stats.BasicText
import kotlin.math.ceil

class CharacterSheetFragment : Fragment() {

    lateinit var character: Character

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CharacterSheetBinding.inflate(inflater)

        var attributesList = character.getAllAttributes()

        if (attributesList[0] is BasicText) {
            attachHeader(attributesList[0].getView(inflater), binding)
            attributesList = attributesList.subList(1, attributesList.size)
        }

        var curRow = attributesList[0].position.y
        val curRowViews = ArrayList<View>()
        for (i in attributesList.indices) {
            val curAttribute = attributesList[i]
            if (curAttribute.position.y != curRow) {
                insertRow(
                    curRowViews, binding, height = curAttribute.customHeight
                        ?: (Resources.getSystem().displayMetrics.widthPixels / curRowViews.size)
                )
                curRowViews.clear()
                curRowViews.add(curAttribute.getView(inflater))
                curRow = curAttribute.position.y
            } else {
                curRowViews.add(curAttribute.getView(inflater))
            }
        }
        insertRow(curRowViews, binding)

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

    private fun insertRow(
        views: List<View>,
        binding: CharacterSheetBinding,
        width: Int = Resources.getSystem().displayMetrics.widthPixels / views.size,
        height: Int = Resources.getSystem().displayMetrics.widthPixels / views.size
    ) {
        val params = LinearLayout.LayoutParams(width, height)
        val rowView = LinearLayout(context)
        rowView.orientation = LinearLayout.HORIZONTAL
        rowView.gravity = Gravity.CENTER
        for (view in views) {
            view.layoutParams = params
            rowView.addView(view)
        }
        binding.characterSheetContainer.addView(rowView)
    }
}