package com.djay.locusassignment.di.mock;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

public class MockDataGenerator {

  /**
   * Create mocked response from assets
   *
   * @param context Context
   * @return mocked json string
   */
  @SuppressWarnings({"ResultOfMethodCallIgnored", "CharsetObjectCanBeUsed"})
  public static String getMockedResponse(Context context) {
    String json = null;
    try {
      InputStream inputStream = context.getAssets().open("response.json");
      int size = inputStream.available();
      byte[] buffer = new byte[size];
      inputStream.read(buffer);
      inputStream.close();
      json = new String(buffer, "UTF-8");

    } catch (IOException e) {
      e.printStackTrace();
    }

    return json;
  }

}
