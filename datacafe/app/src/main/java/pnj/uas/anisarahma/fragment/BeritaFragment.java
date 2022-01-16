package pnj.uas.anisarahma.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pnj.uas.anisarahma.DetailBeritaActivity;
import pnj.uas.anisarahma.R;
import pnj.uas.anisarahma.adapter.AdapterBerita;
import pnj.uas.anisarahma.model.Berita;

public class BeritaFragment extends Fragment {
    GridView gridView;
    AdapterBerita adapterBerita;

    public BeritaFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_berita, container, false);
        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.gridview);
        adapterBerita = new AdapterBerita(getActivity(), R.layout.item_list_berita);
        gridView.setAdapter(adapterBerita);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Berita data = (Berita) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getActivity(), DetailBeritaActivity.class);
                intent.putExtra("judul", data.getTitle());
                intent.putExtra("deskripsi", data.getDeskripsi());
                intent.putExtra("image", data.getImage());
                startActivity(intent);
            }
        });

        buatDataBerita();
    }

    private void buatDataBerita() {
        String[] judul = {
                "Hari Kopi Nasional, Berikut 6 Kopi Indonesia yang Mendunia",
                "Kopi Se-Indonesia : Mencicipi Racikan Kopi Kekinian",
                "Apa Bedanya Biji Kopi Robusta dan Arabika? Mulai dari Bentuk, Rasa, sampai Proses Pengolahan",
                "Apakah Minum Kopi Bahayakan Kesehatan Ginjal?",
                "Kopi Indonesia Bisa Harum di Kancah Dunia, asal...",
                "Tanpa Gula, Kopi Hitam Lebih Bermanfaat untuk Kesehatan"
        };

        String[] deskripsi = {
                "Jakarta - ndonesia dikenal sebagai salah satu penghasil biji kopi terbaik. Banyak biji kopi yang diekspor ke luar negeri dan jadi primadona. Pada momen Hari Kopi Nasional pada 11 Maret 2020, Kompas.com merangkum sejumlah biji kopi Indonesia yang mendunia.\n" +
                        "\n" +"Berikut enam kopi asal Indonesia yang dikenal dan disukai di dunia. Aceh Gayo Kopi Aceh Gayo jadi salah satu yang paling populer di dunia. Menurut Cindy Herlin Marta, Co-Founder Shoot Me In The Head yang juga seorang Licensed Q Arabica Grader, kopi ini jadi yang paling banyak diekspor dari Indonesia. Kopi Toraja Kopi Toraja asal Sulawesi jadi favorit masyarakat dari benua Eropa. Menurut William, karakter rasa kopi Toraja cukup berat, dengan body tebal dan tingkat keasaman rendah. Kopi Kintamani dari Bali jadi kopi Indonesia yang juga cukup populer di dunia. Kopi Kintamani merupakan jenis kopi arabika, karena ditanam di ketinggian lebih dari 1.000 mdpl. Letak kebunnya kebanyakan di dekat Gunung Batur. Kopi Jawa Barat menurut Dadang sedang tren akhir-akhir ini. Menurutnya, kopi Jawa Barat merupakan gabungan dari berbagai jenis biji kopi yang ditanam di sana. Kopi papua, Rasa kopi papua berbeda-beda. Menurut William, rasa biji kopi dari satu origin yang sama bisa saja jadi berbeda. Kopi Sumatra, Gerai kopi Starbucks punya ragam pilihan biji kopi, mulai dari asal Colombia, Ethiopia, Kenya hingga Sumatra. Dari banyak biji kopi, Sumatra adalah paling digemari.",
                "Jakarta - Baru dibuka akhir bulan Mei lalu, kafe Kopi Se-Indonesia milik Nicholas sudah mencuri perhatian. Mengenakan nama 'Se-Indonesia' pada kafenya, kedai kopi modern yang satu ini berdiri di antara resto dan cafe di sepanjang jalan Ruko Crown Golf.\n" +
                        "\n" + "Untuk menu yang ditawarkan fokusnya ke kopi, susu, dan teh. Ekspektasi kami, menu kopi di sini akan menggunakan single origin atau kopi spesifik yang berasal dari wilayah penghasil kopi di Indonesia. Mengingat kafe ini memakai nama Se-Indonesia.Sayangnya kami tidak bisa menemukan kopi single origin di dalam menu.",
                "Jakarta - Dua biji kopi yang terbilang paling dikenal di dunia adalah robusta dan arabika. Keduanya memiliki karakteristik berbeda mulai dari bentuk fisik, proses pengolahan, hingga rasa.\n" +
                        "\n" + "Selain robusta dan arabika, sebenarnya ada banyak jenis kopi berbeda di dunia. Kebanyakan muncul atau tercipta karena kawin silang yang terjadi di alam atau dikembangkan oleh peneliti. Tiap jenis punya karakter dan rasa yang berbeda juga. Biji kopi robusta Biji kopi jenis ini jadi yang paling banyak terdapat di dunia. Menurut William Heuw, owner dari brand Kopi Kangen, kopi robusta cenderung lebih tahan terhadap hama dan penyakit. Biji kopi arabika dianggap jauh lebih baik dan berkualitas dari biji kopi robusta. Biji kopi arabika cenderung tak tahan hama sehingga harus mendapatkan perawatan ekstra. Selasa (23/6/2020).",
                "Jakarta - Kebiasaan minum kopi ini dikhawatirkan sebagian orang, apakah minum kopi bisa membahayakan kesehatan ginjal?.\n" +
                        "\n" +"Penyebab utama penyakit ginjal adalah diabetes dan penyakit tekanan darah tinggi. Bagi beberapa orang yang tidak biasa minum kopi dan kalangan lansia, konsumsi asupan berkafein seperti kopi dapat meningkatkan tekanan darah. Peningkatan tekanan darah tersebut umumnya berlangsung sementara dan terjadi pada orang yang punya riwayat tekanan darah tinggi. Dengan kaitan antara minum kopi dan tekanan darah tinggi, tak pelak jamak muncul kekhawatiran terkait penyakit ginjal.",
                "Jakarta - ndonesia adalah negara penghasil kopi terbesar keempat di dunia pada tahun 2019. Posisi ini berada di bawah Brazil, Vietnam, dan Kolombia, menurut data Kementerian Pertanian RI. Masa depan kopi Indonesia di dunia begitu cerah. Hal tersebut diungkapkan oleh Dadang Hendarsyah, selaku Unit Head da ICS Manager PT. Olam Indonesia Sunda Cluster.\n" +
                        "\n" +"Menurut Dadang, petani Indonesia kebanyakan masih menanam biji kopi jenis robusta. Biji kopi ini dianggap memiliki kualitas di bawah arabika, sehingga harganya pun lebih murah. Dadang menuturkan, banyak petani yang menanam biji robusta karena perawatannya yang mudah dan tahan terhadap hama. \"Di Indonesia arabika masih jarang. Arabika jadinya lebih mahal karena biji kopinya diperlakukan berbeda. Dari penanaman, perawatan, pemetikan, sampai proses produksi berbeda,\" tutur Dadang.",
                "Jakarta - Banyak penelitian yang menyatakan kopi dapat mengakibatkan berbagai masalah kesehatan, mulai dari menyebabkan susah tidur hingga serangan jantung. Padahal, segudang manfaat kopi hitam tanpa gula berikut ini adalah alasan kuat bagi kita untuk menjadikan minum kopi sebagai bagian dari pola hidup sehat..\n" +
                        "\n" +"Kopi pada dasarnya merupakan produk alam yang memiliki banyak manfaat bagi kesehatan. Hanya saja dengan menambahkan gula ke dalamnya, minuman ini justru dapat menyebabkan pergeseran kandungan nutrisi. Apalagi jika kadar pemanis buatan itu melebihi ambang batas yang direkomendasikan. Menurut Asosiasi Jantung Amerika (AHA), jumlah asupan gula tambahan yang aman adalah 9 sendok teh (36 gram atau setara 150 kalori) per hari bagi pria dan 6 sendok teh (25 gram atau setara 100 kalori) per hari bagi wanita. Konsumsi gula berlebih akan meningkatkan risiko kita terkena penyakit, seperti diabetes tipe 2."
        };

        String[] image = {
                "https://asset.kompas.com/crops/HM3hYMLpHjphQceqVYw3GyNt8eY=/0x75:900x675/750x500/data/photo/2019/04/16/3723511057.jpg",
                "https://akcdn.detik.net.id/community/media/visual/2020/07/10/kopi-se-indonesia-7.jpeg?w=700&q=90",
                "https://asset.kompas.com/crops/c4N2TXZ1Du2xsY82uxM1cSp138s=/0x0:1500x1000/750x500/data/photo/2019/10/29/5db808f2eefd3.jpg",
                "https://asset.kompas.com/crops/kPXMRq_TnzlaOb1odggNXl5O3Cc=/61x0:734x449/750x500/data/photo/2020/01/22/5e2877bc5ee33.jpg",
                "https://asset.kompas.com/crops/jG-BkeX_eoOoy5K-FzDhCqTjv34=/0x0:1500x1000/750x500/data/photo/2019/10/29/5db808e983ab1.jpg",
                "https://asset.kompas.com/crops/RDag4VGBKvJp3NiJYfKVDijdxYU=/0x15:1000x681/750x500/data/photo/2017/11/13/1093198494.jpg"
        };

        ArrayList<Berita> data = new ArrayList<>();
        for(int i=0; i < judul.length; i++){
            Berita model = new Berita();
            model.setImage(image[i]);
            model.setTitle(judul[i]);
            model.setDeskripsi(deskripsi[i]);
            data.add(model);
        }
        adapterBerita.addAll(data);
        adapterBerita.setNotifyOnChange();
    }
}
