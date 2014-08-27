package com.practiceapps.multiplicationgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GameOverActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		
		Intent intent = getIntent();
		String score = intent.getStringExtra("score");
		if(isHighscore(score)) {
			saveHighscore();
		}
	}
	
	private void saveHighscore() {
		final EditText input = new EditText(this);
		
		new AlertDialog.Builder(this)
	    .setTitle("New highscore!")
	    .setMessage("name:")
	    .setView(input)
	    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	        	// save the score+name
	        }
	    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	            // Do nothing.
	        }
	    }).show();
	}
	
	private boolean isHighscore(String s) {
		return true;
	}
	
	public void playAgain(View v) {
		Intent intent = new Intent(this, GameInitActivity.class);
		startActivity(intent);
	}
	
	public void goToMainMenu(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
