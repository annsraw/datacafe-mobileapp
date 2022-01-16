package pnj.uas.anisarahma.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pnj.uas.anisarahma.R;
import pnj.uas.anisarahma.model.PelangganData;


public class AdapterPelanggan extends ArrayAdapter<PelangganData> {
    Context context;
    int resource;


    public AdapterPelanggan(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);

            holder.img = convertView.findViewById(R.id.img);
            holder.txtnama = convertView.findViewById(R.id.txtnama);
            holder.txtpesanan = convertView.findViewById(R.id.txtpesanan);

            convertView.setTag(holder);
        }else {

            holder=(Holder) convertView.getTag();
        }

        holder.txtnama.setText(getItem(position).getNama());
        holder.txtpesanan.setText(getItem(position).getPesanan());

        return convertView;

    }

    public void setNotifyOnChange() {
    }

    class Holder {
        ImageView img;
        TextView txtnama,txtpesanan;
    }
}
