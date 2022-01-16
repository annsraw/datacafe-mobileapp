package pnj.uas.anisarahma;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pnj.uas.anisarahma.db.DatabasePelanggan;
import pnj.uas.anisarahma.fragment.PelangganFragment;

public class TambahDataPelanggan extends AppCompatActivity implements View.OnClickListener {
    EditText edtId, edtNama, edtPesanan, edtJumlah, edtKeterangan;
    Button actionSimpan;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_pelanggan);
        edtId           = findViewById(R.id.edtId);
        edtNama         = findViewById(R.id.edtNama);
        edtPesanan        = findViewById(R.id.edtPesanan);
        edtJumlah     = findViewById(R.id.edtJumlah);
        edtKeterangan   = findViewById(R.id.edtKeterangan);
        actionSimpan    = findViewById(R.id.actionSimpan);
        actionSimpan.setOnClickListener(TambahDataPelanggan.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
                case R.id.actionSimpan:
                ContentValues newValues = new ContentValues();
                newValues.put("id", Integer.parseInt(edtId.getText().toString()));
                newValues.put("nama", edtNama.getText().toString());
                newValues.put("pesanan", edtPesanan.getText().toString());
                newValues.put("jumlah", edtJumlah.getText().toString());
                newValues.put("keterangan", edtKeterangan.getText().toString());

                database = new DatabasePelanggan(this).getWritableDatabase();

                long insert = database.insert("tb_pelanggan", null, newValues);
                Intent intent = new Intent(this, PelangganFragment.class);

                if (insert != -1) {
                    Toast.makeText(this, "Simpan Berhasil", Toast.LENGTH_SHORT).show();
                    this.finish();
                } else {
                    Toast.makeText(this, "Simpan Gagal", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
        }
    }
}
