package aggiecelina.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserInputActivity extends AppCompatActivity {

    private final static String TAG = "UserInputActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_input_activity);

        final EditText editText = (EditText) findViewById(R.id.user_input_edittext_id);
        //Log.i(TAG, "********************** keyword: " + editText.getText().toString());

        Button button = (Button) findViewById(R.id.search_button_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserInputActivity.this, BookSearchActivity.class);
                 intent.putExtra("keyword", editText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
