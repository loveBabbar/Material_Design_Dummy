package com.example.loveb.demo_viewpager;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Chats.OnFragmentInteractionListener
                                                              ,Status.OnFragmentInteractionListener
                                                              ,Contacts.OnFragmentInteractionListener  {

    android.support.v7.widget.Toolbar toolbar;
    TabLayout tabLayout;
    MaterialSearchView searchView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setTitleTextColor(Color.parseColor("#ff5572"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //SearchView
        searchView=(MaterialSearchView)findViewById(R.id.searchView);//listeners lagadenge agar kuch data ho to

        //yha se leke
     drawerLayout=(DrawerLayout)findViewById(R.id.dl);
     drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Closed);
     drawerLayout.setDrawerListener(drawerToggle);
     drawerToggle.syncState();


     NavigationView navView=(NavigationView)findViewById(R.id.navview);

     navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             int id=item.getItemId();
             if(id==R.id.myprofile)
             {
                 Toast.makeText(MainActivity.this,"MyProfile Selected!!",Toast.LENGTH_SHORT).show();
             }
             else if(id==R.id.settings)
             {
                 Toast.makeText(MainActivity.this,"Setings Selected!!",Toast.LENGTH_SHORT).show();
             }
             else if(id==R.id.feedback)
             {
                 Toast.makeText(MainActivity.this,"FeedBack Selected!!",Toast.LENGTH_SHORT).show();
             }


             return true;
         }
     });//yaha tak h navigation drawer ka code baaki saara viewpager ka h

        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Chats"));
        tabLayout.addTab(tabLayout.newTab().setText("Status"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacts"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager=(ViewPager)findViewById(R.id.pager);
        pagerAdapter Adapter=new pagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(Adapter);


        //iss line ko daalne se ye hoga ki :  agar main niche se slide karke page change karunga to
        //upar jo tabs h and jo highlighting line h , wo bhi change hogi , matlab ki ek sync bana rahega
        //tabs and pages k beech me. Agar maanle k ye line absent h , to agar main page change karunga to
        //pages ka content to change hoga but upar ki highlighting line change nhi hogi
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        //iss line ko daalne se kya hoga ki: agar main tab p click karta hu, to sirf highlighting line change
        //hogi and niche jo pages h wo nhi change honge(agar ye nhi likhi hui to). or agar ye line hamne likhi
        //hui h to agar main tab p click karu to mere pages bhi sync rahenge and wo bhi saath me change honge
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());//tab ki position lele jispe click kia h
                //and fer jaha tha wah se jaha jaana h waha tak ek smooth transition mkarde
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item=menu.findItem(R.id.action_search);
      searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return  drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
