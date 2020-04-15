package com.example.stackexchangeapp.ui.mainscreen.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.stackexchangeapp.R
import com.example.stackexchangeapp.business.dataviewmodel.UserView
import com.example.stackexchangeapp.business.interactor.IStackExchangeInteractor
import com.example.stackexchangeapp.ui.mainscreen.viewmodel.MainScreenViewModel
import io.reactivex.Single
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    @Mock
    private lateinit var interactor: IStackExchangeInteractor

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    private val interactorModule = module {
        single { interactor }
    }

    private val viewModelModule = module {
        viewModel { MainScreenViewModel(get()) }
    }

    @Before
    fun setup() {
        //Stop the actual Koin implementation
        stopKoin()
        //Start Koin with mock interactor
        startKoin {
            modules(listOf(interactorModule, viewModelModule))
        }
    }

    @Test
    fun mainScreenStart_initialViewShown() {
        activityTestRule.launchActivity(null)
        //App name on the toolbar
        onView(withText(R.string.app_name)).check(matches(isDisplayed()))
        //Edit text and its empty value
        onView(withId(R.id.et_main_screen_name)).check(matches(isDisplayed()))
        onView(withId(R.id.et_main_screen_name)).check(matches(withText("")))
        //Button and its title
        onView(withId(R.id.bt_main_screen_search)).check(matches(isDisplayed()))
        onView(withId(R.id.bt_main_screen_search)).check(matches(isClickable()))
        onView(withText(R.string.button_text)).check(matches(isDisplayed()))
    }

    @Test
    fun mainScreenActivityStarted_typeOnEditText_editTextValueChanged() {
        mainScreenStart_initialViewShown()
        //Type some string on the editText
        onView(withId(R.id.et_main_screen_name)).perform(typeText(STRING_TO_TYPE))
        //Check that the text was changed.
        onView(withId(R.id.et_main_screen_name)).check(matches(withText(STRING_TO_TYPE)))
    }

    @Test
    fun mainScreenActivityStarted_editTextValueChanged_resultsShown() {
        `when`(interactor.getUsersByName(name = STRING_TO_TYPE)).thenReturn(
            Single.just(results).delay(500, TimeUnit.MILLISECONDS)
        )
        mainScreenActivityStarted_typeOnEditText_editTextValueChanged()
        // Click on the search button
        onView(withId(R.id.bt_main_screen_search)).perform(click())
        // Check if the progress bar is displayed
        onView(withId(R.id.pb_main_screen_loading)).check(matches(isDisplayed()))
        //Wait for it
        Thread.sleep(500)
        // Check if the results are displayed
        onView(withText(REPUTATION1)).check(matches(isDisplayed()))
        onView(withText(USERNAME1)).check(matches(isDisplayed()))
        onView(withText(REPUTATION2)).check(matches(isDisplayed()))
        onView(withText(USERNAME2)).check(matches(isDisplayed()))
        // Check if the progress bar is gone
        onView(withId(R.id.pb_main_screen_loading)).check(matches(not(isDisplayed())))
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    companion object {
        private const val STRING_TO_TYPE = "Username"
        private const val REPUTATION1 = "12"
        private const val USERNAME1 = "username1"
        private const val REPUTATION2 = "22"
        private const val USERNAME2 = "username2"
        private val results = listOf(
            UserView(REPUTATION1, USERNAME1),
            UserView(REPUTATION2, USERNAME2)
        )
    }
}