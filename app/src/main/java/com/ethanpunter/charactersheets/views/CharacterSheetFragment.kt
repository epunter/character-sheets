package com.ethanpunter.charactersheets.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ethanpunter.charactersheets.data.Sheet
import com.ethanpunter.charactersheets.databinding.CharacterSheetBinding

class CharacterSheetFragment : Fragment() {

    lateinit var character: Sheet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CharacterSheetBinding.inflate(inflater)
        binding.character = character
        return binding.root
    }
}