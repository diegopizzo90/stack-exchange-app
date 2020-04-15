package com.example.stackexchangeapp.ui.mainscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stackexchangeapp.business.interactor.IStackExchangeInteractor
import com.example.stackexchangeapp.ui.mainscreen.MainScreenStateView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainScreenViewModel(private val interactor: IStackExchangeInteractor) : ViewModel() {

    private val mainScreenStateMutable: MutableLiveData<MainScreenStateView> = MutableLiveData()
    val mainScreenState: LiveData<MainScreenStateView> get() = mainScreenStateMutable

    private val disposables = CompositeDisposable()

    fun getUsersByName(nameToSearch: String) {
        when {
            nameToSearch.isEmpty() -> {
                mainScreenStateMutable.value = MainScreenStateView.UsernameEmpty
            }
            else -> {
                retrieveUsersByName(nameToSearch)
            }
        }
    }

    private fun retrieveUsersByName(nameToSearch: String) {
        disposables.add(interactor.getUsersByName(name = nameToSearch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mainScreenStateMutable.value = MainScreenStateView.ShowLoading }
            .doFinally { mainScreenStateMutable.value = MainScreenStateView.HideLoading }
            .subscribe({
                if (it.isEmpty()) {
                    mainScreenStateMutable.value = MainScreenStateView.ShowEmptyResult
                } else {
                    mainScreenStateMutable.value = MainScreenStateView.ShowData(it)
                }
            }, {
                mainScreenStateMutable.value = MainScreenStateView.ShowError
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}