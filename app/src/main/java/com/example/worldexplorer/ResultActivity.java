package com.example.worldexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
  TextView resultText;
  private StringBuilder sb;
  private SharedPreferences sp;
  private String spFile = "com.example.worldexplorer.wexprefs";
  String userClimate, userCommunity;
  private ArrayList<Location> matchingLocations;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);
    resultText = (TextView)findViewById(R.id.result_text);

    sp = getSharedPreferences(spFile, MODE_PRIVATE);
    sb = new StringBuilder();
    userClimate = sp.getString(Location.STATE_CLIMATE, "*");
    userCommunity = sp.getString(Location.STATE_COMMUNITY, "*");
    resultText.setText(getString(R.string.template_results, userClimate, userCommunity));

    Button btn = (Button)findViewById(R.id.button_restart);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        l();
      }
    });

    Button exBtn = (Button)findViewById(R.id.button_result_explore);
    exBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        initData();
        launchMap();
      }
    });
  }

  private void initData() {
    Log.d("WEX", "init");
    String[] placeNames = getResources().getStringArray(R.array.place_names);
    String[] placeCoords =  getResources().getStringArray(R.array.place_coords);
    String[] placeClimates = getResources().getStringArray(R.array.place_climates);
    String[] placeCommunities = getResources().getStringArray(R.array.place_communities);

    matchingLocations = new ArrayList<Location>();
    for (int i = 0; i < placeNames.length; i++) {
      if (placeClimates[i].equalsIgnoreCase(userClimate)) {
        matchingLocations.add(new Location(placeNames[i], placeCoords[i], placeClimates[i], placeCommunities[i]));
      }
    }
    Log.d("WEX", "number of matches "+matchingLocations.size());
  }

  private void launchMap() {
    Log.d("WEX", "launching map");
    Uri mapURI = Uri.parse("geo:0,0?q=" + matchingLocations.get(0).getCoords());
    TextView resultText = (TextView)findViewById(R.id.result_text);
    resultText.setText(matchingLocations.get(0).getPlaceName() + " " + matchingLocations.get(0).getCoords());

    Intent intent = new Intent(Intent.ACTION_VIEW, mapURI);

    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    } else {
      Log.d("WEX", "I CAN'T HANDLE IT!!!");
    }
  }

  private void l () {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }
}