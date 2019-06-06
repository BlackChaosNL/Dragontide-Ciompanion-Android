package com.example.tyil.dtc.Data;

import android.databinding.BaseObservable;

import com.example.tyil.dtc.BR;

public class CampaignCreateData extends BaseObservable{
    private String name;
    private String description;
    private String password;

    public CampaignCreateData() {
        this.name = "";
        this.description = "";
        this.password = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        notifyPropertyChanged(BR.name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

        notifyPropertyChanged(BR.description);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

        notifyPropertyChanged(BR.password);
    }
}
