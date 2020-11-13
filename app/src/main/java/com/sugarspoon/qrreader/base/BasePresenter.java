package com.sugarspoon.qrreader.base;


public interface BasePresenter<V extends BaseView> {

    void onViewCreated();

    void onViewResumed();

    void detachView();
}
