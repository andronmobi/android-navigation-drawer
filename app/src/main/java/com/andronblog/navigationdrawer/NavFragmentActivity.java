package com.andronblog.navigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.andronblog.navigationdrawer.fragment.NavFragment;


public class NavFragmentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavFragment.OnFragmentCreateViewListener {

    protected DrawerLayout mDrawer;
    protected ActionBarDrawerToggle mDrawerToggle;

    protected void setupToolbarAndDrawer(int resToolbarId, int resDrawerId) {

        Toolbar toolbar = (Toolbar) findViewById(resToolbarId);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(resDrawerId);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpened()) {
            closeDrawer();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();
            } else {
                if (!isFinishing()) {
                    finish();
                }
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Do nothing if we click on the current item
        if (item.isChecked()) {
            return true;
        }

        // Return to the root fragment if there are other fragments in the stack
        while (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        }
        return false;
    }

    public void replaceFragment(Class fragmentClass) {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        }
        displayFragment(fragmentClass);
    }

    public void displayFragment(Class fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String backStateName = fragmentClass.getName();
        Fragment fragment = fragmentManager.findFragmentByTag(backStateName);
        if (fragment == null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (InstantiationException e) {
                return;
            } catch (IllegalAccessException e) {
                return;
            }
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.flContent, fragment, backStateName);
        ft.addToBackStack(backStateName);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    protected boolean isDrawerOpened() {
        if (mDrawer != null) {
            return mDrawer.isDrawerOpen(GravityCompat.START);
        }
        return false;
    }

    protected void closeDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(GravityCompat.START);
        }
    }

    protected View.OnClickListener mNavigationBackPressListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().popBackStack();
        }
    };

    @Override
    public void onFragmentCreateViewDone(NavFragment fragment) {

        // Set a title for the toolbar
        String name = fragment.getName();
        setTitle(name != null ? name : getResources().getString(R.string.app_name));

        // Update the navigation bar toggle
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            // Remove the hamburger icon
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            // Show the back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Set on toggle click listener to pop the previous fragment
            mDrawerToggle.setToolbarNavigationClickListener(mNavigationBackPressListener);
        } else {
            // Remove the back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show the hamburger button
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            // Set on toggle click listener to open nav drawer
            mDrawerToggle.setToolbarNavigationClickListener(mDrawerToggle.getToolbarNavigationClickListener());
        }
    }
}
