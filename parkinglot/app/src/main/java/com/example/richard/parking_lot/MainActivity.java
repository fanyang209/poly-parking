package com.example.richard.parking_lot;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

public class MainActivity extends Activity
        implements AdapterView.OnItemClickListener {

    int position;
    private ListView listView;
    private Firebase firebase;
    private ProgressBar progressBar;
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
//    private CheckBox checkBox1;
    private CheckBox checkBox2;
    public static ArrayList position_list;
    public static HashSet<Integer> position_set = new HashSet<Integer>();
    private SharedPreferences sp1;
    private SharedPreferences sp2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("oncreate    ");

//        配置firebase
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://fanparking.firebaseio.com/");

//        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
//        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        listView = (ListView) findViewById(R.id.content_frame);
        progressBar = (ProgressBar) findViewById(R.id.my_progress);
        listView.setOnItemClickListener(this);
        position_set = new HashSet<Integer>();
        System.out.println("oncreate ++");

//        从firebase获取信息
        firebase.child("parking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println("12345676788899    ");
                ParkingAdapter parkingAdapter =
                        new ParkingAdapter(MainActivity.this, firebase, dataSnapshot,listView);
                listView.setAdapter(parkingAdapter);
                System.out.println("listView.getChildCount()   " + listView.getCount());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        System.out.println("oncreat fan ");
        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

// enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        if (savedInstanceState == null) {
//            selectItem(0);
//        }

        sp1 = getSharedPreferences("msg1", Context.MODE_PRIVATE);
        sp2 = getSharedPreferences("msg2", Context.MODE_PRIVATE);
    }

    protected void onResume() {
        super.onResume();

//        Iterator it = position_set.iterator();
//
//        System.out.println("it.hasNext()   " + it.hasNext());

//        choose the checked box and keep it checked when back to mainactivity
        for (int set: position_set){

            System.out.println("set     " + set);
            CheckBox  checkBox1 = (CheckBox)getViewByPosition(set, listView).findViewById(R.id.checkbox1);
            checkBox1.setSelected(sp1.getBoolean("msg2", true));
        }


//        为什么用 while  hasnext不行
//        while(it.hasNext()) {
//
//            System.out.println("it.next()    " + it.next());

//            if ((Integer) it.next() == 0) {
//                checkBox2 = (CheckBox) getViewByPosition((Integer) it.next(), listView).findViewById(R.id.checkbox2);
//                checkBox2.setSelected(true);
//            }

//            find the checkbox that is checked
//            CheckBox  checkBox1 = (CheckBox)getViewByPosition((Integer)it.next(), listView).findViewById(R.id.checkbox1);

            //        checkBox2.setSelected(sp2.getBoolean("msg2", false));
//            checkBox1.setSelected(sp1.getBoolean("msg2", true));

//            SharedPreferences.Editor editor1 = sp1.edit();
//        SharedPreferences.Editor editor2 = sp2.edit();
//            editor1.remove("msg1");
//            editor1.commit();
//        editor2.remove("msg2");
//        editor2.commit();
//        }

    }


    protected void onPause() {
        super.onPause();



//        boolean msg2 =  checkBox2.isChecked();

            SharedPreferences.Editor editor1 = sp1.edit();
//        SharedPreferences.Editor editor2 = sp2.edit();
            editor1.putBoolean("msg1", true);
//        editor2.putBoolean("msg2",msg2);
            editor1.commit();//提交
//        editor2.commit();//提交






    }

    //我们在该事件方法里存储数据
//    @Override
//    protected void onPause() {
//        super.onPause();
//        String msg = editText_msg.getText().toString();
//        if(TextUtils.isEmpty(msg)){
//            return;
//        }
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("msg",msg);
//        editor.commit();//提交
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_websearch:
                // create intent to perform web search for this planet
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
                // catch event that there's no activity to handle intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //    打开map  activity
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        System.out.println("basic map" + position);
        this.position = position;
        Intent intent = new Intent(this,BasicMapActivity.class);
        intent.putExtra("position",position);

        startActivity(intent);
    }


//    checkbox监听事件



    //     The click listner for ListView in the navigation drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }


//   侧栏点击事件
    private void selectItem(int position) {

        switch (position){

            case 0:
                Intent intent = new Intent(this,WholeMapActivity.class);
                startActivity(intent);
                break;

            case 1:
                Intent intent1 = new Intent(this,MarkedActivity.class);
//                intent1.putIntegerArrayListExtra("position_list", position_list);
                startActivity(intent1);
                break;

        }

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        // 关闭侧边栏
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }



//     When using the ActionBarDrawerToggle, you must call it during
//     onPostCreate() and onConfigurationChanged()...

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public View getViewByPosition(int pos, ListView listView) {
         int firstListItemPosition = listView.getFirstVisiblePosition();
         int lastListItemPosition = firstListItemPosition + listView.getCount() - 1;

        System.out.println( "firstListItemPosition    "+ firstListItemPosition );
        System.out.println("lastListItemPosition      " + lastListItemPosition);
        System.out.println("pos     "  +     pos);

//        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
//            return listView.getAdapter().getView(pos, null, listView);
//        } else {
             int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(pos);
        }
//    }
}

