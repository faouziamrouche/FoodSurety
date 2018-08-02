package dz.origin.FoodSurety;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import dz.origin.origin.R;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ImageView picture = findViewById(R.id.picture);
        TextView brand = findViewById(R.id.brand);
        TextView date = findViewById(R.id.date);
        TextView maxt = findViewById(R.id.maxt);
        TextView mint= findViewById(R.id.mint);
        TextView transport= findViewById(R.id.transport);
        TextView farm= findViewById(R.id.farm);
        if (HomeActivity.scanResult.equals("2")){
            brand.setText("Charal");
            date.setText("1st August 2018 : 3:23pm");
            maxt.setText("23 C");
            mint.setText("2 C");
            transport.setText("Al Oubaid Lmtd");
            farm.setText("4353");
            picture.setImageResource(R.drawable.meat);
        }
    }
}
