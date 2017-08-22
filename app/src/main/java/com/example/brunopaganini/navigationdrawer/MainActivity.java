package com.example.brunopaganini.navigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navView);

        setFragentByDefault();

        navigationView.getMenu().findItem(R.id.nav_item1)
                .setActionView(new Switch(this));

        // To set whether switch is on/off use:
        final Switch aSwitchBtn = (Switch) navigationView.getMenu().findItem(R.id.nav_item1).getActionView();
        aSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.menu_mail:
                        fragment = new EmailsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_alert:
                        fragment = new AlertsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        fragmentTransaction = true;
                        break;
                }

                if (fragmentTransaction){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .commit();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    drawerLayout.closeDrawers();
                }

                return false;
            }
        });
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setFragentByDefault(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new EmailsFragment())
                .commit();
        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }
}
