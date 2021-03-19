package fr.dbope.restaurantadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantClickedActivity extends AppCompatActivity {

    RecupereRestaurantsResponse recupereRestaurantsResponse;

    TextView selectedName, selectedDescription, selectedGrade, selectedLocalisation, selectedPhone_number, selectedWeb_site;
    ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_clicked);

        selectedName = findViewById(R.id.selectedName);
        selectedDescription = findViewById(R.id.selectedDescription);
        selectedGrade = findViewById(R.id.selectedGrade);
        selectedLocalisation = findViewById(R.id.selectedLocalisation);
        selectedPhone_number = findViewById(R.id.selectedPhone_number);
        selectedWeb_site = findViewById(R.id.selectedWeb_site);
        selectedImage = findViewById(R.id.selectedImage);

        Intent intent = getIntent();

        if (intent.getExtras() != null){
            recupereRestaurantsResponse = (RecupereRestaurantsResponse) intent.getSerializableExtra("data");

            //String message = "name : "+recupereRestaurantsResponse.getName();
            //Log.e("data",message);f
            selectedName.setText(recupereRestaurantsResponse.getName());
            selectedDescription.setText(recupereRestaurantsResponse.getDescription());
            selectedGrade.setText(recupereRestaurantsResponse.getGrade());
            selectedLocalisation.setText(recupereRestaurantsResponse.getLocalization());
            selectedPhone_number.setText(recupereRestaurantsResponse.getPhone_number());
            selectedWeb_site.setText(recupereRestaurantsResponse.getWebsite());

            GlideApp.with(this).load(recupereRestaurantsResponse.getPhoto()).override(200,100).into(selectedImage);
        }
    }
}