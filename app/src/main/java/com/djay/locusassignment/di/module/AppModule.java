package com.djay.locusassignment.di.module;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

  /**
   * The method returns Application context
   */
  @Provides
  @Singleton
  Context provideContext(Application application) {
    return application;
  }
}
