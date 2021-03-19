package fr.dbope.restaurantadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjouterRestaurantsActivity extends AppCompatActivity {

    EditText name_restaurant, description_restaurant, grade_restaurant, localisation_restaurant, phone_number, web_site, hours, photo_restaurant;
    Button btn_Add_Restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_restaurants);

        name_restaurant = findViewById(R.id.name_restaurant);
        description_restaurant = findViewById(R.id.description_restaurant);
        grade_restaurant = findViewById(R.id.grade_restaurant);
        localisation_restaurant = findViewById(R.id.localisation_restaurant);
        phone_number = findViewById(R.id.phone_number);
        web_site = findViewById(R.id.web_site);
        hours = findViewById(R.id.hours);
        photo_restaurant = findViewById(R.id.photo_restaurant);

        btn_Add_Restaurants = findViewById(R.id.btn_Add_Restaurants);

        btn_Add_Restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjouterRestaurants(createRequest());
            }
        });
    }

    public AjouterRestaurantsRequest createRequest() {
        AjouterRestaurantsRequest ajouterRestaurantsRequest = new AjouterRestaurantsRequest();
        ajouterRestaurantsRequest.setName(name_restaurant.getText().toString());
        ajouterRestaurantsRequest.setDescription(description_restaurant.getText().toString());
        ajouterRestaurantsRequest.setGrade(grade_restaurant.getText().toString());
        ajouterRestaurantsRequest.setLocalization(localisation_restaurant.getText().toString());
        ajouterRestaurantsRequest.setPhone_number(phone_number.getText().toString());
        ajouterRestaurantsRequest.setWebsite(web_site.getText().toString());
        ajouterRestaurantsRequest.setHours(hours.getText().toString());
        ajouterRestaurantsRequest.setPhoto(photo_restaurant.getText().toString());

        return ajouterRestaurantsRequest;
    }

    public void AjouterRestaurants(AjouterRestaurantsRequest ajouterRestaurantsRequest){
        Call<AjouterRestaurantsResponse> ajouterRestaurantsResponseCall = ApiAjouterRestaurantsClient.getRestaurantsService().AjouterRestaurants(ajouterRestaurantsRequest);
        ajouterRestaurantsResponseCall.enqueue(new Callback<AjouterRestaurantsResponse>() {
            @Override
            public void onResponse(Call<AjouterRestaurantsResponse> call, Response<AjouterRestaurantsResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AjouterRestaurantsActivity.this, "Saved successfuly", Toast.LENGTH_LONG).show();
                    RecupereRestaurantsActivity();

                } else {
                    Toast.makeText(AjouterRestaurantsActivity.this, "Request failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AjouterRestaurantsResponse> call, Throwable t) {
                Toast.makeText(AjouterRestaurantsActivity.this, "Saved successfuly"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void RecupereRestaurantsActivity() {
        Intent intent = new Intent(this, fr.dbope.restaurantadvisor.RecupereRestaurantsActivity.class);
        startActivity(intent);
    }
}