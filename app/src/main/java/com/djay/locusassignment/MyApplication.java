package com.djay.locusassignment;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.djay.locusassignment.di.component.DaggerAppComponent;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;

/**
 * My application class
 */
public class MyApplication extends Application implements HasActivityInjector {

  @Inject
  DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

  @Override
  public DispatchingAndroidInjector<Activity> activityInjector() {
    return activityDispatchingAndroidInjector;
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
  }

  @Override
  public void onCreate() {
    super.onCreate();

    // init Dagger for dependency injection
    DaggerAppComponent.builder()
        .application(this)
        .build()
        .inject(this);
  }
}