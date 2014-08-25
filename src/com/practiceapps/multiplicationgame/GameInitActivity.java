package com.practiceapps.multiplicationgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class GameInitActivity extends Activity {

	NumberPicker sizePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_init);
		
		sizePicker = (NumberPicker) findViewById(R.id.size_picker);
		sizePicker.setMinValue(5);
		sizePicker.setMaxValue(15);
		sizePicker.setWrapSelectorWheel(false);
		sizePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	}
	
	public void startGame(View v) {
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("size", String.valueOf(sizePicker.getValue()));
		startActivity(intent);
	}
	
}
