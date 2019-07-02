package com.djay.locusassignment.ui;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import java.lang.ref.WeakReference;

/**
 * Abstract ViewModel class, binding with Navigator classes
 * @param <N>
 */
@SuppressWarnings("unused")
public abstract class BaseAndroidViewModel<N> extends AndroidViewModel {

  private WeakReference<N> mNavigator;

  public BaseAndroidViewModel(
      @NonNull Application application) {
    super(application);
  }


  public N getNavigator() {
    return mNavigator.get();
  }

  public void setNavigator(N navigator) {
    this.mNavigator = new WeakReference<>(navigator);
  }
}
