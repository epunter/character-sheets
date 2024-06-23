package com.ethanpunter.charactersheets.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.recyclerview.widget.RecyclerView
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.databinding.TextBlockItemBinding
import com.ethanpunter.charactersheets.stats.TextBlock
import java.util.*

class TextBlockAdapter(private val textBlock: TextBlock) :
    RecyclerView.Adapter<TextBlockAdapter.TextBlockViewHolder>() {

    private var textValues = Collections.synchronizedList(textBlock.textValues)

    init {
        textBlock.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            @SuppressLint("NotifyDataSetChanged") // shut up
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (propertyId == BR.textValues) {
                    notifyDataSetChanged()
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextBlockViewHolder {
        return TextBlockViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return textValues.size
    }

    override fun onBindViewHolder(holder: TextBlockViewHolder, position: Int) {
        if (position in textValues.indices) {
            holder.bind(textBlock, position)
        }
    }

    class TextBlockViewHolder(
        private val itemBinding: TextBlockItemBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(textBlock: TextBlock, pos: Int) {
            itemBinding.textItem.text = textBlock.textValues[pos]
            itemBinding.root.setOnClickListener { textBlock.editEntry(itemBinding.root.context, pos) }
            itemBinding.btnDeleteTextItem.visibility = if (textBlock.editable) View.VISIBLE else View.GONE
            itemBinding.btnDeleteTextItem.setOnClickListener { textBlock.deleteEntry(pos) }
            itemBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TextBlockViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TextBlockItemBinding.inflate(layoutInflater, parent, false)
                return TextBlockViewHolder(binding)
            }
        }
    }
}