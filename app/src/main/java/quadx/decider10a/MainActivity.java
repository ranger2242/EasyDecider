package quadx.decider10a;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        final Random rn = new Random();
        final ArrayList<String> names= new ArrayList<>();
        Scanner sc = null;
        try {
            File file = new File(getFilesDir() + "/names.txt");


            sc = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (sc!=null && sc.hasNextLine()){
            names.add(sc.nextLine());
        }

        setContentView(R.layout.activity_main);
        Button random= ((Button)findViewById(R.id.button));
        Button ordered= ((Button)findViewById(R.id.button2));
        final EditText list =(EditText)findViewById(R.id.editText);
        list.setGravity(Gravity.TOP);
        for(int i=0;i<names.size();i++){
            list.append(names.get(i) );
            if(i!=names.size()-1){
                list.append("\n");
            }
        }
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.clear();
                String multiLines = list.getText().toString();
                String[] ns;
                String delimiter = "\n";
                ns = multiLines.split(delimiter);
                Toast.makeText(MainActivity.this,"RANDOM:: "+ns[rn.nextInt(ns.length-1)], Toast.LENGTH_LONG).show();
                names.addAll(Arrays.asList(ns));
                writeToFile(names,getApplicationContext());
            }
        });

        ordered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.clear();
                String multiLines = list.getText().toString();
                String[] ns;
                String delimiter = "\n";
                ns = multiLines.split(delimiter);
                int n=Integer.parseInt(ns[ns.length-1]);
                n++;
                if(n>=ns.length-1)
                    n=0;
                Toast.makeText(MainActivity.this,"DECIDED:: "+ns[n], Toast.LENGTH_LONG).show();
                ns[ns.length-1]=""+n;
                list.setText("");

                names.addAll(Arrays.asList(ns));
                writeToFile(names,getApplicationContext());
                for(int i=0;i<names.size();i++){
                    list.append(names.get(i) );
                    if(i!=names.size()-1){
                        list.append("\n");
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }/* else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void writeToFile(ArrayList<String> data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("names.txt", Context.MODE_PRIVATE));
            for(int i=0;i<data.size();i++) {
                String s=data.get(i);
                if (i != data.size() - 1) {
                    s+="\n";
                }
                outputStreamWriter.write(s);
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
