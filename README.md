# tabs
Simple changing colors tabs.

## Using:

### Resources:
  Add colors to res.arrays. Each array corresponds to tab.

### Activity:

  Obtain color arrays via getResources().obtainTypedArray() to an array of TypedArray.
    ```java
    TypedArray[] colorThemes = new TypedArray[]{
                    resources.obtainTypedArray(R.array.color_set_tab_1),
                    resources.obtainTypedArray(R.array.color_set_tab_2),
                    resources.obtainTypedArray(R.array.color_set_tab_3)};
    ```

  Get TabLayout and ViewPager instances.
    ```java
    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
    ```

  Create an instance of ColorChangeListener and provide it TypedArray[], Window and TabLayout.
    ```java
    ColorChangeListener listener = new ColorChangeListener(colors, getWindow(), tabLayout);
    ```

  Attach that listener to ViewPager.
    ```java
    viewPager.addOnPageChangeListener(listener);
    ```

  Setup TabLayout with ViewPager.
    ```java
    tabLayout.setupWithViewPager(viewPager);
    ```

![tab: Tab#1]()
