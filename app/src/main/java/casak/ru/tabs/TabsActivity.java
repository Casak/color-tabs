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
import android.view.DragEvent;
import android.view.View;
import android.view.Window;

public class TabsActivity extends ActionBarActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private static TypedArray COLOR_THEME_TOP250;
    private static TypedArray COLOR_THEME_COMMING_SOON;
    private static TypedArray COLOR_THEME_FAV;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
// Get the ViewPager and set it's PagerAdapter so that it can display items

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                TabsActivity.this));

        COLOR_THEME_TOP250 = getResources().obtainTypedArray(R.array.colorSetTop250);
        COLOR_THEME_COMMING_SOON = getResources().obtainTypedArray(R.array.colorSetComingSoon);
        COLOR_THEME_FAV = getResources().obtainTypedArray(R.array.colorSetFavorite);

        // Give the TabLayout the ViewPager

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        switch(tab.getPosition()){
                            case 0:changeColor(COLOR_THEME_TOP250); break;
                            case 1:changeColor(COLOR_THEME_COMMING_SOON); break;
                            case 2:changeColor(COLOR_THEME_FAV); break;
                            default:changeColor(COLOR_THEME_TOP250); break;
                        }
                    }
                });
    }

    private void changeColor(TypedArray colors){
        changeInterfaceHeadColorTheme(
                colors.getColor(0,0),
                colors.getColor(1,0),
                colors.getColor(2,0),
                colors.getColor(3,0)
        );
        /*getWindow().setStatusBarColor(colors.getColor(1,0));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(colors.getColor(0, 0)));
        tabLayout.setBackgroundColor(colors.getColor(3, 0));
        tabLayout.setSelectedTabIndicatorColor(colors.getColor(2, 0));
        getWindow().setNavigationBarColor(colors.getColor(3,0));*/
    }

    private void changeInterfaceHeadColorTheme(Integer zero_color, Integer status_bar_color, Integer selected_tab_color, Integer background_color) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(zero_color));
        getWindow().setStatusBarColor(status_bar_color);
        tabLayout.setBackgroundColor(background_color);
        tabLayout.setSelectedTabIndicatorColor(selected_tab_color);
        getWindow().setNavigationBarColor(background_color);
    }
}
