package com.omni.moviewdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

//@BindView(R.id.toolbar_details)
    Toolbar toolbar ;
//    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

//        intent = getIntent();
//        if(intent.hasExtra("movie")){
//            Movie movie = intent.getParcelableExtra("movie") ;
//            toolbar.setTitle(movie.getOriginalTitle());
//            String tit = movie.getTitle();
//            Toast.makeText(this, movie.getOriginalTitle(), Toast.LENGTH_SHORT).show();
//        }



    }

}
