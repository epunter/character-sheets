package com.ethanpunter.charactersheets.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ethanpunter.charactersheets.viewmodels.MainMenuViewModel
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.data.Sheet
import com.ethanpunter.charactersheets.databinding.CharacterSheetListItemBinding
import java.util.*

class CharacterListAdapter(mainMenuViewModel: MainMenuViewModel, context: Context) :
    RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private val inflater: LayoutInflater

    private val characters = Collections.synchronizedList(mainMenuViewModel.characters)

    init {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mainMenuViewModel.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            @SuppressLint("NotifyDataSetChanged") // shut up
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (propertyId == BR.characters) {
                    notifyDataSetChanged()
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        if (position in characters.indices) {
            holder.bind(characters[position])
        }
    }


    class CharacterViewHolder(private val itemBinding: CharacterSheetListItemBinding) :
        ViewHolder(itemBinding.root) {

            fun bind(character: Sheet) {
                itemBinding.character = character
                itemBinding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): CharacterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterSheetListItemBinding.inflate(layoutInflater, parent, false)

                return CharacterViewHolder(binding)
            }
        }


    }


}