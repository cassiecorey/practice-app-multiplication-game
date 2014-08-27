package com.practiceapps.multiplicationgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;

public class LeaderboardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leaderboard);
		populateLeaderboard();
	}
	
	private void populateLeaderboard() {
		
	}
	
	public void resetScores(View v) {
		new AlertDialog.Builder(this)
	    //.setTitle("Delete entry")
	    .setMessage("Delete all highscores?")
	    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            clearScores();
	        }
	     })
	    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
		
	}
	
	private void clearScores() {
		
	}

	public void backToMenu() {
		NavUtils.navigateUpFromSameTask(this);
	}
}
