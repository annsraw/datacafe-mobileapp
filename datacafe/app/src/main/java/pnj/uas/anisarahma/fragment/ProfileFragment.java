package pnj.uas.anisarahma.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pnj.uas.anisarahma.MainActivity;
import pnj.uas.anisarahma.R;


public class ProfileFragment extends Fragment {
    TextView txtEmail, txtNim, txtNama, txtKelas;
    Button btnLogout;
    SharedPreferences preferences;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtEmail   = view.findViewById(R.id.txtEmail);
        txtNim     = view.findViewById(R.id.txtNim);
        txtNama    = view.findViewById(R.id.txtNama);
        txtKelas   = view.findViewById(R.id.txtKelas);

        preferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        txtEmail.setText(preferences.getString("email", ""));
        txtNim.setText(preferences.getString("nim", ""));
        txtNama.setText(preferences.getString("nama", ""));
        txtKelas.setText(preferences.getString("kelas", ""));
    }


    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        btnLogout  = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
            return view;
    }
}
