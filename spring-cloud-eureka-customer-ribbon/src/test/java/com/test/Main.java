package com.test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Main {
    public static void main(String[] args) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("：first call");
                subscriber.onNext(11);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer q) {
                        System.out.println("：桥接" + q);
                        return ""+ q;
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String value) { // 参数类型 Bitmap
                        System.out.println("value :" + value);
                    }
                });
    }
}
