package pnj.uas.anisarahma;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import pnj.uas.anisarahma.fragment.DetailPelanggan;


public class DetailPelangganActivity extends AppCompatActivity {
    DetailPelanggan detailPelanggan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_detail);

        Bundle extras = getIntent().getExtras();
        detailPelanggan = new DetailPelanggan();

        if (extras!=null) {
            String id           = extras.getString("id","");
            String nama         = extras.getString("nama","");
            String pesanan         = extras.getString("pesanan","");
            String jumlah      = extras.getString("jumlah","");
            String keterangan   = extras.getString("keterangan","");

            Bundle data = new Bundle();
            data.putString("id",id);
            data.putString("nama",nama);
            data.putString("pesanan",pesanan);
            data.putString("jumlah",jumlah);
            data.putString("keterangan",keterangan);

            detailPelanggan.setArguments(data);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerLayout , detailPelanggan);
        ft.commit();
    }
}

