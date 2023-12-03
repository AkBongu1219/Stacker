package com.example.stacker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
    }

    public void onDifficultySelected(View view) {
        String difficulty;
        int id = view.getId();
        if (id == R.id.easy) {
            difficulty = "Easy";
        } else if (id == R.id.medium) {
            difficulty = "Medium";
        } else if (id == R.id.hard) {
            difficulty = "Hard";
        } else {
            return; // Do nothing if the clicked view is not a difficulty button
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }
}
