package com.example.worldexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button startButton = (Button)findViewById(R.id.button_next_climate);
    startButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        launchClimateActivity();
      }
    });
  }

  private void launchClimateActivity () {
    Intent intent = new Intent(this, ClimateActivity.class);
    startActivity(intent);
  }
}