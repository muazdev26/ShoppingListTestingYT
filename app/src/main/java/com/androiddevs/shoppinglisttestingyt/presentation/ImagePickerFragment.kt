package com.androiddevs.shoppinglisttestingyt.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.androiddevs.shoppinglisttestingyt.R
import kotlin.getValue

class ImagePickerFragment : Fragment(R.layout.fragment_image_picker){
    private val shoppingViewModel: ShoppingViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}