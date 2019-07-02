package com.djay.locusassignment.ui;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import dagger.android.AndroidInjection;

/**
 * Abstract Activity to provide base for dependency injection
 *
 * @param <T> ViewDataBinding
 * @param <V> BaseAndroidViewModel
 */
public abstract class InjectionActivity<T extends ViewDataBinding, V extends BaseAndroidViewModel> extends
    AppCompatActivity {

  private T mViewDataBinding;
  private V mViewModel;

  /**
   * Override for set binding variable
   *
   * @return variable id
   */
  protected abstract int getBindingVariable();

  /**
   * @return layout resource id
   */
  protected abstract
  @LayoutRes
  int getLayoutId();

  /**
   * Override for set view model
   *
   * @return view model instance
   */
  protected abstract V getViewModel();

  private void performDataBinding() {
    mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
    mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
    mViewDataBinding.executePendingBindings();
  }

  /**
   * Performs Dependency injection
   */
  private void performDependencyInjection() {
    try {
      AndroidInjection.inject(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void onCreate(Bundle pBundle) {
    performDependencyInjection();
    super.onCreate(pBundle);
    performDataBinding();
  }

  /**
   * Returns ViewDataBinding
   */
  protected T getViewDataBinding() {
    return mViewDataBinding;
  }
}
