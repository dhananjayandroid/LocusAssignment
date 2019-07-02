package com.djay.locusassignment.ui.main.adapter;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Generic ViewModel class
 */
class ViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {

  private final V v;   // ViewDataBinding

  /**
   * ViewHolder constructor with ViewDataBinding
   */
  ViewHolder(V v) {
    super(v.getRoot());
    this.v = v;
  }

  /**
   * Provides current binding
   *
   * @return ViewDataBinding
   */
  V getBinding() {
    return v;
  }
}