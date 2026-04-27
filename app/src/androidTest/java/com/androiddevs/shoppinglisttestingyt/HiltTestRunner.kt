package com.androiddevs.shoppinglisttestingyt

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.androiddevs.shoppinglisttestingyt.di.ShoppingApplication

class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(
            cl,
            "dagger.hilt.android.testing.HiltTestApplication",
            context
        )
    }
}