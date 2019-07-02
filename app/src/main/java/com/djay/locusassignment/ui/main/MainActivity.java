package com.djay.locusassignment.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.djay.locusassignment.BR;
import com.djay.locusassignment.R;
import com.djay.locusassignment.databinding.ActivityMainBinding;
import com.djay.locusassignment.service.model.Item;
import com.djay.locusassignment.ui.InjectionActivity;
import com.djay.locusassignment.ui.main.adapter.MyAdapter;
import com.djay.locusassignment.utils.CommonUtils;
import com.djay.locusassignment.utils.ImageUtils;
import com.djay.locusassignment.utils.PermissionHelper;
import com.djay.locusassignment.utils.PermissionHelper.PermissionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * MainActivity class for showing list of items
 */
public class MainActivity extends
    InjectionActivity<ActivityMainBinding, MainViewModel> implements MainNavigator,
    PermissionListener {

  private Item selectedItem;
  private MyAdapter mAdapter;
  private PermissionHelper permissionHelper;
  final ArrayList<Item> itemsList = new ArrayList<>();
  private static final int CAMERA_REQUEST = 100;

  @Inject
  MainViewModel mMainViewModel;

  @Override
  public int getBindingVariable() {
    return BR.viewmodel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  public MainViewModel getViewModel() {
    return mMainViewModel;
  }

  @Override
  protected void onCreate(Bundle pBundle) {
    super.onCreate(pBundle);
    mMainViewModel.setNavigator(this);
    getViewModel().getItemsListObservable().observe(this, items -> {
      if (items != null) {
        mAdapter.setItems(items);
      }
    });
    init();
  }

  /**
   * Init initial ui and data
   */
  private void init() {
    RecyclerView recyclerView = getViewDataBinding().rvItems;
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(RecyclerView.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);
    mAdapter = new MyAdapter(itemsList);
    mAdapter.setNavigator(this);
    recyclerView.setAdapter(mAdapter);

    permissionHelper = new PermissionHelper(this, this);
  }

  @Override
  public void openCamera(Item item) {
    selectedItem = item;
    permissionHelper.checkPermission();
  }

  @Override
  public void openImage(Item item) {
    ImageUtils.showImage(this, item);
  }

  /**
   * start image Capture
   */
  private void startCapture() {
    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (cameraIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(cameraIntent, CAMERA_REQUEST);
    } else {
      Toast.makeText(this, getString(R.string.camera_unavailable), Toast.LENGTH_LONG).show();
    }
  }

  @SuppressWarnings("ConstantConditions")
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
      Bundle bundle = data.getExtras();
      if (bundle != null && bundle.get("data") != null) {
        try {
          selectedItem
              .setCurrentValue(ImageUtils.saveBitmapFile(this, (Bitmap) bundle.get("data")));
        } catch (IOException e) {
          e.printStackTrace();
        }
        mAdapter.notifyItemChanged(itemsList.indexOf(selectedItem));
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    permissionHelper.checkPermissionGrantedOrNot(requestCode, grantResults);
  }

  @Override
  public void permissionGranted() {
    startCapture();
  }

  @Override
  public void permissionDenied() {
    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == R.id.submit) {
      StringBuilder result = new StringBuilder();
      for (Item item : itemsList) {
        if (item.getCurrentValue() != null) {
          if (item.getType().equalsIgnoreCase("SINGLE_CHOICE")) {
            int currentValue = CommonUtils.convertPosition(item.getCurrentValue());
            if (currentValue != -1) {
              result.append("\n").append(item.getId()).append(" : ")
                  .append(item.getDataMap().getOptions().get(currentValue));
            }
          } else {
            result.append("\n").append(item.getId()).append(" : ").append(item.getCurrentValue());
          }
        }
      }
      Log.e("Current Values", result.toString());
      Toast.makeText(this, getString(R.string.log_printed), Toast.LENGTH_LONG).show();
    }
    return true;
  }
}
