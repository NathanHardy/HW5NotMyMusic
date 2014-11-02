package com.nathan.hardy.notmymusic;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class MainActivity extends Activity {

    SearchFragment searchFragment = new SearchFragment();

    PlaceholderFragment placeholder = new PlaceholderFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, placeholder)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            SharedMethod();
            return true;
        }

        if (id == R.id.action_add) {
            SharedMethod2();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    private void SharedMethod() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack(null);

        getFragmentManager().beginTransaction()
                .remove(placeholder)
                .commit();

        transaction.add(R.id.container, searchFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        transaction.remove(searchFragment);

        getFragmentManager().beginTransaction()
                .remove(searchFragment)
                .commit();
    }

    public void SwapView(View view) {
        SharedMethod();
    }

    public void StartNewActivity(View view) {
        SharedMethod2();
    }

    private void SharedMethod2() {
        startActivity(new Intent(MainActivity.this, AddActivity.class));
    }

    public void StartDetailActivity(View view) {
        startActivity(new Intent(MainActivity.this, DetailActivity.class));
    }
}
