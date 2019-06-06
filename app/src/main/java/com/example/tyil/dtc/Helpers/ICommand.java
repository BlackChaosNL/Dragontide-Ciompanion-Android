package com.example.tyil.dtc.Helpers;

public interface ICommand<T> {
    void Success(T object);
}
