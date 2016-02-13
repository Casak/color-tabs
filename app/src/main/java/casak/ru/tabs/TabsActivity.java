package casak.ru.tabs;

import android.animation.ArgbEvaluator;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.design.widget.TabLayout;
import android.view.Window;

public class TabsActivity extends ActionBarActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private static TypedArray COLOR_THEME_TOP250;
    private static TypedArray COLOR_THEME_COMMING_SOON;
    private static TypedArray COLOR_THEME_FAV;
    private static TypedArray[] color_themes = new TypedArray[3];

    private ArgbEvaluator color_evaluator = new ArgbEvaluator();
    private Window window;

    private Integer _next_color =  -1;

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
        color_themes[0] = COLOR_THEME_TOP250;
        color_themes[1] = COLOR_THEME_COMMING_SOON;
        color_themes[2] = COLOR_THEME_FAV;

        // Give the TabLayout the ViewPager

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        window = getWindow();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                TypedArray current_color_theme = color_themes[position];
                TypedArray next_color_theme = position < color_themes.length - 1
                        ? color_themes[position + 1]
                        : color_themes[position];

                Integer[] a = new Integer[4];
                for ( int i = 0; i < 4 ; i++ ) {
                    a[i] = (Integer) color_evaluator.evaluate(
                            positionOffset,
                            current_color_theme.getColor(i, 0),
                            next_color_theme.getColor(i, 0)
                    );
                }

                changeInterfaceHeadColorTheme(a[0], a[1], a[2], a[3]);
            }

            @Override
            public void onPageSelected(int position) {
                changeColorTheme(color_themes[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void changeColorTheme(TypedArray color_theme){
        changeInterfaceHeadColorTheme(
                color_theme.getColor(0, 0),
                color_theme.getColor(1, 0),
                color_theme.getColor(2, 0),
                color_theme.getColor(3, 0)
        );
    }

    private void changeInterfaceHeadColorTheme(Integer action_bg_color, Integer status_bar_color, Integer selected_tab_color, Integer background_color) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(action_bg_color));
        window.setStatusBarColor(status_bar_color);
        window.setNavigationBarColor(background_color);

        tabLayout.setBackgroundColor(background_color);
        tabLayout.setSelectedTabIndicatorColor(selected_tab_color);
    }
}
