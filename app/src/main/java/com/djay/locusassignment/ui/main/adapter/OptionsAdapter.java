package com.djay.locusassignment.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.djay.locusassignment.BR;
import com.djay.locusassignment.R;
import com.djay.locusassignment.service.model.Item;
import com.djay.locusassignment.utils.CommonUtils;
import java.util.ArrayList;

/**
 * Single selection Adapter for choice list
 */
public class OptionsAdapter extends RecyclerView.Adapter<ViewHolder> {

  private final ArrayList<String> mItems;
  private final Item mFullItem;

  public OptionsAdapter(Item pItems) {
    this.mFullItem = pItems;
    this.mItems = (ArrayList<String>) pItems.getDataMap().getOptions();
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.getBinding().setVariable(BR.myAdapter, this);
    holder.getBinding().setVariable(BR.position, position);
    holder.getBinding()
        .setVariable(BR.lastPosition, CommonUtils.convertPosition(mFullItem.getCurrentValue()));
    holder.getBinding().setVariable(BR.item, mItems.get(position));
  }

  @Override
  public int getItemCount() {
    return mItems.size();
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup pParent, int viewType) {
    LayoutInflater lLayoutInflater = LayoutInflater.from(pParent.getContext());
    return new ViewHolder<>(DataBindingUtil
        .inflate(lLayoutInflater, R.layout.item_choice_option, pParent, false));
  }

  /**
   * On check change changes current value
   *
   * @param cbSelection CheckBox
   * @param position position
   */
  public void onCheckChange(CheckBox cbSelection, int position) {
    if (!cbSelection.isChecked()) {
      mFullItem.setCurrentValue("" + position);
    } else {
      mFullItem.setCurrentValue("-1");
    }
    notifyDataSetChanged();
  }
}