package app.ewtc.masterung.hambergericon;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import app.ewtc.masterung.hambergericon.fragment.MainFragment;
import app.ewtc.masterung.hambergericon.fragment.SecondFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private String tag = "24AugV1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View
        initialView();

        //Add Fragment to Activity
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContent, new MainFragment())
                    .commit();
        }

        //Main Controller
        mainController();

        //Second Controller
        secondController();

    }

    private void secondController() {
        TextView textView = (TextView) findViewById(R.id.txtSecond);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tag, "Click Second");

                if (getSupportFragmentManager().findFragmentById(R.id.fragmentContent) != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(getSupportFragmentManager()
                                    .findFragmentById(R.id.fragmentContent));
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContent, new SecondFragment())
                        .commit();
                drawerLayout.closeDrawers();
            }
        });
    }

    private void mainController() {
        TextView textView = (TextView) findViewById(R.id.txtMain);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tag, "Click Main");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContent, new MainFragment())
                        .commit();
                drawerLayout.closeDrawers();
            }
        });
    }

    private void initialView() {

        toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }   // initialView

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
