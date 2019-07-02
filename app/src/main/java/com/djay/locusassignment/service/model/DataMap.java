
package com.djay.locusassignment.service.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMap {

  @SerializedName("options")
  @Expose
  private List<String> options = null;

  public List<String> getOptions() {
    return options;
  }

  @SuppressWarnings("unused")
  public void setOptions(List<String> options) {
    this.options = options;
  }

}
