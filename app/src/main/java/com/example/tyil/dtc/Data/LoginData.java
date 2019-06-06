package com.example.tyil.dtc.Data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.tyil.dtc.BR;

public class LoginData extends BaseObservable {
    private String email;
    private String username;
    private String password;

    public LoginData(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public LoginData(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public LoginData() {
        this.email = "";
        this.password = "";
    }

    @Bindable
    public String getEmail() {
        return this.email;
    }

    @Bindable
    public String getPassword() {
        return this.password;
    }

    @Bindable
    public String getUsername() { return this.username; }

    @Bindable
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }
}
