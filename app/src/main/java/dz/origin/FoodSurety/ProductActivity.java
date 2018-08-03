package dz.origin.FoodSurety;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import dz.origin.origin.R;

public class ProductActivity extends AppCompatActivity {
    public static String PICTURE;
    public static String BRAND;
    public static String DATE;
    public static String MAXT;
    public static String MINT;
    public static String TRANSPORT;
    public static String FARM;

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
            brand.setText(BRAND);
            date.setText(DATE);
            maxt.setText(MAXT);
            mint.setText(MINT);
            transport.setText(TRANSPORT);
            farm.setText(FARM);
            picture.setImageResource(R.drawable.meat);
        }
    }
}
