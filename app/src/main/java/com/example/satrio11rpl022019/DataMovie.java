package com.example.satrio11rpl022019;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DataMovie extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    ModelMovieRealm movieModel;

    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;

    TextView Moviejudul;
    ImageView imgmovie;
    TextView Moviedesc;
    Button Movietanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_movie);
        extras = getIntent().getExtras();
        Moviejudul = findViewById(R.id.Moviejudul);
        Moviedesc = findViewById(R.id.moviedesc);
        imgmovie = findViewById(R.id.imgmovie);
        Movietanda = findViewById(R.id.Movietanda);



        if (extras != null) {
            title = extras.getString("judul");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            Moviejudul.setText(title);
            Moviedesc.setText(deskripsi);
            Glide.with(DataMovie.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imgmovie);

        }
        Realm.init(DataMovie.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        Movietanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieModel = new ModelMovieRealm();
                movieModel.setDesc(deskripsi);
                movieModel.setJudul(title);
                movieModel.setPath(path);
                movieModel.setReleaseDate(date);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);

            }
        });
    }


}

