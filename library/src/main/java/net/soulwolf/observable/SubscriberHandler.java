package net.soulwolf.observable;

/**
 * author: Ewen
 * since : 15/10/16 上午10:50.
 */
public abstract class SubscriberHandler<RESULT> implements Subscriber<RESULT> {

    @Override
    public void onStart() {

    }

    public void onFailure(Throwable error){
        error.printStackTrace();
    }

    public abstract void onSuccess(RESULT response) throws Exception;


    public final void onError(Throwable throwable){
        throwable.printStackTrace();
        onFailure(throwable);
        onFinally(null);
    }

    @Override
    public final void onCompleted(RESULT response) {
        try {
            onSuccess(response);
            onFinally(response);
        }catch (Exception e){
            onError(e);
        }
    }

    protected void onFinally(RESULT result){

    }
}
