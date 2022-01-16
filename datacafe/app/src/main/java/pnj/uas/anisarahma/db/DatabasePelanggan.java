package pnj.uas.anisarahma.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabasePelanggan extends SQLiteOpenHelper {
    public static  String _NAMA_DATABASE = "db_pelanggan";
    public  static int _VERSION = 1;
    public static String tb_pelanggan =
            "CREATE TABLE tb_pelanggan (" +
                    "id INTEGER PRIMARY KEY, " +
                    "nama TEXT, " +
                    "pesanan TEXT, " +
                    "jumlah TEXT, " +
                    "keterangan TEXT)";

    public DatabasePelanggan(@Nullable Context context) {
        super(context, _NAMA_DATABASE, null, _VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tb_pelanggan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE tb_pelanggan");
            db.execSQL(tb_pelanggan);
        }
    }
}