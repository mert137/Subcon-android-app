package com.example.fragment.subcontechs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager manager = getSupportFragmentManager();
        AnaEkran homeFragment = new AnaEkran();
        manager.beginTransaction()
                .replace(R.id.mainLayout, homeFragment)  // content_main.xml deki frame layout id'si
                .commit();

     //   navigationView.getHeaderView(0).setVisibility(NavigationView.GONE);   // Bu satırla headerı ortadan kaldırdık.
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();

        switch (id) {
            case R.id.nav_ana_layout:
                AnaEkran firstFragment = new AnaEkran();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, firstFragment)  // content_main.xml deki frame layout id'si
                        .commit();
                break;
            case R.id.nav_discover_layout:
                KesfetFragment kesfetFragment = new KesfetFragment();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, kesfetFragment)   // content_main.xml deki frame layout id'si
                        .commit();
                break;
            case R.id.nav_watch_layout:
                HorizontalListViewFragment secondFragment = new HorizontalListViewFragment();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, secondFragment)   // content_main.xml deki frame layout id'si
                        .commit();
                break;
            case R.id.nav_listen_layout:
                MusicCategories thirdFragment = new MusicCategories();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, thirdFragment)    // content_main.xml deki frame layout id'si
                        .commit();
                break;
            case R.id.nav_play_layout:
                GameCat fourthFragment = new GameCat();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, fourthFragment)    // content_main.xml deki frame layout id'si
                        .commit();
                break;
            case R.id.nav_futbol_layout:
                FutbolCat fifthFragment = new FutbolCat();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, fifthFragment)    // content_main.xml deki frame layout id'si
                        .commit();
                break;
            case R.id.nav_read_layout:
                OkuCat sixthFragment = new OkuCat();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, sixthFragment)    // content_main.xml deki frame layout id'si
                        .commit();
                break;
            case R.id.nav_learn_layout:
                OgrenCat sevenFragment = new OgrenCat();
                manager.beginTransaction()
                        .replace(R.id.mainLayout, sevenFragment)    // content_main.xml deki frame layout id'si
                        .commit();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
