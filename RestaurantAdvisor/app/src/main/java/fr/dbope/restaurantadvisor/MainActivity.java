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

public class MainActivity extends AppCompatActivity {


    EditText login, email, name, firstname, age, password;
    Button btn_register, btn_restaurants, btn_ajouterRestaurants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        firstname = findViewById(R.id.firstname);
        age = findViewById(R.id.age);
        password = findViewById(R.id.password);
        btn_register = findViewById(R.id.btn_register);
        btn_restaurants = findViewById(R.id.btn_restaurants);
        btn_ajouterRestaurants = findViewById(R.id.btn_ajouterRestaurants);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(createRequest());
            }
        });

        btn_restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecupereRestaurantsActivity();
            }
        });

        btn_ajouterRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjouterRestaurants();
            }
        });
    }


    public UserRequest createRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setLogin(login.getText().toString());
        userRequest.setEmail(email.getText().toString());
        userRequest.setName(name.getText().toString());
        userRequest.setFirstname(firstname.getText().toString());
        userRequest.setAge(age.getText().toString());
        userRequest.setPassword(password.getText().toString());

        return userRequest;
    }

    public void saveUser(UserRequest userRequest) {

        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUsers(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Saved successfuly", Toast.LENGTH_LONG).show();
                    AUthActivity();
                } else {
                    Toast.makeText(MainActivity.this, "Request failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Saved successfuly"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void AUthActivity() {
        Intent intent = new Intent(this,AuthActivity.class);
        startActivity(intent);
    }

    private void RecupereRestaurantsActivity() {
        Intent intent = new Intent(this, fr.dbope.restaurantadvisor.RecupereRestaurantsActivity.class);
        startActivity(intent);
    }

    private void AjouterRestaurants() {
        Intent intent = new Intent(this,AjouterRestaurantsActivity.class);
        startActivity(intent);
    }
}