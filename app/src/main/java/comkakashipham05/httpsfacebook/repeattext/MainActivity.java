package comkakashipham05.httpsfacebook.repeattext;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import comkakashipham05.httpsfacebook.repeattext.Fragment.GioiThieuFragment;
import comkakashipham05.httpsfacebook.repeattext.Fragment.MainFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Dialog dialog;
    private AdView ad_banner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        replaceFragment(new MainFragment());
        connectads();
        //nho phim tat Ctrl+alt+m de extra method
        //de kiem code google
    }
    public void connectads() {
        ad_banner = (AdView) findViewById(R.id.av_banner);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        ad_banner.loadAd(adRequest);
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentView, fragment,"tag");
        fragmentTransaction.commit();
    }
    private void initViews() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // tạo menu mới
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Sự kiện click item
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.version) {
            dialog = new Dialog(MainActivity.this);
            // khởi tạo dialog
            dialog.setContentView(R.layout.dialog_version);
            // xét layout cho dialog
            dialog.setTitle("VERSION");

            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
    public  void haha(){
        AlertDialog.Builder dialog_version = new AlertDialog.Builder(this);
        String version = "Repeat Text Version 1.0 \nUpdate 1/1/2017";

        dialog_version.setMessage(version);
        dialog_version.setNegativeButton("Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which)
            {dialog.dismiss();}
        });
        AlertDialog alertDialog = dialog_version.create();
        // tạo dialog
        alertDialog.show();
    }

    public void logout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // khởi tạo dialog
        alertDialogBuilder.setMessage("Bạn có muốn thoát không");
        // thiết lập nội dung cho dialog
        alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
                // button "Có" thoát khỏi ứng dụng
            }
        });

        alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // button "no" ẩn dialog đi
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // tạo dialog
        alertDialog.show();
        // hiển thị dialog
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.repeattext) {
            replaceFragment(new MainFragment());
            // Handle the camera action
        } else if (id == R.id.contact) {
            replaceFragment(new GioiThieuFragment());

        } else if (id == R.id.btn_rate) {

        }
        else if (id == R.id.btnout) {
            logout();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
