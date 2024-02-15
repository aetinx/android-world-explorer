package com.example.worldexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UrbanRuralActivity extends AppCompatActivity {
  TextView communityText;
  private StringBuilder communitySB;
  private SharedPreferences sp;
  String spFile = "com.example.worldexplorer.wexprefs";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_urban_rural);
    sp = getSharedPreferences(spFile, MODE_PRIVATE);

    communitySB = new StringBuilder();
    communityText = (TextView)findViewById(R.id.community_result_label);
    setupButton(R.id.button_urban, "Urban");
    setupButton(R.id.button_rural, "Rural");

    Button btn = (Button)findViewById(R.id.button_result);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        l();
      }
    });
  }

  private void l () {
    Intent intent = new Intent(this, ResultActivity.class);
    startActivity(intent);
  }

  private void setupButton(int buttonId, final String community) {
    Button button = findViewById(buttonId);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        communitySB.setLength(0);
        communitySB.append(community);
        Log.d("WEX", communitySB.toString());
        communityText.setText(communitySB.toString());
      }
    });
  }

  @Override
  protected void onStart() { super.onStart(); Log.d("WEX", "UR START"); }
  protected void onPause() {
    super.onStart();
    Log.d("WEX", "UR PAUSE");
    SharedPreferences.Editor preferencesEditor = sp.edit();
    preferencesEditor.putString(Location.STATE_COMMUNITY, communitySB.toString());
    preferencesEditor.apply();
  }
  @Override
  protected void onRestart() { super.onStart(); Log.d("WEX", "UR RESTART"); }
  @Override
  protected void onResume() { super.onStart(); Log.d("WEX", "UR RESUME"); }
}