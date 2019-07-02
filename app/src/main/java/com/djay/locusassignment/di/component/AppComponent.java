package com.djay.locusassignment.di.component;

import android.app.Application;
import com.djay.locusassignment.MyApplication;
import com.djay.locusassignment.di.builder.ActivityBuilder;
import com.djay.locusassignment.di.module.ApiModule;
import com.djay.locusassignment.di.module.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

/**
 * We mark this interface with the @Component annotation. And we define all the modules that can be
 * injected. Note that we provide AndroidSupportInjectionModule.class here. This class was not
 * created by us. Provides our activities and fragments with given module.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class,
    AppModule.class, ActivityBuilder.class, ApiModule.class})
public interface AppComponent {

  void inject(MyApplication app);

  /**
   * We will call this builder interface from our custom Application class. This will set our
   * application object to the AppComponent. So inside the AppComponent the application instance is
   * available. So this application instance can be accessed by our modules such as ApiModule when
   * needed
   */
  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}
