package cr.ac.ucr.app_proyecto_i_aplicada;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import android.widget.SearchView;
import android.widget.TextView;

public class CoreVisesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProductListFragment.OnFragmentInteractionListener,
ProductDetailFragment.OnFragmentInteractionListener,CategoryListFragment.OnFragmentInteractionListener,
        LogoutFragment.OnFragmentInteractionListener{

    private SearchView svProducts;
    private TextView prueba;
    private ImageView ivCoreVisesLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_vises);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarApp);
        setSupportActionBar(toolbar);
      //  getSupportActionBar().setTitle("CoreVises");

        svProducts = (SearchView) findViewById(R.id.searchViewProduct);
        ivCoreVisesLogo=(ImageView) findViewById(R.id.ivCoreVisesLogo);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Boolean transactionFragmentation=false;
        Fragment fragment=null;

        if (id == R.id.nav_products) {
            fragment= new ProductListFragment();
            Bundle intent = new Bundle();

            //Se añade el name vacion para relacionarlo que no se llamo desde el fragment de categorias
            intent.putString("name", "");
            //al fragment se le añaden los parametros del item que se toco
            fragment.setArguments(intent);

            transactionFragmentation=true;
            svProducts.setVisibility(View.VISIBLE);
            ivCoreVisesLogo.setVisibility(View.GONE);
        } else if (id == R.id.nav_categories) {
            fragment= new CategoryListFragment();
            transactionFragmentation=true;
        }else if (id == R.id.nav_login) {

        }

        if(transactionFragmentation){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_core_vises, fragment).commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
