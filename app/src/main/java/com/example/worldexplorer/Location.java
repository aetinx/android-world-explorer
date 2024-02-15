package com.example.worldexplorer;

public class Location {
  private String placeName;
  private String coords;
  private String climate;
  private String community;

  public static final String STATE_CLIMATE = "com.example.worldexplorer.CLIMATE";
  public static final String STATE_COMMUNITY = "com.example.worldexplorer.URBAN_RURAL";

  public Location(String placeName, String coords, String climate, String community) {
    this.placeName = placeName;
    this.coords = coords;
    this.climate = climate;
    this.community = community;
  }

  public String getPlaceName() {
    return placeName;
  }
  public String getCoords() {
    return coords;
  }
  public String getClimate() {
    return climate;
  }
  public String getCommunity() {
    return community;
  }
}
