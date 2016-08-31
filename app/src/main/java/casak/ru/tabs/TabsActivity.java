package casak.ru.tabs;

import android.animation.ArgbEvaluator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class TabsActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    private static ArgbEvaluator colorEvaluator;
    private Window window;
    private static TypedArray[] colorThemes;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) window = getWindow();

        colorEvaluator = new ArgbEvaluator();

        Resources resources = getResources();
        colorThemes = new TypedArray[]{
                resources.obtainTypedArray(R.array.color_set_tab_1),
                resources.obtainTypedArray(R.array.color_set_tab_2),
                resources.obtainTypedArray(R.array.color_set_tab_3)};

        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ColorChangeListener());
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                TabsActivity.this));

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    class ColorChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            TypedArray currentColorSet = colorThemes[position];
            TypedArray nextColor = position < colorThemes.length - 1
                    ? colorThemes[position + 1]
                    : colorThemes[position];

            Integer[] a = new Integer[4];
            for (int i = 0; i < 4; i++) {
                a[i] = (Integer) colorEvaluator.evaluate(
                        positionOffset,
                        currentColorSet.getColor(i, 0),
                        nextColor.getColor(i, 0)
                );
            }

            changeInterfaceHeadColorTheme(a[0], a[1], a[2], a[3]);
        }

        @Override
        public void onPageSelected(int position) {
            changeColorTheme(colorThemes[position]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        private void changeColorTheme(TypedArray colorSet) {
            changeInterfaceHeadColorTheme(
                    colorSet.getColor(0, 0),
                    colorSet.getColor(1, 0),
                    colorSet.getColor(2, 0),
                    colorSet.getColor(3, 0)
            );
        }

        private void changeInterfaceHeadColorTheme(Integer actionBarColor, Integer statusBarColor,
                                                   Integer tabIndicatorColor, Integer backgroundColor) {

            try{
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(actionBarColor));
            } catch (NullPointerException e){
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(statusBarColor);
                window.setNavigationBarColor(backgroundColor);
            }
            tabLayout.setBackgroundColor(backgroundColor);
            tabLayout.setSelectedTabIndicatorColor(tabIndicatorColor);
        }
    }
}