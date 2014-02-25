package com.droid.keja.ui;

import android.animation.Animator;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.droid.keja.R;

import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Created by james on 22/02/14.
 */
public class HomesActivity extends ActionBarActivity {

    private static boolean TXT_EXPANDED = false;
    private static final String LOG_TAG = "HomesActivity";

    private ActionBar actionBar;
    private ShareActionProvider shareActionProvider;

    private TextView homeDesc_txt;
    private ScrollView requestInfoView;
    private Button requestInfo_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homes);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initViews();

    }

    private void initViews(){
        homeDesc_txt = (TextView)findViewById(R.id.home_desc_preview);
        requestInfoView = (ScrollView)findViewById(R.id.section_home_scrollView);
        requestInfo_btn = (Button)findViewById(R.id.request_info_BTN);

    }

    public void btnClickListener(View view){
        Intent imageSlider;

        switch (view.getId()){

            case R.id.img_count: //show fullscreen image slider
                 imageSlider = new Intent(this, ImageSliderActivity.class);
                startActivity(imageSlider);
                break;

            case R.id.home_thumbnail_pic: //show full screen image slider
                imageSlider = new Intent(this, ImageSliderActivity.class);
                startActivity(imageSlider);
                break;

            case R.id.request_info_BTN: //show request info section
                requestInfoView.fullScroll(View.FOCUS_DOWN);
                break;

            case R.id.location_direction_btn: //show directions..directionsAPI
                Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.location_map_btn: //show home on map
                Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.info_agent_call_btn: //call realtor
                try{
                    call(getString(R.string.info_agent_no));
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(this, "oops..something's wrong!", Toast.LENGTH_SHORT).show();
                    Log.e(LOG_TAG, "Calling: "+ex.getMessage());
                }
                break;

            case R.id.info_agent_email_btn: //email realtor
                String destEmail = getString(R.string.info_agent_email);
                String subject = getString(R.string.info_agent_email_subject);
                String message = getString(R.string.info_agent_email_compose);
                sendEmail(destEmail, null, subject, message);
                break;
            default:
                break;
        }
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

    private void call(String phoneNo) throws ActivityNotFoundException{
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneNo));
        startActivity(callIntent);
    }

    private void sendEmail(String destEmail, String cc, String subject, String message){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{destEmail});
//        emailIntent.putExtra(Intent.EXTRA_CC, new String[]{cc});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Choose an Email Client"));
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
