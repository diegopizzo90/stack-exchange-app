package com.example.stackexchangeapp.ui.userdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stackexchangeapp.business.interactor.IStackExchangeInteractor
import com.example.stackexchangeapp.ui.userdetails.UserDetailsStateView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserDetailsViewModel(private val interactor: IStackExchangeInteractor) : ViewModel() {

    private val userDetailsStateMutable: MutableLiveData<UserDetailsStateView> = MutableLiveData()
    val userDetailsState: LiveData<UserDetailsStateView> get() = userDetailsStateMutable

    private val disposables = CompositeDisposable()

    fun getUserDetails(userId: Int) {
        disposables.add(interactor.getUserDetailsById(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { userDetailsStateMutable.value = UserDetailsStateView.ShowLoading }
            .doFinally { userDetailsStateMutable.value = UserDetailsStateView.HideLoading }
            .subscribe({
                userDetailsStateMutable.value = UserDetailsStateView.ShowData(it)
            }, {
                userDetailsStateMutable.value = UserDetailsStateView.ShowError
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}