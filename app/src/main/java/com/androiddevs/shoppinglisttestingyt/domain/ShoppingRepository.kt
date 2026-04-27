package com.androiddevs.shoppinglisttestingyt.domain

import androidx.lifecycle.LiveData
import com.androiddevs.shoppinglisttestingyt.data.lcoal.ShoppingItem
import com.androiddevs.shoppinglisttestingyt.data.remote.dto.ImageResponse
import com.androiddevs.shoppinglisttestingyt.util.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}