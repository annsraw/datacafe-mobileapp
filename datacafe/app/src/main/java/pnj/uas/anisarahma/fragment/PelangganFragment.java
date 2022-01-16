package pnj.uas.anisarahma.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pnj.uas.anisarahma.DashboardActivity;
import pnj.uas.anisarahma.DetailPelangganActivity;
import pnj.uas.anisarahma.R;
import pnj.uas.anisarahma.TambahDataPelanggan;
import pnj.uas.anisarahma.adapter.AdapterPelanggan;
import pnj.uas.anisarahma.db.DatabasePelanggan;
import pnj.uas.anisarahma.model.PelangganData;


public class PelangganFragment extends Fragment {
    ListView listView;
    AdapterPelanggan adapterPelanggan;
    SQLiteDatabase database;

    public PelangganFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pelanggan, container, false);
        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listview);
        adapterPelanggan = new AdapterPelanggan(getActivity(), R.layout.item_list_view_pelanggan);
        listView.setAdapter(adapterPelanggan);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long L) {
                PelangganData data = (PelangganData) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(getActivity(), DetailPelangganActivity.class);
                intent.putExtra("id", data.getId());
                intent.putExtra("nama",data.getNama());
                intent.putExtra("pesanan",data.getPesanan());
                intent.putExtra("jumlah",data.getJumlah());
                intent.putExtra("keterangan",data.getKeterangan());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                final PelangganData pelangganData = (PelangganData) parent.getAdapter().getItem(position);
                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_delete).setTitle("Hapus")
                        .setMessage("Menghapus data "+ pelangganData.getNama()+"?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteData(pelangganData.getId());
                                getActivity().finish();
                                startActivity(PelangganFragment.this.getActivity().getIntent());
                            }
                        }).setNegativeButton("Tidak",null).show();
                return true;
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent input = new Intent(getActivity(), TambahDataPelanggan.class);
                startActivity(input);
            }
        });

        buatData();
    }

    private void deleteData(String id) {
        database = new DatabasePelanggan(getActivity()).getWritableDatabase();
        long delete = database.delete("tb_pelanggan", "id=?",new String[]{id});
        Intent del = new Intent(getActivity(), DashboardActivity.class);
        if(delete!=-1) {
            Toast.makeText(getActivity(), "Hapus Berhasil", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Hapus Gagal", Toast.LENGTH_SHORT).show();
        }
        startActivity(del);
        getActivity().finish();
    }

    void buatData() {
        ArrayList<PelangganData> data = new ArrayList<>();
        database = new DatabasePelanggan(getActivity()).getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM tb_pelanggan", null);
        if (cursor.moveToFirst()){
            do {
                PelangganData itemPelanggan = new PelangganData();
                itemPelanggan.setId(""+cursor.getInt(0));
                itemPelanggan.setNama(cursor.getString(1));
                itemPelanggan.setPesanan(cursor.getString(2));
                itemPelanggan.setJumlan(cursor.getString(3));
                itemPelanggan.setKeterangan(cursor.getString(4));
                data.add(itemPelanggan);
            }
            while (cursor.moveToNext());
        }
        database.close();
        adapterPelanggan.addAll(data);
        adapterPelanggan.setNotifyOnChange();
    }
}
