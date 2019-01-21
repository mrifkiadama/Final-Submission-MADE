package com.example.adamalbarisyi.mycataloguemovieuiux;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.HomePage.HomeFragment;
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.NowPlayingPage.NowPlayingFragment;
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.SearchPage.SearchFragment;
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.UpComingPage.UpComingFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());
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
        if (item.getItemId() == R.id.action_chang_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Fragment fragment = null;
        String title = "";
        switch (menuItem.getItemId()) {
            case R.id.home_menu:
                title = "Home";
                fragment = new HomeFragment();

                break;
            case R.id.now_playing_menu:
                title = "Now Playing";
                fragment = new NowPlayingFragment();
                break;
            case R.id.up_coming_menu:
                title = "Up Coming";
                fragment = new UpComingFragment();
                break;
            case R.id.search_menu:
                title = "Search";
                fragment = new SearchFragment();
                break;
        }
        getSupportActionBar().setTitle(title);
        return loadFragment(fragment);
    }
}
