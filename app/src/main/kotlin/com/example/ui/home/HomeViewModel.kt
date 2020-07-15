package com.example.ui.home

import com.example.ui.base.BaseViewModel
import io.reactivex.subjects.PublishSubject

class HomeViewModel : BaseViewModel() {

    enum class ContextEvent {
        BUTTON_CLICKED,
    }
    val contextEventBus: PublishSubject<ContextEvent> = PublishSubject.create()

    fun onBtnClick() {
        contextEventBus.onNext(ContextEvent.BUTTON_CLICKED)
    }
}
