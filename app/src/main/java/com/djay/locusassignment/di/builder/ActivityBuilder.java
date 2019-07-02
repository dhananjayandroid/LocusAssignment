package com.djay.locusassignment.di.builder;

import com.djay.locusassignment.ui.main.MainActivity;
import com.djay.locusassignment.ui.main.MainActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

  /**
   * This abstract method binds MainActivity to provide MainViewModel later
   *
   * @return MainActivity
   */
  @ContributesAndroidInjector(modules = MainActivityModule.class)
  abstract MainActivity bindMainActivity();
}
