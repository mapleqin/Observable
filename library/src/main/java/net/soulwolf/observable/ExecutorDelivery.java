package net.soulwolf.observable;


import android.os.Handler;

import java.util.concurrent.Executor;

/**
 * author: Ewen
 * since : 15/10/15 下午6:48.
 */
class ExecutorDelivery<RESULT> implements SubscriberDelivery<RESULT> {

    private Executor mDeliveryExecutor;

    ExecutorDelivery(final Handler handler){
        mDeliveryExecutor = new Executor() {
            @Override
            public void execute(Runnable command) {
                handler.post(command);
            }
        };
    }

    @Override
    public void postSuccess(RESULT t, Subscriber<? super RESULT> subscriber) {
        mDeliveryExecutor.execute(new SubscriberDeliveryRunnable(subscriber,t));
    }

    @Override
    public void postStart(Subscriber<? super RESULT> subscriber) {
        mDeliveryExecutor.execute(new SubscriberDeliveryRunnable(subscriber));
    }

    @Override
    public void postError(Throwable error, Subscriber<? super RESULT> subscriber) {
        mDeliveryExecutor.execute(new SubscriberDeliveryRunnable(subscriber, error));
    }

    class SubscriberDeliveryRunnable implements Runnable {

        static final int SUBSCRIBER_POST_START      = 1;
        static final int SUBSCRIBER_POST_SUCCESS    = 2;
        static final int SUBSCRIBER_POST_ERROR      = 3;

        int mPostType;
        Subscriber<? super RESULT> subscriber;
        Throwable error;
        RESULT    result;

        SubscriberDeliveryRunnable(Subscriber<? super RESULT> s){
            this.mPostType = SUBSCRIBER_POST_START;
            this.subscriber = s;
        }

        SubscriberDeliveryRunnable(Subscriber<? super RESULT> s,RESULT result){
            this.mPostType = SUBSCRIBER_POST_SUCCESS;
            this.subscriber = s;
            this.result = result;
        }

        SubscriberDeliveryRunnable(Subscriber<? super RESULT> s,Throwable error){
            this.mPostType = SUBSCRIBER_POST_SUCCESS;
            this.subscriber = s;
            this.error = error;
        }

        @Override
        public void run() {
            try {
                switch (mPostType){
                    case SUBSCRIBER_POST_START:
                        subscriber.onStart();
                        break;

                    case SUBSCRIBER_POST_SUCCESS:
                        subscriber.onCompleted(result);
                        break;

                    case SUBSCRIBER_POST_ERROR:
                        subscriber.onError(error);
                        break;
                }
            }catch (Exception e){
                if(subscriber != null){
                    subscriber.onError(e);
                }
            }
        }
    }

}
