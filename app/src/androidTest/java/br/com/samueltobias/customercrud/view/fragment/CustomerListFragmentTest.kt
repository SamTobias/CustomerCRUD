package br.com.samueltobias.customercrud.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.samueltobias.customercrud.R
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CustomerListFragmentTest {

    @Before
    fun init() {

    }

    @Test
    fun shouldNavigateToFormFragment() { // Create a mock NavController
        val mockNavController = mockk<NavController>()

        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<CustomerListFragment>()

        // Set the NavController property on the fragment
        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        // Verify that performing a click prompts the correct Navigation action
        onView(ViewMatchers.withId(R.id.customer_list_fab_add)).perform(ViewActions.click())
        verify {mockNavController.navigate(R.id.action_customerListFragment_to_customerFormFragment)}
    }
}