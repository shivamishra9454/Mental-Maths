package com.example.yash1998.mentalmaths;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton,option1,option2,option3,option4;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationOfCorrectAnswer,score=0,noOfQuestions=0;
    GridLayout gridLayout;
    CountDownTimer countDownTimer;
    TextView answerTextView,scoreTextView,questionTextView,timeLeftTextView;

    public void start(View view){
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
        questionTextView.setVisibility(View.VISIBLE);
        answerTextView.setText("");
        gridLayout.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        countDownTimer.start();
        generateQuestion();
        score=0;
        noOfQuestions=0;
    }

    public void chooseAnswer(View view){

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            answerTextView.setText("Correct!");
        }
        else{
            answerTextView.setText("Wrong!");
        }
        generateQuestion();
        noOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
    }

    public void generateQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        int op=rand.nextInt(4);
        String operator="";
        int ans=0;
        switch(op){
            case 0: operator=" + ";ans=a+b;break;
            case 1: operator=" - ";ans=a-b;break;
            case 2: operator=" * ";ans=a*b;break;
            case 3: operator=" / ";ans=a/b;break;
        }
        questionTextView.setText(Integer.toString(a)+operator+Integer.toString(b)+" = ?");

        locationOfCorrectAnswer=rand.nextInt(4);

        answers.clear();
        int incorrectAnswer;
        for(int i=0;i<4;i++){
            if (i==locationOfCorrectAnswer){
                answers.add(ans);
            }
            else{
                incorrectAnswer=rand.nextInt(41);
                while (incorrectAnswer==a+b){
                    incorrectAnswer=rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        option1.setText(Integer.toString(answers.get(0)));
        option2.setText(Integer.toString(answers.get(1)));
        option3.setText(Integer.toString(answers.get(2)));
        option4.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerTextView=(TextView)findViewById(R.id.answerTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        questionTextView=(TextView)findViewById(R.id.questionTextView);
        timeLeftTextView=(TextView)findViewById(R.id.timeLeftTextView);
        startButton=(Button)findViewById(R.id.startButton);
        gridLayout=(GridLayout)findViewById(R.id.gridLayout);

        option1=(Button)findViewById(R.id.option1);
        option2=(Button)findViewById(R.id.option2);
        option3=(Button)findViewById(R.id.option3);
        option4=(Button)findViewById(R.id.option4);

        countDownTimer=new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timeLeftTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                answerTextView.setVisibility(View.VISIBLE);
                startButton.setText("Play Again?");
                startButton.setVisibility(View.VISIBLE);
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
                questionTextView.setVisibility(View.INVISIBLE);
                scoreTextView.setText("0/0");
                answerTextView.setText("Your Score: "+score+"/"+noOfQuestions);
                gridLayout.setVisibility(View.INVISIBLE);
            }
        };

    }
}
