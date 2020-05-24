package com.o.dagger2practice.ui.main;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.o.dagger2practice.BaseActivity;
import com.o.dagger2practice.R;
import com.o.dagger2practice.ui.main.posts.PostsFragment;
import com.o.dagger2practice.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        init();
    }

    private void init() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sessionManager.logOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_profile);
                break;
            case R.id.nav_posts:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_posts);
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
