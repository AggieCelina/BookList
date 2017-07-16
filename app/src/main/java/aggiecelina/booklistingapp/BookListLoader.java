package aggiecelina.booklistingapp;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by aggiecelina on 15.07.17.
 */

public class BookListLoader extends AsyncTaskLoader<List<Book>> {

    private String url;
    private static final String LOG_TAG = BookListLoader.class.getName();

    public BookListLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Book> loadInBackground() {

        Log.i(LOG_TAG, "load in backgroung");

        if (url == null) {
            return null;
        }
        List<Book> result = QueryUtils.fetchEarthquakeData(url);
        Log.i(LOG_TAG, "fetch data");

        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return result;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i(LOG_TAG, "force load");
    }
}
