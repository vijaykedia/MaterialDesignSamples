package com.kediavijay.swipetorefreshlistview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by vijaykedia on 28/03/16.
 * Main Launcher Activity
 */
public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, new ArrayList<>(Arrays.asList(getCities())));
        adapter.setNotifyOnChange(true);

        // Create a ListView
        mListView = (ListView) findViewById(R.id.list_view);
        assert mListView != null;

        mListView.setAdapter(adapter);

        // Create Swipe to Refresh Layout
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        assert mSwipeRefreshLayout != null;

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                adapter.addAll(Arrays.asList(getCities()));
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private String[] getCities() {
        final String[] cities = getResources().getStringArray(R.array.cities);

        final Random random = new Random();
        for (int i = 0; i < cities.length; i++) {
            final int index = random.nextInt(cities.length);
            final String temp = cities[index];
            cities[index] = cities[i];
            cities[i] = temp;
        }
        return cities;
    }
}
