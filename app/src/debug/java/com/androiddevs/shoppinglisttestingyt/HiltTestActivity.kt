package com.androiddevs.shoppinglisttestingyt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // 👇 Apply theme BEFORE super.onCreate
        val theme = intent.getIntExtra(
            "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
            com.google.android.material.R.style.Theme_MaterialComponents_DayNight_NoActionBar
        )
        setTheme(theme)

        super.onCreate(savedInstanceState)
    }
}