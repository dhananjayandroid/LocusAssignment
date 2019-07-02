package com.djay.locusassignment.service.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.djay.locusassignment.service.model.Item;
import java.util.List;
import javax.inject.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ProjectRepository {

  private final ApiService mApiService;

  public ProjectRepository(ApiService apiService) {
    mApiService = apiService;
  }

  /**
   * Provides items list
   *
   * @return LiveData of List of items
   */
  public LiveData<List<Item>> getProjectList() {
    final MutableLiveData<List<Item>> data = new MutableLiveData<>();

    mApiService.getProjectList().enqueue(new Callback<List<Item>>() {
      @Override
      public void onResponse(@NonNull Call<List<Item>> call,
          @NonNull Response<List<Item>> response) {
        data.setValue(response.body());
      }

      @Override
      public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {
        t.printStackTrace();
      }
    });

    return data;
  }

}