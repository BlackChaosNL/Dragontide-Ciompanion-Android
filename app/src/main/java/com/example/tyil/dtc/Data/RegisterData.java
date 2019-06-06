package com.example.tyil.dtc.Data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.tyil.dtc.BR;

public class RegisterData extends BaseObservable {
    private String email;
    private String username;
    private String password;
    private String password2;

    public RegisterData() {
        this.email = "";
        this.username = "";
        this.password = "";
        this.password2 = "";
    }

    @Bindable
    public String getEmail() {
        return this.email;
    }

    @Bindable
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    @Bindable
    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    @Bindable
    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getPassword2() {
        return password2;
    }

    @Bindable
    public void setPassword2(String password2) {
        this.password2 = password2;
        notifyPropertyChanged(BR.password2);
    }
}
