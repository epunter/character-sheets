package com.ethanpunter.charactersheets.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ethanpunter.charactersheets.viewmodels.MainMenuViewModel
import com.ethanpunter.charactersheets.BR
import com.ethanpunter.charactersheets.data.CharacterSheet
import com.ethanpunter.charactersheets.databinding.CharacterSheetListItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class CharacterListAdapter(private val mainMenuViewModel: MainMenuViewModel, context: Context) :
    RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private val inflater: LayoutInflater

    private var characters = ArrayList(mainMenuViewModel.characterSheets)

    init {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mainMenuViewModel.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            @SuppressLint("NotifyDataSetChanged") // shut up
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (propertyId == BR.characterSheets) {
                    characters = ArrayList(mainMenuViewModel.characterSheets)
                    notifyDataSetChanged()
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.from(parent, mainMenuViewModel)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        if (position in characters.indices) {
            holder.bind(characters[position])
        }
    }


    class CharacterViewHolder(
        private val itemBinding: CharacterSheetListItemBinding,
        private val mainMenuViewModel: MainMenuViewModel
    ) :
        ViewHolder(itemBinding.root) {

        fun bind(characterSheet: CharacterSheet) {
            itemBinding.character = characterSheet
            itemBinding.root.setOnClickListener { mainMenuViewModel.openCharacter(characterSheet) }
            itemBinding.btnDeleteCharSheet.setOnClickListener {
                CoroutineScope(IO).launch {
                    mainMenuViewModel.deleteCharacter(
                        characterSheet
                    )
                }
            }
            itemBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, mainMenuViewModel: MainMenuViewModel): CharacterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterSheetListItemBinding.inflate(layoutInflater, parent, false)
                return CharacterViewHolder(binding, mainMenuViewModel)
            }
        }


    }


}