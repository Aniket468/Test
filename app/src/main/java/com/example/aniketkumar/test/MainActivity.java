package com.example.aniketkumar.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private String[] activityTitles;
    private Handler mHandler;
    private ImageButton imageButton;
    private  Button sell;
    int log;
    SharedPreferences sp;
    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the intent
    private static final String TAG_My_ACCOUNT = "my_account";
    private static final String TAG_DEALS = "my_deal";
    private static final String TAG_APP_TUTORIAL = "app_tutorial";
    private static final String TAG_LOGOUT = "logout";
    private static final String TAG_FEEDBACK = "feedback";
    public static String CURRENT_TAG = TAG_My_ACCOUNT;
    private static final String urlNavHeaderBg = "R.drawable.tutorial";
    private static final String urlProfileImg = "R.drawable.account";
    @Override
    protected void onPostResume() {
        super.onPostResume();
        MenuItem mn;
        mn=navigationView.getMenu().getItem(0);
        mn.setChecked(true);

    }


    @Override
    public void onBackPressed() {
        //  super.onBackPressed();


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setTitle("Warning!!!");
        builder.setMessage("Are you really want to Exit");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //  AlertDialog alert=builder.create();
        //alert.show();
        builder.show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//For FullScreen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHandler = new Handler();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        imageButton=(ImageButton)findViewById(R.id.image1);
        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);
        final EditText editText=(EditText)findViewById(R.id.editText1);
        sell=findViewById(R.id.sell_button);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp=getApplicationContext().getSharedPreferences("login",MODE_PRIVATE);
                log=sp.getInt("successful",0);
                if(log==1)
                {
                    startActivity(new Intent(getApplicationContext(),Login_Activity.class));
                    finish();
                }
                else
                {
                 startActivity(new Intent(getApplicationContext(),Selling_Activity.class));
                }
            }
        });









        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String s1=editText.getText().toString();
                if(s1.equals(""))
                {
                 Snackbar.make(view,"Enter Brand to search",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                }
                else
                {
                    Intent intent =new Intent(getApplicationContext(),Search_activity.class);
                    intent.putExtra("search",s1);
                    startActivity(intent);
                }

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "filter", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        MenuItem mn;
        mn=navigationView.getMenu().getItem(0);
        mn.setChecked(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();
    }

    private void loadNavHeader() {
        // name, website
        txtName.setText("Aniket");
        txtWebsite.setText("www.aniket.com");

        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);
        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);
        // showing dot next to logout label
        navigationView.getMenu().getItem(4).setActionView(R.layout.menu);
    }


    private void setUpNavigationView() {
        MenuItem mn;
        mn=navigationView.getMenu().getItem(0);
        mn.setChecked(true);
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        navItemIndex = 0;
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_account:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_My_ACCOUNT;
                        menuItem.setChecked(false);
                        startActivity(new Intent(MainActivity.this, My_account.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_deals:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_DEALS;
                        startActivity(new Intent(MainActivity.this, My_deals.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_app_tutorial:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_APP_TUTORIAL;

                        break;
                    case R.id.nav_logout:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_LOGOUT;
                        break;
                    case R.id.nav_feedback:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_FEEDBACK;
                        startActivity(new Intent(MainActivity.this, Feedback.class));
                        drawer.closeDrawers();
                        return true;
//abc//cghsx

                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, About_us.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_rateus:
                        String url = "https://play.google.com/store/apps/details?id=com.mnnit.athleticmeet&hl=en";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        drawer.closeDrawers();
                        startActivity(i);
                        return true;
                    case R.id.nav_share:
                        try {
                            Intent i1 = new Intent(Intent.ACTION_SEND);
                            i1.setType("text/plain");
                            i1.putExtra(Intent.EXTRA_SUBJECT, "SELL-C");
                            String sAux = "\nDownload the Sell-C App for Selling and Buying Cycle At MNNIT Campus \n\n";
                            sAux = sAux + "https://play.google.com/store/apps/details?id=com.mnnit.athleticmeet&hl=en \n\n";
                            i1.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(i1, "choose one"));
                        } catch(Exception e) {
                            //e.toString();
                        }
                        drawer.closeDrawers();
                        return  true;

                    default:
                        navItemIndex = 0;
                }
                //Checking if the item is in checked state or not, if not make it in checked state

//                if (menuItem.isChecked()) {
//                    menuItem.setChecked(false);
//                } else {
//                    menuItem.setChecked(true);
//                }
                //menuItem.setChecked(true);


                return true;
            }
        });

    }
}

