
package com.djay.locusassignment.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("dataMap")
  @Expose
  private DataMap dataMap;

  private String currentValue;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  @SuppressWarnings("unused")
  public void setTitle(String title) {
    this.title = title;
  }

  public DataMap getDataMap() {
    return dataMap;
  }

  @SuppressWarnings("unused")
  public void setDataMap(DataMap dataMap) {
    this.dataMap = dataMap;
  }

  public String getCurrentValue() {
    return currentValue;
  }

  public void setCurrentValue(String currentValue) {
    this.currentValue = currentValue;
  }
}
