package fr.dbope.restaurantadvisor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("auth")
    Call<AuthResponse> AuthLogin(@Body AuthRequest authRequest);
}
