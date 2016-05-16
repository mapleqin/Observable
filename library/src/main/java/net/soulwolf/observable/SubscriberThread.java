package net.soulwolf.observable;

/**
 * author: Ewen
 * since : 15/10/16 上午10:22.
 */
public class SubscriberThread<RESULT> implements Runnable {

    private boolean isFinish;
    private boolean isCancel;
    private final OnSubscribe<RESULT> mOnSubscribe;
    private final SubscriberDelegate<? super RESULT> mSubscriberDelegate;

    public SubscriberThread(OnSubscribe<RESULT> onSubscribe,SubscriberDelegate<? super RESULT> delegate){
        mOnSubscribe = onSubscribe;
        mSubscriberDelegate = delegate;
    }

    @Override
    public void run() {
        if(isCancel){
            return;
        }
        try {
            mOnSubscribe.call(mSubscriberDelegate);
            isFinish = true;
        } catch (Exception e) {
            mSubscriberDelegate.onError(e);
        }
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void cancel(boolean isCancel) {
        this.isCancel = isCancel;
    }
}
