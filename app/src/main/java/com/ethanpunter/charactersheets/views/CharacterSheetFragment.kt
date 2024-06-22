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
import com.ethanpunter.charactersheets.data.CharacterSheet
import com.ethanpunter.charactersheets.databinding.CharacterSheetBinding
import com.ethanpunter.charactersheets.stats.TextLine
import com.ethanpunter.charactersheets.stats.Stat

class CharacterSheetFragment : Fragment() {

    lateinit var characterSheet: CharacterSheet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CharacterSheetBinding.inflate(inflater)

        val attributesList = characterSheet.getAllAttributes()

        // If the first attribute is a TextLine only (as will usually be Character Name or similar)
        // Use that as a header, enlarging the size and spanning the whole screen
        if (attributesList[0] is TextLine) {
            val headerView = attributesList[0].getView(inflater)
            headerView.setOnClickListener { attributesList[0].edit(headerView.context) }
            attachHeader(headerView, binding)
            insertAttributes(attributesList.subList(1, attributesList.size), binding, inflater)
        } else {
            insertAttributes(attributesList, binding, inflater)
        }

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

    private fun insertAttributes(
        attributesList: List<Stat>,
        binding: CharacterSheetBinding,
        inflater: LayoutInflater
    ) {
        var curRow = attributesList[0].position.y
        val curRowViews = ArrayList<View>()
        for (i in attributesList.indices) {
            val curAttribute = attributesList[i]
            val curAttributeView = curAttribute.getView(inflater)
            curAttributeView.setOnClickListener { curAttribute.edit(curAttributeView.context) }
            if (curAttribute.position.y != curRow) {
                insertRow(
                    curRowViews, binding, height = curAttribute.customHeight
                        ?: (Resources.getSystem().displayMetrics.widthPixels / curRowViews.size)
                )
                curRowViews.clear()
                curRowViews.add(curAttributeView)
                curRow = curAttribute.position.y
            } else {
                curRowViews.add(curAttributeView)
            }
        }
        insertRow(
            curRowViews,
            binding,
            height = attributesList[attributesList.lastIndex].customHeight
                ?: (Resources.getSystem().displayMetrics.widthPixels / curRowViews.size)
        )
    }

    private fun insertRow(
        views: List<View>,
        binding: CharacterSheetBinding,
        width: Int = Resources.getSystem().displayMetrics.widthPixels / views.size,
        height: Int
    ) {
        val params = LinearLayout.LayoutParams(width, height)
        val rowView = LinearLayout(context)
        rowView.orientation = LinearLayout.HORIZONTAL
        rowView.gravity = Gravity.CENTER or Gravity.TOP
        rowView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        for (view in views) {
            view.layoutParams = params
            rowView.addView(view)
        }

        binding.characterSheetContainer.addView(rowView)
    }
}