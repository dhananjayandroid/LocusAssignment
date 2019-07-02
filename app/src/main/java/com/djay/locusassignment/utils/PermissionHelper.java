package com.djay.locusassignment.utils;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class PermissionHelper {

  // Required permissions Permissions request codes
  private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 99;
  private static final int MY_PERMISSIONS_REQUEST_CAMERA = 9;

  private final Activity activity;
  private final PermissionListener permissionListener;

  public PermissionHelper(Activity activity, PermissionListener permissionListener) {
    this.activity = activity;
    this.permissionListener = permissionListener;
  }

  /**
   * Start required permission checks
   */
  public void checkPermission() {
    if (ActivityCompat.checkSelfPermission(activity, permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
          MY_PERMISSIONS_REQUEST_CAMERA);
    } else {
      checkExternalStoragePermission();
    }
  }

  /**
   * Check if external storage permission is given or not.
   * If given, notifies listener
   */
  private void checkExternalStoragePermission() {
    if (ActivityCompat.checkSelfPermission(activity, permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(activity, new String[]{permission.WRITE_EXTERNAL_STORAGE},
          MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
    } else {
      permissionListener.permissionGranted();
    }
  }

  /**
   * Checks if  permission is Granted or not
   *
   * @param requestCode Request Code for permission
   * @param grantResults Grant permission results
   */
  public void checkPermissionGrantedOrNot(int requestCode, @NonNull int[] grantResults) {
    if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
      if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        checkExternalStoragePermission();
      } else {
        permissionListener.permissionDenied();
      }
    } else if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_STORAGE) {
      if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        permissionListener.permissionGranted();
      } else {
        permissionListener.permissionDenied();
      }
    }
  }

  /**
   * Listener for Permission status update
   */
  public interface PermissionListener {

    void permissionGranted();

    void permissionDenied();
  }

}
