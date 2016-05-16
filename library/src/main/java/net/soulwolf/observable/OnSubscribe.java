package net.soulwolf.observable;

/**
 * author: Ewen
 * since : 15/10/15 下午6:40.
 */
public interface OnSubscribe<RESULT> {

    void call(SubscriberDelegate<? super RESULT> subscriber) throws Exception;

}
