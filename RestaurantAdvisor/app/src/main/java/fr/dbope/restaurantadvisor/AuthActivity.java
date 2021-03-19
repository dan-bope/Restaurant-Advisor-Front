package fr.dbope.restaurantadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    EditText email_auth, password_auth;
    Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        email_auth = findViewById(R.id.email_auth);
        password_auth = findViewById(R.id.password_auth);
        btn_signin = findViewById(R.id.btn_signin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email_auth.getText().toString()) || TextUtils.isEmpty(password_auth.getText().toString())) {
                    Toast.makeText(AuthActivity.this, "Email / Password Required", Toast.LENGTH_LONG).show();
                } else {
                    AUthentificate();
                }
            }
        });
    }

    public void AUthentificate() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email_auth.getText().toString());
        authRequest.setPassword(password_auth.getText().toString());
        Call<AuthResponse> authResponseCall = ApiAuthClient.getAuthService().AuthLogin(authRequest);
        authResponseCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AuthActivity.this, "Authentificate", Toast.LENGTH_LONG).show();
                    RecupereRestaurantsActivity();
                } else {
                    Toast.makeText(AuthActivity.this, "Authentificate failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(AuthActivity.this, "Saved successfuly"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void RecupereRestaurantsActivity() {
        Intent intent = new Intent(this,RecupereRestaurantsActivity.class);
        startActivity(intent);
    }
}