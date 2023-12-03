package com.example.stacker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class YouWin extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_screen);
    }

    public void restartGame(View view) {
        // Intent to restart the game
        Intent intent = new Intent(this, MainActivity.class); // Replace GameActivity with your game's main activity
        startActivity(intent);
        finish();
    }

    public void goToMenu(View view) {
        // Intent to navigate to the menu screen
        Intent intent = new Intent(this, ModuleActivity.class);
        startActivity(intent);
        finish();
    }
}
