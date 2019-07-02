package com.djay.locusassignment.utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.djay.locusassignment.R;
import com.djay.locusassignment.service.model.Item;
import com.djay.locusassignment.ui.main.adapter.OptionsAdapter;
import java.io.File;

/**
 * Utility class related to binding. Contains binding-adapter methods
 */
public class BindingUtils {

  /**
   * sets adapter to options RecyclerView
   *
   * @param recyclerView RecyclerView
   * @param item Item
   */
  @BindingAdapter({"setOptionsAdapter"})
  public static void setOptionsAdapter(RecyclerView recyclerView, Item item) {
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
    layoutManager.setOrientation(RecyclerView.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(new OptionsAdapter(item));
  }

  /**
   * Load Images with Glide
   *
   * @param imageView ImageView
   * @param item Item
   */
  @BindingAdapter({"loadImage"})
  public static void loadImage(ImageView imageView, Item item) {
    if (item.getCurrentValue() != null) {
      Glide.with(imageView.getContext())
          .load(new File(item.getCurrentValue()))
          .apply(new RequestOptions()
              .optionalFitCenter()
              .diskCacheStrategy(DiskCacheStrategy.ALL))
          .into(imageView);
    } else {
      imageView.setImageResource(R.mipmap.ic_launcher);
    }
  }
}
