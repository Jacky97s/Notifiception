package app.fcu.notifiception;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //把 toolbar 的 layout 固定
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //叫出 Navigation，抽屜的概念
        drawerLayout = findViewById(R.id.drawer_Layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //預設開啟 Notifiception fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new Notifiception());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Notifiception");

        //當 Navigation 的 item 被按下，開啟該 item fragment
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.pg_noti:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new Notifiception());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Notifiception");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.pg_br:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new BatteryReminder());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Battery Reminder");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.pg_tos:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new TurnOffSilent());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Turn Off Silent");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.pg_police:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new PoliceData());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Emergency Contact");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.pg_mail:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new Mail());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Feedback");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.pg_about:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new About());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("About Us");
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
