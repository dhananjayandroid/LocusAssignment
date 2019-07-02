package com.djay.locusassignment.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.djay.locusassignment.BR;
import com.djay.locusassignment.R;
import com.djay.locusassignment.service.model.Item;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utility class related to Image. contains methods for saving and delete bitmaps etc. .
 */
public class ImageUtils {

  /**
   * Save Bitmap to file
   *
   * @param context Context
   * @param bitmap Bitmap to save
   * @return file path
   * @throws IOException method throws IOException if error occurs while saving bitmap to file
   */
  public static String saveBitmapFile(Context context, Bitmap bitmap) throws IOException {
    File mediaFile = getOutputMediaFile(context);
    FileOutputStream fileOutputStream = new FileOutputStream(mediaFile);
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
    fileOutputStream.flush();
    fileOutputStream.close();
    return mediaFile.getAbsolutePath();
  }

  /**
   * Creates Files object to save
   *
   * @param context Context
   * @return File to save
   */
  @SuppressWarnings("ResultOfMethodCallIgnored")
  private static File getOutputMediaFile(Context context) {
    File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "Locus");
    if (mediaStorageDir.isDirectory()) {
      // Create a media file name
      String mCurrentPath =
          mediaStorageDir.getPath() + File.separator + "IMG_" + System.currentTimeMillis() + ".jpg";
      return new File(mCurrentPath);
    } else { /// error handling for PIE devices..
      mediaStorageDir.delete();
      mediaStorageDir.mkdirs();
      galleryAddPic(context, mediaStorageDir);
      return (getOutputMediaFile(context));
    }
  }

  /**
   * Add pic to Gallery if not automatically fetched
   *
   * @param context Context
   * @param f File instance
   */
  private static void galleryAddPic(Context context, File f) {
    Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
    Uri contentUri = Uri.fromFile(f);
    mediaScanIntent.setData(contentUri);
    context.sendBroadcast(mediaScanIntent);
  }

  /**
   * Deletes image file
   *
   * @param filePath file path
   */
  public static void deleteImage(String filePath) {
    if (filePath != null) {
      File file = new File(filePath);
      //noinspection ResultOfMethodCallIgnored
      file.delete();
    }
  }

  /**
   * Shows image in a dialog
   *
   * @param activity Activity context
   * @param item Item
   */
  public static void showImage(Activity activity, Item item) {
    ViewDataBinding viewDataBinding = DataBindingUtil
        .inflate(LayoutInflater.from(activity), R.layout.dialog_viewer, null, false);
    viewDataBinding.setVariable(BR.item, item);
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    builder.setView(viewDataBinding.getRoot());
    builder.setCancelable(true);
    AlertDialog alertDialog = builder.create();
    if (alertDialog.getWindow() != null) {
      alertDialog.getWindow().getAttributes().windowAnimations = R.style.ZoomInOut;
    }
    alertDialog.show();
  }

}