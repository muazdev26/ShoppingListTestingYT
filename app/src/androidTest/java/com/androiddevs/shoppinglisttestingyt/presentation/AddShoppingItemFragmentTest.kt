package com.androiddevs.shoppinglisttestingyt.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.androiddevs.shoppinglisttestingyt.R
import com.androiddevs.shoppinglisttestingyt.data.FakeShoppingRepository
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValue
import com.androiddevs.shoppinglisttestingyt.launchFragmentInHiltContainer
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.replaceText
import com.androiddevs.shoppinglisttestingyt.data.lcoal.ShoppingItem
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class AddShoppingItemFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory


    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickInsertIntoDb_shoppingItemInsertedIntoDb() {
        val testShoppingViewModel = ShoppingViewModel(FakeShoppingRepository())
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AddShoppingItemFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
            shoppingViewModel = testShoppingViewModel
        }

        onView(withId(R.id.etShoppingItemName))
            .perform(replaceText("Eggs"))

        onView(withId(R.id.etShoppingItemPrice))
            .perform(replaceText("10.0"))
        onView(withId(R.id.etShoppingItemAmount))
            .perform(replaceText("10"))

        onView(withId(R.id.btnAddShoppingItem))
            .perform(click())

        assertThat(testShoppingViewModel.shoppingItems.getOrAwaitValue())
            .contains(ShoppingItem(name = "Eggs", amount = 10, price = 10.0F, imageUrl = ""))
    }

    @Test
    fun pressBackButton_popBackStack() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AddShoppingItemFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()

        verify(navController).popBackStack()
    }

    @Test
    fun clickAddShoppingImageButton_navigateToImagePickFragment() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AddShoppingItemFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.ivShoppingImage)).perform(click())

        verify(navController).navigate(
            AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
        )
    }

    @Test
    fun setCurImageUrl_clearsOnBackPress() {
        val navController = mock(NavController::class.java)
        val testShoppingViewModel = ShoppingViewModel(FakeShoppingRepository())

        launchFragmentInHiltContainer<AddShoppingItemFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
            shoppingViewModel = testShoppingViewModel
        }

        testShoppingViewModel.setCurImageUrl("test")
        pressBack()

        assertThat(testShoppingViewModel.curImageUrl.getOrAwaitValue()).isEqualTo("")
    }
}
