package youssef.kaffel.topquiz.Controleur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import youssef.kaffel.topquiz.Modele.Question;
import youssef.kaffel.topquiz.Modele.QuestionBank;
import youssef.kaffel.topquiz.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView Question;
    private Button mAnswerButton1;
    private Button mAnswerButton2;
    private Button mAnswerButton3;
    private Button mAnswerButton4;

    private Question mCurrentQuestion;
    private QuestionBank mQuestionBank;
    private int mScore;
    private int mNumberOfQuestion;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentscore";
    public static final String BUNDLE_STATE_QUESTION = "question";
    private boolean mEnableTouchEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mQuestionBank = this.generateQuestion();
        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestion = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }
        else{
            mScore = 0;
            mNumberOfQuestion=4;
        }


        mEnableTouchEvent = true;

        Question = (TextView) findViewById(R.id.question);
        mAnswerButton1 = (Button) findViewById(R.id.propostion_1);
        mAnswerButton2 = (Button) findViewById(R.id.propostion_2);
        mAnswerButton3 = (Button) findViewById(R.id.propostion_3);
        mAnswerButton4 = (Button) findViewById(R.id.propostion_4);

        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        mAnswerButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_QUESTION,mNumberOfQuestion);
        outState.putInt(BUNDLE_STATE_SCORE,mScore);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int)  v.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            //good
            Toast.makeText(this , "Correct",Toast.LENGTH_SHORT).show();
            mScore++;
        }
            else{
                //wrong
            Toast.makeText(this , "Wrong",Toast.LENGTH_SHORT).show();

            }
            mEnableTouchEvent = false;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mEnableTouchEvent = true;
                    if (--mNumberOfQuestion == 0){
                        endGame();
                    }
                    else {
                        mCurrentQuestion=mQuestionBank.getQuestion();
                        displayQuestion(mCurrentQuestion);
                    }

                }
            },2000);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvent && super.dispatchTouchEvent(ev);
    }

    private void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Well Done !")
                .setMessage("your score is"+mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();

                    }
                })
                .create()
                .show();
    }
    private void displayQuestion(final Question question){
        Question.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));
    }
    private QuestionBank generateQuestion() {
        Question question1 = new Question("What is the name of France President", Arrays.asList("François", "Emmanuel", "2", "5"), 1);
        Question question2 = new Question("Q2", Arrays.asList("1", "3", "2", "4"), 2);
        Question question3 = new Question("Click Yes", Arrays.asList("Hmmm", "ok", "Fine", "No"), 3);
        Question question4 = new Question("What is my name", Arrays.asList("Bom Bom", "lolo", "Torreto", "Vape Store"), 3);
    return new QuestionBank(Arrays.asList(question1,question2,question3,question4));
    }


    }
