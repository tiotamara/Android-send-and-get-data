package com.example.tio.biodataa;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    String nim, nama, no_telp, email, alamat,informasi,foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // SETTING LAYOUT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);

        //MENGAMBIL DATA DARI INTENT
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nim = bundle.getString("nim");
            nama = bundle.getString("nama");
            no_telp = bundle.getString("no_telp");
            email = bundle.getString("email");
            alamat = bundle.getString("alamat");
            foto = bundle.getString("foto");
        }else{
            String err = "tidak ada data";
            nim = err;
            nama = err;
            no_telp = err;
            email = err;
            alamat = err;
            foto = err;
        }


        Toast.makeText(this, foto, Toast.LENGTH_LONG)
                .show();
        //MEMBUAT INISIALISASI
        TextView informasi = (TextView)findViewById(R.id.informasi);
        TextView nim_pl = (TextView)findViewById(R.id.txt_nim);
        TextView nama_pl = (TextView)findViewById(R.id.txt_nama);
        TextView no_telp_pl = (TextView)findViewById(R.id.txt_no_telp);
        TextView email_pl = (TextView)findViewById(R.id.txt_email);
        TextView alamat_pl = (TextView)findViewById(R.id.txt_alamat);
        ImageView foto_pl = (ImageView)findViewById(R.id.foto);

        // MEMBUAT TEXT UNDERLINE
        String info = new String("INFORMASI");
        SpannableString content = new SpannableString(info);
        content.setSpan(new UnderlineSpan(), 0, info.length(), 0);
        informasi.setText(content);

        //MENGAMBIL FOTO
        Bitmap foto_path = BitmapFactory.decodeFile(foto);

        //SETTEXT
        nim_pl.setText(nim);
        nama_pl.setText(nama);
        no_telp_pl.setText(no_telp);
        email_pl.setText(email);
        alamat_pl.setText(alamat);
        foto_pl.setImageBitmap(foto_path);
    }
}
