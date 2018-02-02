package com.example.kennwu.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score;
    int numQuestions;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    Button playAgainButton;


    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextView;

    RelativeLayout gameRelativeLayout;


    public void playAgain(View view){
        score = 0;
        numQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");

        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();


        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                //convert long to string using value of
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) +"s");
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Your score: "+ Integer.toString(score) + "/" + Integer.toString(numQuestions));
            }
        }.start();
    }

    public void chooseAnswer(View view){

        //Log.i("Tag", (String) view.getTag());

        //if user gets correct answer
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");

        } else {
            resultTextView.setText("Wrong!");
        }

        numQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numQuestions));

        generateQuestion();

    }

    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer =rand.nextInt(4);

        //clear the arrayList
        answers.clear();

        int incorrectAnswer;

        for(int i = 0; i < 4;i++ ){
            if(i ==locationOfCorrectAnswer){
                answers.add(a+b);
            } else {
                incorrectAnswer = rand.nextInt(41);

                //generate incorrect answer that does not match correctAnswer until it is different
                while(incorrectAnswer ==  a + b){
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        //want the timer to start when the game ends
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        playAgainButton = (Button) findViewById(R.id.playAgainButton);



    }
}
