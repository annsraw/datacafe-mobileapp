package pnj.uas.anisarahma;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pnj.uas.anisarahma.fragment.CafeFragment;
import pnj.uas.anisarahma.fragment.MapsFragment;
import pnj.uas.anisarahma.fragment.BeritaFragment;
import pnj.uas.anisarahma.fragment.PelangganFragment;
import pnj.uas.anisarahma.fragment.ProfileFragment;


public class DashboardActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        preferences = getSharedPreferences("login", MODE_PRIVATE);

        MapsFragment home = new MapsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, home);
        transaction.commit();

        BottomNavigationView navigationView = findViewById(R.id.menu_bottom);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigationView.setItemIconTintList(null);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionTambah) {
            Intent intent = new Intent(this, TambahDataPelanggan.class);
            startActivity(intent);
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.home:
                    MapsFragment home = new MapsFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, home);
                    transaction.commit();
                    return true;

                case R.id.berita:
                    BeritaFragment berita = new BeritaFragment();
                    FragmentTransaction transactionartikel = getSupportFragmentManager().beginTransaction();
                    transactionartikel.replace(R.id.content, berita);
                    transactionartikel.commit();
                    return true;

                case R.id.cafe:
                    CafeFragment cafe = new CafeFragment();
                    FragmentTransaction transactioncafe = getSupportFragmentManager().beginTransaction();
                    transactioncafe.replace(R.id.content, cafe);
                    transactioncafe.commit();
                    return true;

                case R.id.pelanggan:
                    PelangganFragment pelanggan = new PelangganFragment();
                    FragmentTransaction transactionpelanggan = getSupportFragmentManager().beginTransaction();
                    transactionpelanggan.replace(R.id.content, pelanggan);
                    transactionpelanggan.commit();
                    return true;

                case R.id.profile:
                    ProfileFragment profile = new ProfileFragment();
                    FragmentTransaction transactionprofile = getSupportFragmentManager().beginTransaction();
                    transactionprofile.replace(R.id.content, profile);
                    transactionprofile.commit();
                    return true;
            }
            return false;
        }
    };

    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Ingin Keluar?");
        alert.setTitle("Exit?");
        alert.setIcon(android.R.drawable.ic_dialog_alert);
        alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DashboardActivity.super.onBackPressed();
                finish();
                System.exit(0);
            }
        });
        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
}
