package uclv.dte.campanalasvillas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.Stack;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView myBrowser;

    Stack<String> activitys;
    String actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

/*
        final ImageView imageView = (ImageView)findViewById(R.id.imagen);
        AssetManager assetManager = getAssets();
        InputStream istr = assetManager.open('logo_dte.png');
        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        imageView.setImageBitmap(bitmap);;
*/


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        myBrowser = (WebView)findViewById(R.id.mybrowser);

        //activamos javascript
        WebSettings settings = myBrowser.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        activitys = new Stack<String>();
        actual = "file:///android_asset/indextimeline.html";
        myBrowser.loadUrl("file:///android_asset/indextimeline.html");

        //myBrowser.addJavascriptInterface(new Intermediario(this), "Android");

        //myBrowser.setWebViewClient(new WebViewClient(){
        //    public void onPageFinished(WebView view, String url){
        //        myBrowser.loadUrl("javascript:init('" + "Sergio" + "')");
        //    }
        //});
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(activitys.empty()) {
                super.onBackPressed();
            }else{
                actual = activitys.pop();
                myBrowser.loadUrl(actual);
            }
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
            Dialog dialogo = crearDialogoAlerta();
            dialogo.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            if(actual.equalsIgnoreCase("file:///android_asset/indexmapa.html") ) {

            }else{
                activitys.push(actual);
            }
            actual = "file:///android_asset/indexmapa.html";
            myBrowser.loadUrl("file:///android_asset/indexmapa.html");
        } else if (id == R.id.nav_gallery) {
            if(actual.equalsIgnoreCase("file:///android_asset/indexgaleria.html")) {

            }else{
                activitys.push(actual);
            }
            actual = "file:///android_asset/indexgaleria.html";
            myBrowser.loadUrl("file:///android_asset/indexgaleria.html");
        } else if (id == R.id.nav_timeline) {
            if(actual.equalsIgnoreCase("file:///android_asset/indextimeline.html")) {

            }else{
                activitys.push(actual);
            }
            actual = "file:///android_asset/indextimeline.html";
            myBrowser.loadUrl("file:///android_asset/indextimeline.html");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private Dialog crearDialogoAlerta() {
        //Creacion del Dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //inflando el contenido con un layout;
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_sobre,(ViewGroup) findViewById(R.id.lytLayout));
        builder.setView(layout);

        builder.setPositiveButton("REGRESAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }
}
