package com.bobo.myyoulu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RadioGroup;

import com.bobo.myyoulu.R;
import com.bobo.myyoulu.fragment.ContactFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private List<Fragment> fragments;
    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        setAdapter();
        setListeners();
    }

    private void setAdapter() {
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    private void setListeners() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.radio0);
                        break;
                    case 1:
                        radioGroup.check(R.id.radio1);
                        break;
                    case 2:
                        radioGroup.check(R.id.radio2);
                        break;
                    case 3:
                        radioGroup.check(R.id.radio3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.radio1:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.radio2:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.radio3:
                        viewPager.setCurrentItem(3);
                        break;
                }

            }
        });
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        fragments = new ArrayList<>();
        fragments.add(new ContactFragment());
        fragments.add(new ContactFragment());
        fragments.add(new ContactFragment());
        fragments.add(new ContactFragment());
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
