package net.soulwolf.observable;

/**
 * author: Ewen
 * since : 15/10/16 上午10:54.
 */
public abstract class OnSubscribeImpl<RESULT> implements OnSubscribe<RESULT>{


    @Override
    public void call(SubscriberDelegate<? super RESULT> subscriber) throws Exception {
        try {
            subscriber.onStart();
            RESULT result = execute();
            if(result == null){
                throw new NullPointerException("The executed result is NULL!");
            }
            subscriber.onCompleted(result);
        }catch (Exception e){
            subscriber.onError(e);
        }
    }

    public abstract RESULT execute() throws Exception;
}
