package com.practiceapps.multiplicationgame;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

	private int answer;
	private int size;
	private int score = 0;
	private final long startTime = 60 * 1000;
	private final long interval = 1000;
	private long timeRemaining = 60 * 1000;
	private boolean isPaused = false;
	private CountDownTimer countDownTimer;
	private GameActivity thisGame;
	
	private Random rand = new Random();
	private TextView scoreView;
	private TextView timerView;
	private Toast incorrectToast = null;
	private Toast correctToast = null;

	private void incScore(int i) {
		score+=i;
	}

	private void decScore(int i) {
		score-=i;
		if(score<0) {
			score = 0;
		}
	}
	
	private void setTimeRemaining(long timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		size = Integer.parseInt(getIntent().getStringExtra("size"));
		scoreView = (TextView) findViewById(R.id.score);
		timerView = (TextView) findViewById(R.id.timer);
		correctToast = Toast.makeText(getApplicationContext(), "Correct!  +10", Toast.LENGTH_SHORT);
		incorrectToast = Toast.makeText(getApplicationContext(), "Incorrect  -2", Toast.LENGTH_SHORT);
		correctToast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
		incorrectToast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
		countDownTimer = new MyCountDownTimer(startTime, interval);
		thisGame = this;
		countDownTimer.start();
		newQuestion();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(!isPaused) {
		pauseGame(this.getCurrentFocus());
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	public void pauseGame(View v) {
		final View view = v;
		isPaused = true;
		countDownTimer.cancel();
		new AlertDialog.Builder(this)
		.setMessage("Game paused")
		.setPositiveButton(R.string.resume, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				isPaused = false;
				countDownTimer = new MyCountDownTimer(timeRemaining, interval);
				countDownTimer.start();
			}
		})
		.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				Intent intent = new Intent(view.getContext(), GameInitActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		})
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setCancelable(false)
		.show();
	}

	public void checkAnswer(View v) {
		String bText = ((Button) v).getText().toString();
		//wrapped in "if" so that if they press an X twice nothing happens
		if(!bText.equals("X")) {
			int chosenAnswer = Integer.parseInt(bText);
			if(chosenAnswer==answer) {
				showToast(true);
				incScore(10);
				newQuestion();

			} else {
				showToast(false);
				((Button) v).setText("X");
				decScore(2);
			}
			scoreView.setText("Score: " + score);
		}
	}

	private void newQuestion() {
		TextView questionView = (TextView) findViewById(R.id.question);
		Button button1 = (Button) findViewById(R.id.result1);
		Button button2 = (Button) findViewById(R.id.result2);
		Button button3 = (Button) findViewById(R.id.result3);
		Button button4 = (Button) findViewById(R.id.result4);

		//generate two random multipliers
		int x = rand.nextInt(size)+1;
		int y = rand.nextInt(size)+1;
		answer = x*y;

		//generate random results
		int[] results = generateResults(x, y);

		//randomly pick a correct result between 0 and 3 (index)
		int correctB = rand.nextInt(3);
		results[correctB] = answer;

		//populate the views
		questionView.setText(x+" x "+y+" = ?");
		button1.setText(String.valueOf(results[0]));
		button2.setText(String.valueOf(results[1]));
		button3.setText(String.valueOf(results[2]));
		button4.setText(String.valueOf(results[3]));

	}

	private int[] generateResults(int x, int y) {
		int[] results = new int[4];
		int min;
		int max;

		if(x<y) {
			min = (x-1)*y;
			max = (x+1)*y;
		} else {
			min = x*(y-1);
			max = x*(y+1);
		}

		for(int i=0; i<results.length; i++) {
			int r = rand.nextInt((max-min)+min)+min;
			results[i] = r;
		}

		return results;
	}

	private void showToast(boolean correct) {
		if(correct) {
			correctToast.show();
		} else {
			incorrectToast.show();
		}
		//Override to make toast disappear in 1/2 second instead of preset min of 2
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				correctToast.cancel();
				incorrectToast.cancel();
			}
		}, 500);
	}
	
	private class MyCountDownTimer extends CountDownTimer {
		
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}
		
		@Override
		public void onFinish() {
			isPaused=false;
			Intent intent = new Intent(thisGame, GameOverActivity.class);
			intent.putExtra("score", String.valueOf(score));
			startActivity(intent);
			finish();
		}
		
		@Override
		public void onTick(long millisUntilFinished) {
			setTimeRemaining(millisUntilFinished);
			timerView.setText(String.valueOf(millisUntilFinished / 1000));
		}
	}
}
