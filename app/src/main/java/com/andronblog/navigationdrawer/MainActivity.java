package com.andronblog.navigationdrawer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;

import com.andronblog.navigationdrawer.fragment.FragmentOne;
import com.andronblog.navigationdrawer.fragment.FragmentThree;

public class MainActivity extends NavFragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbarAndDrawer(R.id.toolbar, R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayFragment(FragmentOne.class);
        navigationView.setCheckedItem(R.id.nav_fragment_1);
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
    public boolean onNavigationItemSelected(MenuItem item) {

        boolean handled = super.onNavigationItemSelected(item);
        if (!handled) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_fragment_1) {
                replaceFragment(FragmentOne.class);
            } else if (id == R.id.nav_fragment_3) {
                replaceFragment(FragmentThree.class);
            }
        }

        closeDrawer();
        return true;
    }

}
