package com.example.tyil.dtc.Data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.tyil.dtc.BR;

public class CampaignData extends BaseObservable {
    private Integer id;
    private String name;
    private String description;

    @Bindable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

        notifyPropertyChanged(BR.description);
    }
}
