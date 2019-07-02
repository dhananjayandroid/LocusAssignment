package com.djay.locusassignment.utils;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Common Utility class
 */
public class CommonUtils {

  /**
   * Convert String position value to integer
   *
   * @param position string
   * @return position integer value of provided position string.
   */
  public static int convertPosition(String position) {
    try {
      return Integer.parseInt(position);
    } catch (Exception e) {
      return -1;
    }
  }

  /**
   * Hack for editText scrolling in RecyclerView
   *
   * @param editText EditText
   */
  @SuppressLint("ClickableViewAccessibility")
  public static void scrollHack(EditText editText) {
    if (editText != null) {
      editText.setOnTouchListener((v, event) -> {
        if (editText.hasFocus()) {
          v.getParent().requestDisallowInterceptTouchEvent(true);
          if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
            return true;
          }
        }
        return false;
      });
    }
  }
}
