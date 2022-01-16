package pnj.uas.anisarahma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    //Set waktu lama splashscreen
    SharedPreferences sharedPreferences;
    private static int splashInterval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPreferences.getBoolean("isLogin", false)){
                    Intent done = new Intent(SplashScreen.this, DashboardActivity.class);
                    startActivity(done);
                    //jeda selesai Splashscreen
                    this.finish();
                }
                else {
                    Intent login = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(login);
                    //jeda selesai Splashscreen
                    this.finish();
                }
            }
            private void finish() {
                // TODO Auto-generated method stub
                SplashScreen.this.finish();
            }
        }, splashInterval);
    }
}
