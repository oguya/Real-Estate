package com.droid.keja.ui;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.droid.keja.R;

import org.w3c.dom.Text;

/**
 * Created by james on 22/02/14.
 */
public class HomesActivity extends ActionBarActivity {

    private ActionBar actionBar;
    private final String LOG_TAG = "HomesActivity";

    private ShareActionProvider shareActionProvider;
    private TextView homeDesc_txt;
    private static boolean TXT_EXPANDED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homes);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        homeDesc_txt = (TextView)findViewById(R.id.home_desc_preview);
    }

    public void ExpandTextView(View view){
        Toast.makeText(getApplicationContext(), "Clicked!", Toast.LENGTH_LONG).show();
        Animation textViewAnimator;

        if(TXT_EXPANDED){
            //minimize textview
            TXT_EXPANDED = false;
            homeDesc_txt.setMaxLines(2);
            homeDesc_txt.setEllipsize(TextUtils.TruncateAt.END);
            homeDesc_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.expand, 0);

            textViewAnimator = AnimationUtils.loadAnimation(this, R.anim.slide_up);
            homeDesc_txt.clearAnimation();
            homeDesc_txt.setAnimation(textViewAnimator);
            homeDesc_txt.startAnimation(textViewAnimator);

        }else{
            //maximize textview
            TXT_EXPANDED = true;
            homeDesc_txt.setMaxLines(10000);
            homeDesc_txt.setEllipsize(null);
            homeDesc_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.minimize, 0);

            textViewAnimator = AnimationUtils.loadAnimation(this, R.anim.slide_down);
            homeDesc_txt.clearAnimation();
            homeDesc_txt.setAnimation(textViewAnimator);
            homeDesc_txt.startAnimation(textViewAnimator);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homes, menu);

        //shareAction provider
        MenuItem shareItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        shareActionProvider.setShareIntent(getDefaultShareIntent());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Intent getDefaultShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        return intent;
    }

    @Override
    public void onPause(){
        super.onPause();

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
}
