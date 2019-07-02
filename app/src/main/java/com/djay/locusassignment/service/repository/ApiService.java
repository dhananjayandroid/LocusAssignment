package com.djay.locusassignment.service.repository;

import com.djay.locusassignment.service.model.Item;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

  @GET("items/list")
  Call<List<Item>> getProjectList();
}