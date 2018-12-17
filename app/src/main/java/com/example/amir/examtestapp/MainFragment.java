package com.example.amir.examtestapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MainFragment extends Fragment {

    private String tag="life cycle method";

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.i(tag,"fragment on attach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(tag,"fragment on create");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(tag,"fragment on createView");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(tag,"fragment on activity created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(tag,"fragment on start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(tag,"fragment on resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(tag,"fragment on pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(tag,"fragment on stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(tag,"fragment on destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(tag,"fragment on destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(tag,"fragment on detach");

    }

}
