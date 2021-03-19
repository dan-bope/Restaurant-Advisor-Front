package fr.dbope.restaurantadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecupereRestaurantsActivity extends AppCompatActivity {

    private List<RecupereRestaurantsResponse> recupereRestaurantsResponseList = new ArrayList<>();

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupere_restaurants);

        gridView = findViewById(R.id.gridView);

        getAllRestaurants();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(RecupereRestaurantsActivity.this, RestaurantClickedActivity.class).putExtra("data",recupereRestaurantsResponseList.get(position)));
                //String message = "Name : "+recupereRestaurantsResponseList.get(position).getName();
                //Toast.makeText(RecupereRestaurantsActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAllRestaurants() {

        Call<List<RecupereRestaurantsResponse>> recupereRestaurantsResponse = ApiRecupereRestaurant.getRecupereRestaurants().getAllRestaurants();

        recupereRestaurantsResponse.enqueue(new Callback<List<RecupereRestaurantsResponse>>() {
            @Override
            public void onResponse(Call<List<RecupereRestaurantsResponse>> call, Response<List<RecupereRestaurantsResponse>> response) {

                if (response.isSuccessful()) {
                    String message = "Request successful ...";
                    Toast.makeText(RecupereRestaurantsActivity.this,message,Toast.LENGTH_LONG).show();

                    recupereRestaurantsResponseList = response.body();

                    customAdapter customAdapter = new customAdapter(recupereRestaurantsResponseList,RecupereRestaurantsActivity.this);
                    gridView.setAdapter(customAdapter);

                }else {
                    String message = "An error occurred try again later ...";
                    Toast.makeText(RecupereRestaurantsActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<RecupereRestaurantsResponse>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RecupereRestaurantsActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }

    public class customAdapter extends BaseAdapter {

        private List<RecupereRestaurantsResponse> recupereRestaurantsResponseList;
        private Context context;
        private LayoutInflater layoutInflater;

        public customAdapter(List<RecupereRestaurantsResponse> recupereRestaurantsResponseList, Context context) {
            this.recupereRestaurantsResponseList = recupereRestaurantsResponseList;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return recupereRestaurantsResponseList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.row_grid_items,parent,false);
            }

            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView textView = convertView.findViewById(R.id.textView);

            textView.setText(recupereRestaurantsResponseList.get(position).getName());

            GlideApp.with(context).load(recupereRestaurantsResponseList.get(position).getPhoto())
                    .into(imageView);

            return convertView;
        }
    }
}