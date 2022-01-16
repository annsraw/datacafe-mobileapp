package pnj.uas.anisarahma.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pnj.uas.anisarahma.R;
import pnj.uas.anisarahma.model.ModelCafe;

public class AdapterCafe extends ArrayAdapter<ModelCafe> {
    Context context;
    int resource;

    public AdapterCafe(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    class Holder{
        TextView txtPemesan, txtPesanan, txtKeterangan;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder;

        if (convertView==null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            holder.txtPemesan = convertView.findViewById(R.id.txtPemesan);
            holder.txtPesanan = convertView.findViewById(R.id.txtPesanan);
            holder.txtKeterangan = convertView.findViewById(R.id.txtKeterangan);

            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        holder.txtPemesan.setText("Nama : " + getItem(position).getNama());
        holder.txtPesanan.setText("Pesanan : " + getItem(position).getPesanan());
        holder.txtKeterangan.setText("Keterangan : " + getItem(position).getKeterangan());

        return convertView;
    }
}