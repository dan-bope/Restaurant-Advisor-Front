package fr.dbope.restaurantadvisor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecupereRestaurantsService {

    @GET("restaurants")
    Call<List<RecupereRestaurantsResponse>> getAllRestaurants();
}
