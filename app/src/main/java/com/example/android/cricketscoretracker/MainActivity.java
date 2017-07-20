package com.example.android.cricketscoretracker;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private static final int TEAM_A = 0;
    private static final int TEAM_B = 1;
    private TextView runTextA;
    private TextView wicketTextA;
    private TextView ballTextA;
    private TextView runTextB;
    private TextView wicketTextB;
    private TextView ballTextB;
    private int whoIsBatting = -1;
    private int runs = 0;
    private int wickets = 0;
    private int balls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        referenceAndSetListeners();
        resetViews();
    }

    /**
     * By RadioGroup set whoIsbatting variable to later decide who's score to increase
     */
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        //Means one team was batting now other team is selected so setting variables to 0
        if (whoIsBatting != -1) {
            runs = balls = wickets = 0;
        }
        //Changing the whoIsBatting variable
        if (checkedId == R.id.team_a) {
            whoIsBatting = TEAM_A;
        } else whoIsBatting = TEAM_B;
    }

    @Override
    public void onClick(View v) {
        //User may tap button first before selecting the radio, so to save from crash check and show appropriate toast
        if (whoIsBatting != -1) {
            switch (v.getId()) {
                case R.id.add_six:
                    runs += 6;
                    balls++;
                    updateViews();
                    break;
                case R.id.add_four:
                    runs += 4;
                    balls++;
                    updateViews();
                    break;
                case R.id.add_three:
                    runs += 3;
                    balls++;
                    updateViews();
                    break;
                case R.id.add_two:
                    runs += 2;
                    balls++;
                    updateViews();
                    break;
                case R.id.add_one:
                    runs += 1;
                    balls++;
                    updateViews();
                    break;
                case R.id.add_dot:
                    balls++;
                    updateViews();
                    break;
                case R.id.add_out:
                    wickets++;
                    balls++;
                    updateViews();
                    break;
                case R.id.reset:
                    resetViews();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_select_first), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method update views of team who is batting.
     */
    private void updateViews() {
        if (whoIsBatting == TEAM_A) {
            runTextA.setText(String.valueOf(runs));
            wicketTextA.setText(getString(R.string.wicket).replace("#", Integer.toString(wickets)));
            ballTextA.setText(getString(R.string.ball).replace("#", Integer.toString(balls)));
        } else {
            runTextB.setText(String.valueOf(runs));
            wicketTextB.setText(getString(R.string.wicket).replace("#", Integer.toString(wickets)));
            ballTextB.setText(getString(R.string.ball).replace("#", Integer.toString(balls)));
        }
    }

    /**
     * This method is called whe user wants to reset the score card and start over.
     */
    private void resetViews() {
        runs = balls = wickets = 0;
        runTextA.setText(String.valueOf(runs));
        wicketTextA.setText(getString(R.string.wicket).replace("#", Integer.toString(wickets)));
        ballTextA.setText(getString(R.string.ball).replace("#", Integer.toString(balls)));
        runTextB.setText(String.valueOf(runs));
        wicketTextB.setText(getString(R.string.wicket).replace("#", Integer.toString(wickets)));
        ballTextB.setText(getString(R.string.ball).replace("#", Integer.toString(balls)));
    }

    //method to reference views and set OnClickListeners to Buttons
    private void referenceAndSetListeners() {
        Button plusSix = (Button) findViewById(R.id.add_six);
        plusSix.setOnClickListener(this);
        Button plusFour = (Button) findViewById(R.id.add_four);
        plusFour.setOnClickListener(this);
        Button plusThree = (Button) findViewById(R.id.add_three);
        plusThree.setOnClickListener(this);
        Button plusTwo = (Button) findViewById(R.id.add_two);
        plusTwo.setOnClickListener(this);
        Button plusOne = (Button) findViewById(R.id.add_one);
        plusOne.setOnClickListener(this);
        Button plusDot = (Button) findViewById(R.id.add_dot);
        plusDot.setOnClickListener(this);
        Button plusOut = (Button) findViewById(R.id.add_out);
        plusOut.setOnClickListener(this);
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(this);

        runTextA = (TextView) findViewById(R.id.runs_a);
        wicketTextA = (TextView) findViewById(R.id.wickets_a);
        ballTextA = (TextView) findViewById(R.id.balls_a);
        runTextB = (TextView) findViewById(R.id.runs_b);
        wicketTextB = (TextView) findViewById(R.id.wickets_b);
        ballTextB = (TextView) findViewById(R.id.balls_b);

        RadioGroup teamBatting = (RadioGroup) findViewById(R.id.teams);
        teamBatting.setOnCheckedChangeListener(this);
    }
}
