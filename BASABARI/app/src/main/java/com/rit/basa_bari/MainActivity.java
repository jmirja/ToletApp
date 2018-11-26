package com.rit.basa_bari;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.rit.basa_bari.activities.AccountActivity;
import com.rit.basa_bari.activities.LoginActivity;
import com.rit.basa_bari.fragments.AddPostFragment;
import com.rit.basa_bari.show_post_activity.Show_Mess_Activity;

import static com.rit.basa_bari.activities.LoginActivity.LOGCHECKER;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        initialization();

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    //main activity page click button initialization
    public  void initialization(){
        imageView=findViewById( R.id.messImage );
        imageView.setOnClickListener( this );
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

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile ){
            Intent intent=new Intent(this, AccountActivity.class);
            startActivity(intent);

        } else if (id == R.id.yourPost) {
            Intent intent=new Intent(this, AccountActivity.class);
            startActivity(intent);

        } else if (id == R.id.addPost) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framlayout, new AddPostFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Add Post");

        } else if (id == R.id.nav_share) {
            Intent intent=new Intent(this, AccountActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            Intent intent=getIntent();
            String uEmail=intent.getStringExtra("Intent1_EMAIL");
            String uPassword=intent.getStringExtra("Intent1_PASSWORD");
            if(LOGCHECKER==false){
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.messImage){
            startActivity( new Intent( MainActivity.this, Show_Mess_Activity.class ) );
        }

    }
}
