package com.example.stackexchangeapp.business.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.stackexchangeapp.business.dataviewmodel.UserView
import com.example.stackexchangeapp.business.interactor.IStackExchangeInteractor
import com.example.stackexchangeapp.ui.mainscreen.MainScreenStateView
import com.example.stackexchangeapp.ui.mainscreen.viewmodel.MainScreenViewModel
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class MainScreenViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainScreenViewModel

    @Mock
    private lateinit var interactor: IStackExchangeInteractor

    @Mock
    lateinit var observer: Observer<MainScreenStateView>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        viewModel = MainScreenViewModel(interactor).apply {
            mainScreenState.observeForever(observer)
        }
    }

    @Test
    fun getUsersByName_success_successfulViewStateTriggered() {
        //arrange
        `when`(interactor.getUsersByName(name = "user")).thenReturn(Single.just(userList))
        //act
        viewModel.getUsersByName("user")
        //assert
        Mockito.verify(observer).onChanged(MainScreenStateView.ShowLoading)
        Mockito.verify(observer)
            .onChanged(ArgumentMatchers.any(MainScreenStateView.ShowData::class.java))
        Mockito.verify(observer).onChanged(MainScreenStateView.HideLoading)
    }

    @Test
    fun getUsersByName_error_errorViewStateTriggered() {
        //arrange
        `when`(interactor.getUsersByName(name = "user")).thenReturn(Single.error(Throwable()))
        //act
        viewModel.getUsersByName("user")
        //assert
        Mockito.verify(observer).onChanged(MainScreenStateView.ShowLoading)
        Mockito.verify(observer).onChanged(MainScreenStateView.ShowError)
        Mockito.verify(observer).onChanged(MainScreenStateView.HideLoading)
    }

    @Test
    fun getUsersByName_emptyResult_emptyViewStateTriggered() {
        //arrange
        `when`(interactor.getUsersByName(name = "user")).thenReturn(Single.just(listOf()))
        //act
        viewModel.getUsersByName("user")
        //assert
        Mockito.verify(observer).onChanged(MainScreenStateView.ShowLoading)
        Mockito.verify(observer).onChanged(MainScreenStateView.ShowEmptyResult)
        Mockito.verify(observer).onChanged(MainScreenStateView.HideLoading)
    }

    @Test
    fun getUsersByName_usernameEmpty_emptyUsernameViewStateTriggered() {
        //act
        viewModel.getUsersByName("")
        //assert
        Mockito.verify(observer).onChanged(MainScreenStateView.UsernameEmpty)
    }

    companion object {
        private val userList = listOf(
            UserView("12", "username1"),
            UserView("22", "username2")
        )
    }
}