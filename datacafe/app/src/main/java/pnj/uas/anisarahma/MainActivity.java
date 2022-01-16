package pnj.uas.anisarahma;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    Button btnLogin;
    SharedPreferences Preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Preferences = getSharedPreferences("login", MODE_PRIVATE);

        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUsername);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String pass     = txtPassword.getText().toString();

                if (username.equals("anisarahma") && pass.equals("ans0311") ){
                    SharedPreferences.Editor editor = Preferences.edit();
                    editor.putBoolean("isLogin", true);
                    editor.putString("username","1");
                    editor.putString("email","annsrahma.w@gmail.com");
                    editor.putString("nim","4817070379");
                    editor.putString("nama","Anisa Rahmawati");
                    editor.putString("kelas","TI-6C PNJ-ITKJ");
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "Id/Password Salah", Toast.LENGTH_LONG).show();
                    int red = Color.parseColor("#DC143C");
                    txtUsername.setBackgroundColor(red);
                    txtPassword.setBackgroundColor(red);
                }
            }
        });
    }

    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Ingin Keluar?");
        alert.setTitle("Exit?");
        alert.setIcon(android.R.drawable.ic_dialog_alert);
        alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
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
