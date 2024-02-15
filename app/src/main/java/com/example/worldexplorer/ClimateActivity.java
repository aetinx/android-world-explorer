package com.example.worldexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClimateActivity extends AppCompatActivity {
  TextView climateText;
  private StringBuilder climateSB;
  private SharedPreferences sp;
  String spFile = "com.example.worldexplorer.wexprefs";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_climate);
    sp = getSharedPreferences(spFile, MODE_PRIVATE);

    climateSB = new StringBuilder();
    climateText = (TextView)findViewById(R.id.climate_result_label);
    setupButton(R.id.button_cold, "Cold");
    setupButton(R.id.button_medium, "Medium");
    setupButton(R.id.button_hot, "Hot");

    Button startButton = (Button)findViewById(R.id.button_next_urbanrural);
    startButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        l();
      }
    });
  }

  private void l () {
    Intent intent = new Intent(this, UrbanRuralActivity.class);
    startActivity(intent);
  }

  private void setupButton(int buttonId, final String climate) {
    Button button = findViewById(buttonId);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        climateSB.setLength(0);
        climateSB.append(climate);
        Log.d("WEX", climateSB.toString());
        climateText.setText(climateSB.toString());
      }
    });
  }

  @Override
  protected void onStart() { super.onStart(); Log.d("WEX", "CLIMATE START"); }
  @Override
  protected void onPause() {
    super.onPause();
    Log.d("WEX", "CLIMATE PAUSE");
    SharedPreferences.Editor preferencesEditor = sp.edit();
    preferencesEditor.putString(Location.STATE_CLIMATE, climateSB.toString());
    preferencesEditor.apply();
  }
  @Override
  protected void onRestart() { super.onRestart(); Log.d("WEX", "CLIMATE RESTART"); }
  @Override
  protected void onResume() { super.onResume(); Log.d("WEX", "CLIMATE RESUME"); }
}