package com.example.tio.biodataa;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 1;
    String nim, nama, no_telp, email, alamat,imgDecodableString;
    Button button;
    Bundle fieldresults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nim = (EditText)findViewById(R.id.input_nim);
        final EditText nama = (EditText)findViewById(R.id.input_nama);
        final EditText no_telp = (EditText)findViewById(R.id.input_notelp);
        final EditText email = (EditText)findViewById(R.id.input_email);
        final EditText alamat = (EditText)findViewById(R.id.input_alamat);
        Button upload = (Button)findViewById(R.id.img_button);
        Button simpan = (Button)findViewById(R.id.simpan_button);

        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fieldresults = new Bundle();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("nim",nim.getText().toString());
                intent.putExtra("nama",nama.getText().toString());
                intent.putExtra("no_telp",no_telp.getText().toString());
                intent.putExtra("email",email.getText().toString());
                intent.putExtra("alamat",alamat.getText().toString());
                intent.putExtra("foto",imgDecodableString);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // CEK WAKTU IMAGE PICK
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // GET THE CURSOR
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // MOVE TO FIRST ROW
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                Bitmap foto_path = BitmapFactory.decodeFile(imgDecodableString);
                ImageView image = (ImageView) findViewById(R.id.imageView);
                image.setImageBitmap(foto_path);
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

}
