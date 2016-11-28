package com.dereklmclean.styx;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dereklmclean.styx.provider.WordsDatabase;
import com.dereklmclean.styx.schema.CharSet;
import com.dereklmclean.styx.schema.RandomNumber;
import com.dereklmclean.styx.schema.Schema;
import com.dereklmclean.styx.schema.Word;

public class PasswordGeneratorActivity extends ActionBarActivity {

    private static final String TAG = PasswordGeneratorActivity.class.getSimpleName();

    Button button;
    private TextView passwordView;
    private Schema first = new Schema();
    private Schema second = new Schema();
    private Schema mSelected = null;
    private TextView entropyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);
        button = (Button) findViewById(R.id.generate_password_btn);
        button.setOnClickListener(new GeneratePasswordClickHandler());
        passwordView = (TextView) findViewById(R.id.password_display);
        entropyView = (TextView) findViewById(R.id.entropy_display);

        final WordsDatabase db = new WordsDatabase(this);
        final WordList mWordList = new WordDbList(db);

        final Word word = new Word(mWordList);
        final RandomNumber randomNumber = new RandomNumber(3);
        final CharSet symbolSet = new CharSet(new char[]{'@', '#', '$', '%', '&', '*', '!', '~'});

        first.add(word);
        first.add(randomNumber);
        first.add(word);
        first.add(word);
        first.add(word);

        second.add(word);
        second.add(randomNumber);
        second.add(word);
        second.add(word);
        second.add(word);
        second.add(symbolSet);

        final int selected = ((RadioGroup) findViewById(R.id.schema_selector)).getCheckedRadioButtonId();
        selectSchema(selected);

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

    public void onSchemaSelected(View view) {// Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        if (checked) {
            // Check which radio button was clicked
            selectSchema(view.getId());
        }
    }

    private void selectSchema(int id) {
        switch (id) {
            case R.id.schema_1: mSelected = first; break;
            case R.id.schema_2: mSelected = second; break;
        }
        entropyView.setText(String.valueOf(mSelected.entropy()));
    }

    class GeneratePasswordClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            final String password;
            if (mSelected == null) {
                password = "";
            } else {
                password = mSelected.generate();
            }
            passwordView.setText(password);
        }
    }
}
