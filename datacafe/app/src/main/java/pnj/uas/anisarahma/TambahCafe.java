package pnj.uas.anisarahma;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pnj.uas.anisarahma.utils.Config;
import pnj.uas.anisarahma.utils.VolleyMultipartRequest;

public class TambahCafe extends AppCompatActivity {
    EditText edtNama, edtPesanan, edtTanggal, edtKeterangan, edtImage;
    Button actionSimpan, actionImage, actionTanggal;
    ImageView imageView;

    public static final int REQUEST_PERMISSION = 1000;
    public static final int PICK_IMAGE_REQUEST = 1;
    public static final int CAMERA_REQUEST = 2;
    String filepath;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_input_kopi);

        edtNama = findViewById(R.id.edtNama);
        edtPesanan = findViewById(R.id.edtPesanan);
        edtTanggal = findViewById(R.id.edtTanggal);
        edtKeterangan = findViewById(R.id.edtKeterangan);
        edtImage = findViewById(R.id.edtImage);
        imageView = findViewById(R.id.image);
        actionImage = findViewById(R.id.actionImage);
        actionTanggal = findViewById(R.id.actionTanggal);
        actionSimpan = findViewById(R.id.actionSimpan);

        actionTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i, int i1, int i2) {
                        Calendar terpilih = Calendar.getInstance();
                        terpilih.set(Calendar.YEAR, i);
                        terpilih.set(Calendar.MONTH, i1);
                        terpilih.set(Calendar.DAY_OF_MONTH, i2);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                        edtTanggal.setText(sdf.format(terpilih.getTime()));
                    }
                };
                new DatePickerDialog(TambahCafe.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        actionSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNama.getText().toString().length()>0 &&
                        edtPesanan.getText().toString().length()>0 &&
                        edtTanggal.getText().toString().length()>0 &&
                        edtKeterangan.getText().toString().length()>0){
                    simpan();
                }
            }
        });

        actionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) &&
                        (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) &&
                        (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)){
                    ActivityCompat.requestPermissions(TambahCafe.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_PERMISSION);
//                    }
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(TambahCafe.this);
                    alert.setItems(new String[]{"Pilih Gambar", "Camera"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0){
                                showFileChooser();
                            }else {
                                showCamera();
                            }
                        }
                    });
                    alert.show();
                }
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config._LIST_CAFE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE : ", ""+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("cafe");
                    ID = jsonArray.length()+1;
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
        requestQueue.add(stringRequest);
    }

    void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Pilih Gambar"), PICK_IMAGE_REQUEST);
    }

    void showCamera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(TambahCafe.this, ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "pnj.uas.anisarahma.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data !=null){
            filepath = getPath(data.getData());
            edtImage.setText(filepath);
            Picasso.get().load(data.getData()).into(imageView);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                uploadBitmap(bitmap);
            }catch (IOException e){
                Toast.makeText(TambahCafe.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode==CAMERA_REQUEST && resultCode==RESULT_OK && data !=null){
            File file = new File(filepath);
            Uri photoURI = FileProvider.getUriForFile(this,
                    "pnj.uas.anisarahma.fileprovider",
                    file);
            Picasso.get().load(photoURI).into(imageView);

            Bitmap bitmap = BitmapFactory.decodeFile(filepath);
            uploadBitmap(bitmap);
        }
    }

    String getPath(Uri uri){
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();;

        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
                MediaStore.Images.Media._ID + "=?",new String[]{document_id},null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    byte[] getFileDataFromDrawable(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    void simpan(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Config._ADD_CAFE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(TambahCafe.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    if(jsonObject.getString("status").equals("OK")){
                        finish();
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("nama", edtNama.getText().toString());
                parameter.put("pesanan", edtPesanan.getText().toString());
                parameter.put("tanggal_pemesanan", edtTanggal.getText().toString());
                parameter.put("keterangan", edtKeterangan.getText().toString());
                return parameter;
            }
        };
        requestQueue.add(stringRequest);
    }
    void uploadBitmap(final Bitmap bitmap){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Config._UPLOAD_IMAGE, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try {
                    JSONObject jsonObject = new JSONObject(new String(response.data));
                    Toast.makeText(TambahCafe.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    if (jsonObject.getString("status").equals("OK")) {
//                        finish();
                    }
                } catch (JSONException ex) {
                    Toast.makeText(TambahCafe.this, ""+ ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahCafe.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("id", String.valueOf(ID));
                return parameter;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                HashMap<String, DataPart> data = new HashMap<>();
                data.put("image", new DataPart("image" + System.currentTimeMillis() + ".jpeg", getFileDataFromDrawable(bitmap)));
                return data;
            }
        };
        requestQueue.add(volleyMultipartRequest);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        filepath = image.getAbsolutePath();
        return image;
    }
}
