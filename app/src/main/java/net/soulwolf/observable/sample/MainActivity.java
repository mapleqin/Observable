package net.soulwolf.observable.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.soulwolf.observable.Observable;
import net.soulwolf.observable.OnSubscribe;
import net.soulwolf.observable.OnSubscribeCompat;
import net.soulwolf.observable.OnSubscribeImpl;
import net.soulwolf.observable.Subscriber;
import net.soulwolf.observable.SubscriberDelegate;
import net.soulwolf.observable.SubscriberHandler;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.create(new OnSubscribe<String>() {
            @Override
            public void call(SubscriberDelegate<? super String> subscriber) throws Exception {
                Log.i(TAG,"The current thread name[" + Thread.currentThread().getName() + "].");
                try{
                    subscriber.onStart();
                    subscriber.onCompleted("Success!");
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            public void onError(Throwable error) {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            public void onCompleted(String t) {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }
        });

        // simple
        Observable.create(new OnSubscribeImpl<String>() {
            @Override
            public String execute() throws Exception {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
                return "Success";
            }
        }).subscribe(new SubscriberHandler<String>() {
            @Override
            public void onSuccess(String response) throws Exception {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            public void onFailure(Throwable error) {
                super.onFailure(error);
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            protected void onFinally(String s) {
                super.onFinally(s);
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }
        });
        // simple2
        Observable.create(new OnSubscribeCompat<String>("xx","sss") {
            @Override
            public String execute() throws Exception {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
                String params1 = getParams(0);
                String params2 = getParams(1);
                return params1 + ":" + params2;
            }
        }).subscribe(new SubscriberHandler<String>() {

            @Override
            public void onStart() {
                super.onStart();
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            public void onSuccess(String response) throws Exception {
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            public void onFailure(Throwable error) {
                super.onFailure(error);
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }

            @Override
            protected void onFinally(String s) {
                super.onFinally(s);
                Log.i(TAG, "The current thread name[" + Thread.currentThread().getName() + "].");
            }
        });
    }
}
