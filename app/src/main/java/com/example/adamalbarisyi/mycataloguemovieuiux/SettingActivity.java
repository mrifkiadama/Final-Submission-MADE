package com.example.adamalbarisyi.mycataloguemovieuiux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adamalbarisyi.mycataloguemovieuiux.preference.PreferenceMovie;

public class SettingActivity extends AppCompatActivity {

    public SettingActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,new PreferenceMovie()).commit();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return  true;
    }
}
