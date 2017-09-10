package quadx.decider10a;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Entry> masterList = new ArrayList<>();
    int currentEntry = 0;
    Button ordered =null;
    EditText listField =null;
    EditText nameField= null;
    Spinner listsSpinner=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compileList();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /////////////////////////////////////////////////////////

        Button random = ((Button) findViewById(R.id.button));
        ordered = ((Button) findViewById(R.id.button2));
        listField = (EditText) findViewById(R.id.editText);
        nameField= (EditText) findViewById(R.id.nameField);
        listsSpinner= (Spinner) findViewById(R.id.spinner1);
        initLists();
        refreshSpinner();
        listsSpinner.setOnItemSelectedListener(listener);



        final Random rn = new Random();
/*        final ArrayList<String> names = new ArrayList<>();
        Scanner sc = null;
        try {
            File file = new File(getFilesDir() + "/names.txt");


            sc = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (sc != null && sc.hasNextLine()) {
            names.add(sc.nextLine());
        }*/


        listField.setGravity(Gravity.TOP);
        /*
        for (int i = 0; i < names.size(); i++) {
            listField.append(names.get(i));
            if (i != names.size() - 1) {
                listField.append("\n");
            }
        }
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.clear();
                String multiLines = listField.getText().toString();
                String[] ns;
                String delimiter = "\n";
                ns = multiLines.split(delimiter);
                Toast.makeText(MainActivity.this, "RANDOM:: " + ns[rn.nextInt(ns.length - 1)], Toast.LENGTH_LONG).show();
                names.addAll(Arrays.asList(ns));
                writeToFile(names, getApplicationContext());
            }
        });

        ordered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.clear();
                String multiLines = listField.getText().toString();
                String[] ns;
                String delimiter = "\n";
                ns = multiLines.split(delimiter);
                int n = Integer.parseInt(ns[ns.length - 1]);
                n++;
                if (n >= ns.length - 1)
                    n = 0;
                Toast.makeText(MainActivity.this, "DECIDED:: " + ns[n], Toast.LENGTH_LONG).show();
                ns[ns.length - 1] = "" + n;
                listField.setText("");

                names.addAll(Arrays.asList(ns));
                writeToFile(names, getApplicationContext());
                for (int i = 0; i < names.size(); i++) {
                    listField.append(names.get(i));
                    if (i != names.size() - 1) {
                        listField.append("\n");
                    }
                }
            }
        });*/
    }

    AdapterView.OnItemSelectedListener listener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            currentEntry=listsSpinner.getSelectedItemPosition();
            exchangeList(currentEntry);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }

    };
    void refreshSpinner(){
        ArrayList<String> n=new ArrayList<>();
        for(int i=0;i<masterList.size();i++){
            n.add(masterList.get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, n);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listsSpinner.setAdapter(dataAdapter);
        listsSpinner.setSelected(false);
        listsSpinner.setSelection(0);

    }

    void compileList(){
        String n= nameField.getText().toString();
        String[] arr= listField.getText().toString().split("\n");
        ArrayList<String> strings = new ArrayList<>();
        strings.addAll(Arrays.asList(arr));
        Entry er=new Entry(strings,0,n);
        masterList.add(er);
        refreshSpinner();
        clearScreen();
    }
    void clearScreen(){
        exchangeList(0);
    }
    void exchangeList(int x) {
        listField.setText("");
        Entry entry=masterList.get(x);
        ArrayList<String> e = entry.getEntries();
        for (int i = 0; i < e.size(); i++) {
            listField.append(e.get(i));
            if (i != e.size() - 1) {
                listField.append("\n");
            }
        }
        nameField.setText(entry.getName());
    }

    void initLists() {
        ArrayList<String> q = new ArrayList<>();
        q.add("");
        masterList.add(new Entry(q, 0, "New List"));

        ArrayList<String> s = new ArrayList<>();
        s.add("a");
        s.add("b");
        s.add("c");
        masterList.add(new Entry(s, 0, "list1"));

        ArrayList<String> a = new ArrayList<>();
        a.add("x");
        a.add("y");
        a.add("z");
        a.add("q");
        masterList.add(new Entry(a, 0, "list2"));

        ArrayList<String> c = new ArrayList<>();
        c.add("chris");
        c.add("joel");
        c.add("dago");
        c.add("will");
        c.add("eric");
        c.add("rob");
        masterList.add(new Entry(c, 0, "list3"));

        ArrayList<String> d = new ArrayList<>();
        d.add("true");
        d.add("false");
        masterList.add(new Entry(d, 0, "list4"));

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

    private void writeToFile(ArrayList<String> data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("names.txt", Context.MODE_PRIVATE));
            for (int i = 0; i < data.size(); i++) {
                String s = data.get(i);
                if (i != data.size() - 1) {
                    s += "\n";
                }
                outputStreamWriter.write(s);
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
