package com.mmdteam.mgithub.base;

public interface BasePresenter<T> {

    void tackView(T view);

    void dropView();
}
