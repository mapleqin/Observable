package net.soulwolf.observable;

/**
 * author: Ewen
 * since : 15/10/15 下午6:39.
 */
public final class Observable<T> {

    public static <E> Observable<E> create(OnSubscribe<E> subscribe){
        return new Observable<>(subscribe);
    }

    private OnSubscribe<T> mOnSubscribe;
    private SubscriberDelivery<T> mSubscriberDelivery;

    public Observable(OnSubscribe<T> subscribe) {
        this.mOnSubscribe = subscribe;
        this.mSubscriberDelivery = new ExecutorDelivery<>(ThreadComponent.getComponent().getMainHandler());
    }

    public void subscribe(Subscriber<? super T> subscriber){
        SubscriberThread<? super T> subscriberThread =
                new SubscriberThread<>(mOnSubscribe,new SubscriberDelegate<>(mSubscriberDelivery,subscriber));
        execute(subscriberThread);
    }

    public void execute(SubscriberThread<? super T> subscriberThread){
        ThreadComponent.getComponent().execute(subscriberThread);
    }
}
