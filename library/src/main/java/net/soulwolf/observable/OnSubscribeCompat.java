/*
 * Copyright (C) 2015 Leholady(乐活女人) Inc. All rights reserved.
 */
package net.soulwolf.observable;

/**
 * author: EwenQin
 * since : 16/6/1 下午2:01.
 */
public abstract class OnSubscribeCompat<RESULT> extends OnSubscribeImpl<RESULT> {

    private Object[] mParams;

    public OnSubscribeCompat(){
        this(new Object[0]);
    }

    public OnSubscribeCompat(Object ... args){
        this.mParams = args;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getParams(int index){
        return (T) mParams[index];
    }
}
