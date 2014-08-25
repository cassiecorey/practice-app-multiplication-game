package com.practiceapps.multiplicationgame;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

	int answer;
	int size;
	int score = 0;
	Random rand = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		size = Integer.parseInt(getIntent().getStringExtra("size"));
		System.out.println(size);
		newQuestion();
	}

	public void checkAnswer(View v) {
		String bText = ((Button) v).getText().toString();
		if(!bText.equals("X")) {
			int chosenAnswer = Integer.parseInt(bText);
			if(chosenAnswer==answer) {
				showToast(true);

				newQuestion();

			} else {
				showToast(false);
				((Button) v).setText("X");
			}
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
		final Toast toast;
		if(correct) {
			toast = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);
		} else {
			toast = Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT);
		}
		toast.show();

		//Override to make toast disappear in 1 second instead of preset min of 2
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				toast.cancel();
			}
		}, 1000);
	}
}
