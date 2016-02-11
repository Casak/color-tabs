package casak.ru.tabs;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.Window;

public class TabsActivity extends ActionBarActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
// Get the ViewPager and set it's PagerAdapter so that it can display items

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                TabsActivity.this));

        // Give the TabLayout the ViewPager

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        switch(tab.getPosition()){
                            case 0:changeColor(getResources().obtainTypedArray(R.array.colorSetTop250)); break;
                            case 1:changeColor(getResources().obtainTypedArray(R.array.colorSetComingSoon)); break;
                            case 2:changeColor(getResources().obtainTypedArray(R.array.colorSetFavorite)); break;
                            default:changeColor(getResources().obtainTypedArray(R.array.colorSetTop250)); break;
                        }
                    }
                });
    }

    private void changeColor(TypedArray colors){
        getWindow().setStatusBarColor(colors.getColor(1,0));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(colors.getColor(0, 0)));
        tabLayout.setBackgroundColor(colors.getColor(3, 0));
        tabLayout.setSelectedTabIndicatorColor(colors.getColor(2, 0));
        getWindow().setNavigationBarColor(colors.getColor(3,0));
    }
}
