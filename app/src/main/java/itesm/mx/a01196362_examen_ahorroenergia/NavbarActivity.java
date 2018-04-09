package itesm.mx.a01196362_examen_ahorroenergia;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NavbarActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener, FragElectrodomestico.OnElectrodomesticoSelectedListener {

    int indexElectrodomestico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);
        Toolbar toolbar = findViewById(R.id.toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState != null){
            getSupportFragmentManager().executePendingTransactions();

            Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_container);
            if (fragment != null){
                getFragmentManager().beginTransaction().remove(fragment).commit();
            }

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                return;
            }
        }

        if (findViewById(R.id.fragment_container) != null){
            FragElectrodomestico electrodomesticoFrag = FragElectrodomestico.newInstance();

            FragmentTransaction transact = getSupportFragmentManager().beginTransaction();
            transact.replace(R.id.fragment_container, electrodomesticoFrag, "TAG_Electrodomestico");
            transact.commit();
        }
    }

    @Override
    public void onElectrodomesticoSelected(int ElectrodomesticoIndex) {
        indexElectrodomestico = ElectrodomesticoIndex;
        FragDetalle detalleFrag = FragDetalle.newInstance(ElectrodomesticoIndex);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detalleFrag, "Detalle");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
        getMenuInflater().inflate(R.menu.navbar, menu);
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
        DrawerLayout drawer;

        switch (item.getItemId()) {
            case R.id.navigation_electrodomesticos:
                FragElectrodomestico electrodomesticoFrag = FragElectrodomestico.newInstance();
                drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                FragmentTransaction transact = getSupportFragmentManager().beginTransaction();
                transact.replace(R.id.fragment_container, electrodomesticoFrag, "TAG_ELECTRODOMESTICOS");
                transact.commit();
                return true;

            case R.id.navigation_eventos:
                FragEvento eventoFragment = new FragEvento();
                drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                FragmentTransaction transact2 = getSupportFragmentManager().beginTransaction();
                transact2.replace(R.id.fragment_container, eventoFragment, "TAG_EVENTOS");
                transact2.commit();

                return true;
            case R.id.navigation_reporte:
                FragReporte agregar = new FragReporte();
                drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                FragmentTransaction transact3 = getSupportFragmentManager().beginTransaction();
                transact3.replace(R.id.fragment_container, agregar, "TAG_REPORTE");
                transact3.commit();
                return true;
        }
        return false;

    }
}
