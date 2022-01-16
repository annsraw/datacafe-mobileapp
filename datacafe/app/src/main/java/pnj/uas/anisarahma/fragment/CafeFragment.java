package pnj.uas.anisarahma.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pnj.uas.anisarahma.DetailCafeActivity;
import pnj.uas.anisarahma.R;
import pnj.uas.anisarahma.TambahCafe;
import pnj.uas.anisarahma.adapter.AdapterCafe;
import pnj.uas.anisarahma.model.ModelCafe;
import pnj.uas.anisarahma.utils.Config;

public class CafeFragment extends Fragment {
    ListView listView;
    AdapterCafe adapterCafe;

    public CafeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cafe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);
        adapterCafe = new AdapterCafe(getActivity(), R.layout.item_list_cafe);
        listView.setAdapter(adapterCafe);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelCafe modelCafe = (ModelCafe) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getActivity(), DetailCafeActivity.class);
                intent.putExtra("id", modelCafe.getId());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                final ModelCafe modelCafe = (ModelCafe) parent.getAdapter().getItem(position);
                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_delete).setTitle("Hapus")
                        .setMessage("Menghapus data "+ adapterCafe.getItem(position).getNama()+"?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteData(adapterCafe.getItem(position).getId());
                            }
                        }).setNegativeButton("Tidak",null).show();
                return true;
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent input = new Intent(getActivity(), TambahCafe.class);
                startActivity(input);
            }
        });
    }

    private void deleteData(String id) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Config._DROP_CAFE + "?id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    if(jsonObject.getString("status").equals("OK")){
                        onResume();
                    }
                } catch (JSONException e) {
                    Log.e("RESPONSE : ", ""+response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE : ", ""+error);
            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResume() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config._LIST_CAFE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE : ", ""+response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("cafe");

                    ArrayList<ModelCafe> datas = new ArrayList<>();
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject item = jsonArray.getJSONObject(i);

                        ModelCafe modelCafe = new ModelCafe();
                        modelCafe.setId(item.getString("id"));
                        modelCafe.setNama(item.getString("nama"));
                        modelCafe.setPesanan(item.getString("pesanan"));
                        modelCafe.setTanggal_pemesanan(item.getString("tanggal_pemesanan"));
                        modelCafe.setKeterangan(item.getString("keterangan"));
                        datas.add(modelCafe);
                    }

                    adapterCafe.clear();
                    adapterCafe.addAll(datas);
                    adapterCafe.notifyDataSetChanged();

                }catch (JSONException ex){
                    Log.e("RESPONSE", ""+ ex.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE : ", ""+error.getMessage());
            }
        });
        queue.add(stringRequest);
        super.onResume();
    }
}
