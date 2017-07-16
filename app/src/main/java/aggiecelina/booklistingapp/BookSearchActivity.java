package aggiecelina.booklistingapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aggiecelina on 15.07.17.
 */

public class BookSearchActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = BookSearchActivity.class.getName();
    private BookAdapter adapter;
    private TextView emptyStateTextView;
    private static final int BOOK_LOADER_ID = 1;
    //private static final String GOOGLE_BOOKS_API_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=flowers&maxResults=15";
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_search_activity);

        this.url = getApiRequestUrl(getIntent().getExtras().getString("keyword"));

        // Find a reference to the {@link ListView} in the layout
        final ListView bookListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(adapter);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);

        emptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(emptyStateTextView);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookListLoader(this, this.url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        emptyStateTextView.setText(R.string.no_books);

        // Clear the adapter of previous earthquake data
        adapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            adapter.addAll(books);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        adapter.clear();
    }

    public String getApiRequestUrl(String keyword){

        return "https://www.googleapis.com/books/v1/volumes?q=" + keyword + "&maxResults=15";
    }
}
