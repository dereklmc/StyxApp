package com.dereklmclean.styx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dereklmclean.styx.provider.WordsDatabase;
import com.dereklmclean.styx.schema.atoms.CharSet;
import com.dereklmclean.styx.schema.atoms.RandomNumber;
import com.dereklmclean.styx.schema.atoms.Word;

public class PasswordGeneratorActivity extends ActionBarActivity {

    private static final String TAG = PasswordGeneratorActivity.class.getSimpleName();

    Button button;
    private WordsDatabase db;
    private TextView passwordView;
    private WordList mWordList;
    private Schema mSchema = Schema.ONE;
    private TextView entropyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);
        button = (Button) findViewById(R.id.generate_password_btn);
        button.setOnClickListener(new GeneratePasswordClickHandler());
        passwordView = (TextView) findViewById(R.id.password_display);
        entropyView = (TextView) findViewById(R.id.entropy_display);
        db = new WordsDatabase(this);
        mWordList = new WordDbList(db);
        Log.i(TAG, "Word DB Count of Length: " + mWordList.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_password_generator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private String generateSchemaOne() {
        final Word word = new Word(mWordList);
        RandomNumber randomNumber = new RandomNumber(3);
        String word1 = word.generate();
        String word2 = word.generate();
        String word3 = word.generate();
        String word4 = word.generate();
        String number = randomNumber.generate();
        entropyView.setText(String.valueOf(word.entropy() * 4 + randomNumber.entropy()));
        return word1 + number + word2 + word3 + word4;
    }

    @NonNull
    private String generateSchemaTwo() {
        final Word word = new Word(mWordList);
        RandomNumber randomNumber = new RandomNumber(3);
        CharSet symbolSet = new CharSet(new char[]{'@', '#', '$', '%', '&', '*', '!', '~'});
        String word1 = word.generate();
        String word2 = word.generate();
        String word3 = word.generate();
        String word4 = word.generate();
        String number = randomNumber.generate();
        String symbol = symbolSet.generate();
        entropyView.setText(String.valueOf(word.entropy() * 4 + randomNumber.entropy() + symbolSet.entropy()));
        return word1 + number + word2 + word3 + word4 + symbol;
    }

    public void onSchemaSelected(View view) {// Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        if (checked) {
            // Check which radio button was clicked
            switch (view.getId()) {
                case R.id.schema_1: mSchema = Schema.ONE; break;
                case R.id.schema_2: mSchema = Schema.TWO; break;
            }
        }
    }

    class GeneratePasswordClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            final String password;
            switch (mSchema) {
                case ONE:
                    password = generateSchemaOne(); break;
                case TWO:
                    password = generateSchemaTwo(); break;
                default:
                    password = "";

            }
            passwordView.setText(password);
        }
    }

    enum Schema {
        ONE, // [word][number][word][word][word]
        TWO, // [word][number][word][word][word][symbol]
    }
}
