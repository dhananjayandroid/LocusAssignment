package com.djay.locusassignment.ui.main;

import android.app.Application;
import com.djay.locusassignment.service.repository.ProjectRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

  @Provides
  MainViewModel provideMainActivityModule(Application application,
      ProjectRepository projectRepository) {
    return new MainViewModel(application, projectRepository);
  }
}
