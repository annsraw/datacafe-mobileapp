package pnj.uas.anisarahma.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import pnj.uas.anisarahma.DashboardActivity;
import pnj.uas.anisarahma.R;
import pnj.uas.anisarahma.db.DatabasePelanggan;


public class DetailPelanggan extends Fragment implements View.OnClickListener {
    EditText edtId, edtNama, edtPesanan, edtJumlah, edtKeterangan;
    Button actionUpdate, actionDelete;
    SQLiteDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_pelanggan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtId           = view.findViewById(R.id.edtId);
        edtNama         = view.findViewById(R.id.edtNama);
        edtPesanan         = view.findViewById(R.id.edtPesanan);
        edtJumlah      = view.findViewById(R.id.edtJumlah);
        edtKeterangan   = view.findViewById(R.id.edtKeterangan);

        actionUpdate = view.findViewById(R.id.actionUpdate);
        actionDelete = view.findViewById(R.id.actionDelete);

        actionUpdate.setOnClickListener(DetailPelanggan.this);
        actionDelete.setOnClickListener(DetailPelanggan.this);

        Bundle data = getArguments();
        edtId.setText(data.getString("id",""));
        edtNama.setText(data.getString("nama",""));
        edtPesanan.setText(data.getString("pesanan",""));
        edtJumlah.setText(data.getString("jumlah",""));
        edtKeterangan.setText(data.getString("keterangan",""));
    }

    @Override
    public void onClick(View v) {
        final Calendar calendar = Calendar.getInstance();
        switch (v.getId()){
            case R.id.actionUpdate:
                ContentValues editValues = new ContentValues();
                editValues.put("id", edtId.getText().toString());
                editValues.put("nama", edtNama.getText().toString());
                editValues.put("pesanan", edtPesanan.getText().toString());
                editValues.put("jumlah", edtJumlah.getText().toString());
                editValues.put("keterangan", edtKeterangan.getText().toString());

                database = new DatabasePelanggan(getActivity()).getWritableDatabase();
                long update = database.update("tb_pelanggan", editValues, "id=? or nama=?", new String[] {""+ edtId.getText().toString(), ""+ edtId.getText().toString()});
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                if(update!=-1) {
                    Toast.makeText(getActivity(), "Update Berhasil", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Update Gagal", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.actionDelete:
                database = new DatabasePelanggan(getActivity()).getWritableDatabase();
                long delete = database.delete("tb_pelanggan", "id=? or nama=?",new String[] {edtId.getText().toString(), edtNama.getText().toString()});
                Intent del = new Intent(getActivity(), DashboardActivity.class);
                if(delete!=-1) {
                    Toast.makeText(getActivity(), "Hapus Berhasil", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Hapus Gagal", Toast.LENGTH_SHORT).show();
                }
                startActivity(del);
                getActivity().finish();
                break;
        }
    }
}


