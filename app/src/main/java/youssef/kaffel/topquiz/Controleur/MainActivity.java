package youssef.kaffel.topquiz.Controleur;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import youssef.kaffel.topquiz.Modele.User;
import youssef.kaffel.topquiz.R;

public class MainActivity extends AppCompatActivity {
   private EditText mNameInput;
   private Button mPlayButton;
   private TextView mTextView;
   private User mUser;
   public static final int GAME_ACTIVITY_REQUEST = 42;
   private SharedPreferences mPreferences;
   public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
   public static final String PREF_KEY_FIRST_NAME = "PREF_KEY_FIRST_NAME";


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if (GAME_ACTIVITY_REQUEST == requestCode && RESULT_OK == resultCode){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0);
            mPreferences.edit().putInt(PREF_KEY_SCORE,score).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser = new User();
        mPreferences = getPreferences(MODE_PRIVATE);
        mTextView = (TextView) findViewById(R.id.text_viewer);
        mNameInput = (EditText) findViewById(R.id.activity_input_name_txt);
        mPlayButton = (Button) findViewById(R.id.activity_play_btn);
        mPlayButton.setEnabled(false);
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mNameInput.getText().toString();
                mUser.setFirstName(firstName);
                mPreferences.edit().putString(PREF_KEY_FIRST_NAME,mUser.getFirstName()).apply();
                Intent gameActivityIntent = new Intent(MainActivity.this , GameActivity.class);
                startActivityForResult(gameActivityIntent,GAME_ACTIVITY_REQUEST);

            }
        });

    }
}