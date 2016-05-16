package net.soulwolf.observable;

/**
 * author: Ewen
 * since : 15/10/15 下午6:42.
 */
public class SubscriberDelegate<RESULT> {

    private final SubscriberDelivery<RESULT> mSubscriberDelivery;
    private final Subscriber<? super RESULT> mSubscriber;

    public SubscriberDelegate(SubscriberDelivery<RESULT> delivery,
                              Subscriber<? super RESULT> subscriber){
        this.mSubscriberDelivery = delivery;
        this.mSubscriber = subscriber;
    }

    public void onStart(){
        mSubscriberDelivery.postStart(mSubscriber);
    }

    public void onCompleted(RESULT t){
        mSubscriberDelivery.postSuccess(t,mSubscriber);
    }

    public void onError(Throwable error){
        mSubscriberDelivery.postError(error,mSubscriber);
    }
}
