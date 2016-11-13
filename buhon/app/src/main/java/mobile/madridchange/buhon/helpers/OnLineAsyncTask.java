package mobile.madridchange.buhon.helpers;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by omar on 13/11/16.
 */

public abstract class OnLineAsyncTask<I, O, T> extends AsyncTask<Void, Void, O> {
    protected final WeakReference<T> targetRef;
    protected I input;

    public OnLineAsyncTask(I input, T target) {
        this.input = input;
        targetRef = new WeakReference<T>(target);
    }

    @Override
    protected O doInBackground(Void... args) {
        return process(input);
    }

    protected abstract O process(I input);

    @Override
    protected void onPostExecute(O output) {
        final T target = targetRef.get();
        if (target != null)
            applyOutputToTarget(output, target);
    }

    protected abstract void applyOutputToTarget(O output, T target);
}
