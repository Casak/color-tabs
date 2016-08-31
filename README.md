# color-tabs
Simple changing colors tabs.

![tab: Tab#1](https://github.com/Casak/color-tabs/blob/master/tabs-example.gif)

## Using:

### Resources:
  Add colors to res.arrays. Each array corresponds to tab. First item is ActionBar color, second - StatusBar color, third - TabIndicator color and the last one is background color.

  ```xml
  <array name="color_set_tab_1">
        <item>#9C27B0</item>
        <item>#6A1B9A</item>
        <item>#69F0AE</item>
        <item>#E1BEE7</item>
    </array>
  ```

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