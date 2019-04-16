package com.wizy.testapp.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.wizy.testapp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RquestAndMessages extends AppCompatActivity {

    private TextView request, message, home;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rquest_and_messages);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    message.setBackgroundResource(0);// SETTING THE BACKGOUND EMPTY
                    request.setBackground(getDrawable(R.drawable.reactangle_circle_style)); //Setting the backgound rectangle circle
                }else if(position == 1){
                    request.setBackgroundResource(0);// SETTING THE BACKGOUND EMPTY
                    message.setBackground(getDrawable(R.drawable.reactangle_circle_style));//Setting the backgound rectangle circle
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        message = (TextView) findViewById(R.id.message);
        request = (TextView) findViewById(R.id.request);
        home = (TextView) findViewById(R.id.homeTeacher);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.otf");
        home.setTypeface(font);
        request.setTypeface(font);
        message.setTypeface(font);

        message.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                request.setBackgroundResource(0);
                message.setBackground(getDrawable(R.drawable.reactangle_circle_style));

                mViewPager.setCurrentItem(1);
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
                message.setBackgroundResource(0);
                request.setBackground(getDrawable(R.drawable.reactangle_circle_style));
            }
        });



    }
    /*The below function is for Same font to the whole Activity*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0){
                return new RequestForTeacher();
            }else if(position == 1){
                return new MessageForteacher();
            }

            return new RequestForTeacher();

        }


        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

}
