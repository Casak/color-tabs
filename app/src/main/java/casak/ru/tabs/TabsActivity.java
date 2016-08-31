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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources resources = getResources();
        TypedArray[] colors = new TypedArray[]{
                resources.obtainTypedArray(R.array.color_set_tab_1),
                resources.obtainTypedArray(R.array.color_set_tab_2),
                resources.obtainTypedArray(R.array.color_set_tab_3)};

        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        ColorChangeListener listener = new ColorChangeListener(colors, getWindow(), tabLayout);

        viewPager.addOnPageChangeListener(listener);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                TabsActivity.this));

        tabLayout.setupWithViewPager(viewPager);
    }

    class ColorChangeListener implements ViewPager.OnPageChangeListener {
        private TypedArray[] colors;
        private ArgbEvaluator colorEvaluator;
        private Window window;
        private TabLayout tabLayout;

        public ColorChangeListener(TypedArray[] colors, Window window, TabLayout tabLayout){
            this.colors = colors;
            this.window = window;
            this.tabLayout = tabLayout;
            colorEvaluator = new ArgbEvaluator();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            TypedArray currentColorSet = colors[position];
            TypedArray nextColor = position < colors.length - 1
                    ? colors[position + 1]
                    : colors[position];

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
            changeColorTheme(colors[position]);
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