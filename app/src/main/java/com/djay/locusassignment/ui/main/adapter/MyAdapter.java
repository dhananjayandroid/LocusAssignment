package com.djay.locusassignment.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.djay.locusassignment.BR;
import com.djay.locusassignment.R;
import com.djay.locusassignment.service.model.Item;
import com.djay.locusassignment.ui.main.MainNavigator;
import com.djay.locusassignment.utils.CommonUtils;
import com.djay.locusassignment.utils.ImageUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for items list
 */
public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

  private MainNavigator navigator;
  private final ArrayList<Item> mItems;

  // item types
  private static final int PHOTO = 0;
  private static final int SINGLE_CHOICE = 1;
  private static final int COMMENT = 2;

  public MyAdapter(ArrayList<Item> pItems) {
    this.mItems = pItems;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup pParent, int viewType) {
    LayoutInflater lLayoutInflater = LayoutInflater.from(pParent.getContext());
    switch (viewType) {
      case PHOTO:
        return new ViewHolder<>(DataBindingUtil
            .inflate(lLayoutInflater, R.layout.item_image, pParent, false));
      case SINGLE_CHOICE:
        return new ViewHolder<>(DataBindingUtil
            .inflate(lLayoutInflater, R.layout.item_choice, pParent, false));
      case COMMENT:
        return new ViewHolder<>(DataBindingUtil
            .inflate(lLayoutInflater, R.layout.item_comment, pParent, false));
      default:
        return new ViewHolder<>(DataBindingUtil
            .inflate(lLayoutInflater, R.layout.item_image, pParent, false));
    }
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.getBinding().setVariable(BR.myAdapter, this);
    holder.getBinding().setVariable(BR.item, mItems.get(position));
    CommonUtils.scrollHack(holder.getBinding().getRoot().findViewById(R.id.edt_comment));
  }

  @Override
  public int getItemCount() {
    return mItems.size();
  }

  public void setItems(List<Item> pItems) {
    mItems.clear();
    this.mItems.addAll(pItems);
    notifyDataSetChanged();
  }

  @Override
  public int getItemViewType(int position) {
    Item component = mItems.get(position);
    switch (component.getType().toUpperCase()) {
      case "PHOTO":
        return PHOTO;
      case "SINGLE_CHOICE":
        return SINGLE_CHOICE;
      case "COMMENT":
        return COMMENT;
    }
    return 0;
  }

  /**
   * Toggles editText visibility
   *
   * @param editText EditText
   * @param switchView Switch
   * @param item Item
   */
  public void toggleVisibility(EditText editText, Switch switchView, Item item) {
    if (switchView.isChecked()) {
      editText.setVisibility(View.VISIBLE);
    } else {
      item.setCurrentValue(null);
      editText.setVisibility(View.GONE);
      notifyItemChanged(mItems.indexOf(item));
    }
  }

  /**
   * Sets Navigator class
   *
   * @param navigator Navigator
   */
  public void setNavigator(MainNavigator navigator) {
    this.navigator = navigator;
  }

  /**
   * Action on image click
   *
   * @param item Item
   */
  public void imageClick(Item item) {
    if (item.getCurrentValue() != null) {
      navigator.openImage(item);
    } else {
      navigator.openCamera(item);
    }
  }

  /**
   * Clear current Image
   *
   * @param item Item
   */
  public void clearCurrentImage(Item item) {
    if (item.getCurrentValue() != null) {
      ImageUtils.deleteImage(item.getCurrentValue());
      item.setCurrentValue(null);
      notifyItemChanged(mItems.indexOf(item));
    }
  }
}