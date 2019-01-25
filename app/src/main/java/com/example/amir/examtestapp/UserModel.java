package com.example.amir.examtestapp;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    private String[] greating ={"Hi!","Hey!","Wats up!","Hellooooooo!","Hey there"};
    private int index=0;
    public final MutableLiveData<String> liveData = new MutableLiveData<>();

    public UserModel() {
        // trigger user load.
    }

    void doAction() {
        if(index<5){
            liveData.setValue(greating[index++]);
        }else {
            index=0;
            liveData.setValue(greating[index++]);
        }

    }
}
