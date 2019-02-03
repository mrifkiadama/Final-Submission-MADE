package com.example.adamalbarisyi.mycataloguemovieuiux;

import android.content.Intent;
import android.preference.Preference;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.adamalbarisyi.mycataloguemovieuiux.adapter.NowAndUpFavMovieAdapter;
import com.example.adamalbarisyi.mycataloguemovieuiux.adapter.NowUpFavMovieItems;
import com.example.adamalbarisyi.mycataloguemovieuiux.favoritePage.FavoriteFragment;
import com.example.adamalbarisyi.mycataloguemovieuiux.nowplayingPage.NowPlayingFragment;
import com.example.adamalbarisyi.mycataloguemovieuiux.searchPage.SearchFragment;
import com.example.adamalbarisyi.mycataloguemovieuiux.upcomingPage.UpComingFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        Preference.OnPreferenceClickListener,Preference.OnPreferenceChangeListener {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new NowPlayingFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings){
           Intent intent = new Intent(MainActivity.this,SettingActivity.class);
           startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Fragment fragment = null;
        String title = "";
        switch (menuItem.getItemId()) {
            case R.id.now_playing_menu:
                title = "Now Playing";
                fragment = new NowPlayingFragment();
                break;
            case R.id.up_coming_menu:
                title = "Up Coming";
                fragment = new UpComingFragment();
                break;
            case R.id.favorite_menu:
                title = "Favorite";
                fragment = new FavoriteFragment();
                break;
            case R.id.search_menu:
                title = "Search";
                fragment = new SearchFragment();
                break;
        }
        getSupportActionBar().setTitle(title);
        return loadFragment(fragment);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }
}
