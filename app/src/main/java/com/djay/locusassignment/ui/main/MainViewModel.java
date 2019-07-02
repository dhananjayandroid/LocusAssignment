package com.djay.locusassignment.ui.main;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.djay.locusassignment.service.model.Item;
import com.djay.locusassignment.service.repository.ProjectRepository;
import com.djay.locusassignment.ui.BaseAndroidViewModel;
import java.util.List;

/**
 * ViewModel class for MainActivity
 */
public class MainViewModel extends BaseAndroidViewModel<MainNavigator> {

  private final LiveData<List<Item>> itemsListObservable;

  MainViewModel(@NonNull Application application, @NonNull ProjectRepository projectRepository) {
    super(application);
    itemsListObservable = projectRepository.getProjectList();
  }

  /**
   * Expose the LiveData Item query so the UI can observe it.
   */
  LiveData<List<Item>> getItemsListObservable() {
    return itemsListObservable;
  }
}
