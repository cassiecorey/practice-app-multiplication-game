package com.practiceapps.multiplicationgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class GameOverActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);

		Intent intent = getIntent();
		final String score = intent.getStringExtra("score");
		if(isHighscore(score)) {
			final EditText input = new EditText(this);
			input.setHint("Your name");
			input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

			new AlertDialog.Builder(this)
			.setTitle("You got a new highscore!")
			.setView(input)
			.setPositiveButton("Save", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					saveScore(input.getText().toString(), score);
				}
			}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					// Do nothing.
				}
			}).show();
		}
	}

	private boolean isHighscore(String score) {
		
		return true;
	}
	
	private void saveScore(String name, String score) {
		
	}

	public void playAgain(View v) {
		Intent intent = new Intent(this, GameInitActivity.class);
		startActivity(intent);
		finish();
	}

	public void goToMainMenu(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
