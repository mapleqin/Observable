package net.soulwolf.observable;

/**
 * author: Ewen
 * since : 15/10/15 下午6:45.
 */
interface SubscriberDelivery<RESULT> {

    void postSuccess(RESULT t, Subscriber<? super RESULT> subscriber);

    void postStart(Subscriber<? super RESULT> subscriber);

    void postError(Throwable error, Subscriber<? super RESULT> subscriber);

}
