package dz.origin.FoodSurety;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import dz.origin.origin.R;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final Activity homeActivity = this;
    public static String scanResult = "";
//    static MainContract mainContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Set the title and the subtitle
        Bundle b = getIntent().getExtras();
        String email = ""; // or other values
        if(b != null)
            email = b.getString("email");
        View headerView = navigationView.getHeaderView(0);
        TextView title = (TextView) headerView.findViewById(R.id.navHeaderTitleTextView);
        TextView subTitle = (TextView) headerView.findViewById(R.id.navHeaderSubtitleTextView);
        String[] separated = email.split("@");
        title.setText(separated[0]);
        subTitle.setText(email);

        Button scan_qr_code_button = (Button) drawer.findViewById(R.id.scan_qr_code_button);
        scan_qr_code_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(homeActivity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan Card");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        // in here we are trying to connect to the server
//        Web3j web3j = Web3j.build(new HttpService("http://b3f44438.ngrok.io"));
//        Credentials credentials = Credentials.create("7e232b9b5b5f64da32f796483b4590bf4fd8a0643e5c7f96f957913282de3f77");;
//        try {
//            mainContract = MainContract.deploy(
//                    web3j, credentials,
//                    new BigInteger("20000000000"),
//                    BigInteger.valueOf(6721975)).send();
////            mainContract.
//        } catch (Exception e) {
//            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null){
            if (result.getContents()==null) {
                Toast.makeText(this,"Scanning aborted",Toast.LENGTH_LONG).show();
            }
            else {
                scanResult = result.getContents();
//                try {
//                    EditText edt = (EditText) findViewById(R.id.address_edit_text);
//                    Web3j web3j = Web3j.build(new HttpService(edt.getText().toString()));
//                    Credentials credentials = Credentials.create("7e232b9b5b5f64da32f796483b4590bf4fd8a0643e5c7f96f957913282de3f77");;
//                    try {
//                        mainContract = MainContract.deploy(
//                                web3j, credentials,
//                                new BigInteger("20000000000"),
//                                BigInteger.valueOf(6721975)).send();
//                        mainContract.
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } catch (Exception e) {
//                    Toast.makeText(this, "here1", Toast.LENGTH_LONG).show();
//
//                    e.printStackTrace();
//                }

                if(scanResult.equals("1") || scanResult.equals("2") ){
                    Intent productIntent = new Intent(HomeActivity.this, ProductActivity.class);
                    startActivity(productIntent);
                }
                else    Toast.makeText(this, "Un produit non reconaissant ou non fiable!", Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_scan_qr_code) {
                IntentIntegrator integrator = new IntentIntegrator(homeActivity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan Card");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
        } else if (id == R.id.nav_stores) {
                Intent storesIntent = new Intent(HomeActivity.this, StoresActivity.class);
                startActivity(storesIntent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
