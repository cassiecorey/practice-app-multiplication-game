package com.practiceapps.multiplicationgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void startNewGame(View v) {
		Intent intent = new Intent(this, GameInitActivity.class);
		startActivity(intent);
	}
	
	public void viewHighscores(View v) {
		Intent intent = new Intent(this, LeaderboardActivity.class);
		startActivity(intent);
	}
}
