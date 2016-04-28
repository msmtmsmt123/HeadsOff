package com.rong.xposed.headsoff;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;

import com.rong.xposed.headsoff.adapter.PackageListAdapter;
import com.rong.xposed.headsoff.adapter.PackageListItemDivider;
import com.rong.xposed.headsoff.utils.AppUtils;
import com.rong.xposed.headsoff.utils.SettingValues;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
		SwipeRefreshLayout.OnRefreshListener {

	private List<Map<String, Object>> mPackageList = null;
	private SwipeRefreshLayout mSwipeRefreshLayout = null;
	private PackageListAdapter mAdapter = null;

	private boolean bShowSystemApps = false;

	@Override
	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		prefsReload();

		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeResources(
				R.color.swipe_refresh_progress_color_1,
				R.color.swipe_refresh_progress_color_2,
				R.color.swipe_refresh_progress_color_3);

		RecyclerView list = (RecyclerView) findViewById(R.id.app_list);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		list.setLayoutManager(layoutManager);
		list.setItemAnimator(new DefaultItemAnimator());
		list.addItemDecoration(new PackageListItemDivider(this));

		mAdapter = new PackageListAdapter(this, null);
		list.setAdapter(mAdapter);

		final View root = getWindow().getDecorView().findViewById(android.R.id.content);
		root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				root.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				mSwipeRefreshLayout.setRefreshing(true);
				onRefresh();
			}
		});
	}

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	private void prefsReload() {
		SharedPreferences prefs = getSharedPreferences(SettingValues.PREF_FILE, Context.MODE_WORLD_READABLE);
		bShowSystemApps = prefs.getBoolean(SettingValues.KEY_SHOW_SYS_APP, SettingValues.DEFAULT_SHOW_SYS_APP);
	}

	@Override
	public void onRefresh() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				prefsReload();
				mPackageList = AppUtils.getAppList(MainActivity.this, bShowSystemApps);
				mAdapter.setItemList(mPackageList);

				runOnUiThread(new Runnable() {
					public void run() {
						mAdapter.notifyDataSetChanged();
						mSwipeRefreshLayout.setRefreshing(false);
					}
				});
			}
		}).start();
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

		if (id == R.id.action_main_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
