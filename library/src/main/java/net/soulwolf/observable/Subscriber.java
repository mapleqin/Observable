package net.soulwolf.observable;

/**
 * author: Ewen
 * since : 15/10/15 下午6:43.
 */
public interface Subscriber<RESULT> {

    void onStart();

    void onError(Throwable error);

    void onCompleted(RESULT t);

}
