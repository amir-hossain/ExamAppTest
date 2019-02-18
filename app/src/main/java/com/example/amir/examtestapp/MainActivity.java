package com.example.amir.examtestapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        observable from one object
        Observable<String> observable=Observable.just("Hello");

        Observer observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                showMessage(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

//        subscribe to observable
//        observable.subscribe(observer);

//        Consumer<String> myAction = new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                showMessage(s);
//            }
//        };

        Observable<Integer> arrayObserbable=Observable.fromArray(new Integer[]{1,2,3,4});

        arrayObserbable
                .skip(2)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer*integer;
                    }
                })
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer s) throws Exception {
                showMessage(""+s);
            }
        })
        ;
    }

    private void showMessage(String s) {
        Snackbar.make(findViewById(R.id.root),s,Snackbar.LENGTH_INDEFINITE).show();

    }
}
