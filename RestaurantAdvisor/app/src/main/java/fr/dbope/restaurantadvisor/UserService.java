package fr.dbope.restaurantadvisor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("register")
   Call<UserResponse> saveUsers(@Body UserRequest userRequest);
}
