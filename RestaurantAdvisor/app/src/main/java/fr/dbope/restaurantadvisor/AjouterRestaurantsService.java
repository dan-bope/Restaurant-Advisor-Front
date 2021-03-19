package fr.dbope.restaurantadvisor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AjouterRestaurantsService {
    @POST("restaurants")
    Call<AjouterRestaurantsResponse> AjouterRestaurants(@Body AjouterRestaurantsRequest ajouterRestaurantsRequest);
}
