# StateView

An alternate state control for views for Android. Can be used to show that a RecyclerView or any other View has no data

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)  
![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)[ ![Download](https://api.bintray.com/packages/kishannareshpal/maven/stateview/images/download.svg?version=1.2)](https://bintray.com/kishannareshpal/maven/stateview/1.2/link)

## 💪 Installation

Add the library to the **dependencies { ... }** section of your **app** level `build.gradle` file:

```groovy
// Refer to the badge named Download for the version number.
implementation 'com.kishannareshpal:stateview:<version>'
```

## 🍺 Usage

### In the layout:

```markup
<com.kishannareshpal.stateview.StateView
     android:id="@+id/stateview"
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     app:stateMode="normal">

        <!-- 
         Place 1 child view here.
         E.g.: <RecyclerView .../> 
        -->
</com.kishannareshpal.stateview.StateView>
```

> **NOTE: You can only add One \(1\) child view to StateView.**  
> If you try adding more than one child view, you will get an IllegalStateException.



### Attributes

<table>
  <thead>
    <tr>
      <th style="text-align:left"><b>Attribute</b>
      </th>
      <th style="text-align:left"><b>Description</b>
      </th>
      <th style="text-align:left"><b>Data Type</b>
      </th>
      <th style="text-align:left"><b>Enums</b>
      </th>
      <th style="text-align:left"><b>Default Value</b>
      </th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b><code>app:stateMode</code></b>
      </td>
      <td style="text-align:left">Sets the state mode.</td>
      <td style="text-align:left">enum</td>
      <td style="text-align:left"><b><code>normal<br />alternate</code></b>
      </td>
      <td style="text-align:left"><b><code>normal</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateBackgroundColor</code></b>
      </td>
      <td style="text-align:left">Sets the background color for the <b>alternate</b> state mode.</td>
      <td style="text-align:left">color</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">
        <p><code>#FFFFFF</code>
        </p>
        <p>(white)</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateGravity</code></b>
      </td>
      <td style="text-align:left">Change the <b>alternate</b> state components gravity (icon, title, icons,
        progress bars, description, button)</td>
      <td style="text-align:left">enum</td>
      <td style="text-align:left">
        <p><code>left</code>
        </p>
        <p><code>center</code>
        </p>
        <p><code>right</code>
        </p>
      </td>
      <td style="text-align:left"><code>center</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateContentPaddingLeft</code></b>
      </td>
      <td style="text-align:left">Change the left content padding.</td>
      <td style="text-align:left">dimension (dp)</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>18dp</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateContentPaddingTop</code></b>
      </td>
      <td style="text-align:left">Change the top content padding.</td>
      <td style="text-align:left">dimension (dp)</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>18dp</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateContentPaddingRight</code></b>
      </td>
      <td style="text-align:left">Change the right content padding.</td>
      <td style="text-align:left">dimension (dp)</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>18dp</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateContentPaddingBottom</code></b>
      </td>
      <td style="text-align:left">Change the bottom content padding.</td>
      <td style="text-align:left">dimension (dp)</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>18dp</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateMainProgressEnabled</code></b>
      </td>
      <td style="text-align:left">If the main circular progress is enabled/shown.</td>
      <td style="text-align:left">boolean</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>true</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateSmallProgressEnabled</code></b>
      </td>
      <td style="text-align:left">If the small circular progress is enabled/shown.</td>
      <td style="text-align:left">boolean</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>true</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateProgressStrokeColor</code></b>
      </td>
      <td style="text-align:left">Sets the circular progress stroke color.</td>
      <td style="text-align:left">color</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">
        <p><code>#000</code>
        </p>
        <p>(black)</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateProgressBackgroundColor</code></b>
      </td>
      <td style="text-align:left">Sets the circular progress background color</td>
      <td style="text-align:left">color</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">
        <p><code>#00000000</code>
        </p>
        <p>(transparent)</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateIcon</code></b>
      </td>
      <td style="text-align:left">Sets a <b>56x56dp</b> image.</td>
      <td style="text-align:left">drawable</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>null</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateTitle</code></b>
      </td>
      <td style="text-align:left">Sets the title text.</td>
      <td style="text-align:left">string</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>null</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateTitleTextSize</code></b>
      </td>
      <td style="text-align:left">Sets the title text size.</td>
      <td style="text-align:left">dimension (sp)</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>24sp</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateTitleTextColor</code></b>
      </td>
      <td style="text-align:left">Sets the title text color.</td>
      <td style="text-align:left">color</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">
        <p><code>#000</code>
        </p>
        <p>(black)</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateTitleTextAppearance</code></b>
      </td>
      <td style="text-align:left">Customize color, fontFamily, size via style for title text.</td>
      <td style="text-align:left">StyleRes</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>@style/StateViewTheme.TitleTextAppearance</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateDescription</code></b>
      </td>
      <td style="text-align:left">Sets the description text.</td>
      <td style="text-align:left">string</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>null</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateDescriptionTextSize</code></b>
      </td>
      <td style="text-align:left">Sets the description text size.</td>
      <td style="text-align:left">dimension (sp)</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>16sp</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateDescriptionTextColor</code></b>
      </td>
      <td style="text-align:left">Sets the description text color</td>
      <td style="text-align:left">color</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">
        <p><code>#3f3f3f</code>
        </p>
        <p>(dark grey)</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateDescriptionTextAppearance</code></b>
      </td>
      <td style="text-align:left">Customize color, fontFamily, size via style for description text.</td>
      <td
      style="text-align:left">StyleRes</td>
        <td style="text-align:left">n/a</td>
        <td style="text-align:left"><code>@style/StateViewTheme.DescriptionTextAppearance</code>
        </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateActionButtonText</code></b>
      </td>
      <td style="text-align:left">Sets the action button text.</td>
      <td style="text-align:left">string</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left"><code>null</code>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateActionButtonTextColor</code></b>
      </td>
      <td style="text-align:left">Sets the action button text color.</td>
      <td style="text-align:left">color</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">
        <p><code>#fff</code>
        </p>
        <p>(white)</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateActionButtonColor</code></b>
      </td>
      <td style="text-align:left">Sets the action button background color.</td>
      <td style="text-align:left">color</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">
        <p><code>#000</code>
        </p>
        <p>(black)</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateActionButtonCornerRadius</code></b>
      </td>
      <td style="text-align:left">Sets the action button corner radius.</td>
      <td style="text-align:left">dimension (dp)</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">rounded (64dp)</td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>app:stateActionButtonTextAppearance</code></b>
      </td>
      <td style="text-align:left">Customize color, fontFamily, size via style for action button text.</td>
      <td
      style="text-align:left">StyleRes</td>
        <td style="text-align:left">n/a</td>
        <td style="text-align:left"><code>@style/StateViewTheme.ActionButton<br />TextAppearance</code>
        </td>
    </tr>
  </tbody>
</table>

### Theming

You can overwrite `StateViewTheme` in your styles.xml to apply your customized theme for the StateView:

```markup
<styles name="MyStateViewTheme" parent="StateViewTheme">
    <!-- Customize the stateview here with any attribute listed
       on the atrributes table above. 
    -->

    <!-- Example: -->
    <item name="stateBackgroundColor">#ff6363</item>
    <item name="stateActionButtonCornerRadius">8dp</item>
    …    
</styles>
```

After you have your styles `MyStateViewTheme` set, use it by applying the style attribute to the StateView in your layout file as follows:

```markup
<com.kishannareshpal.stateview.StateView
    …
    style="@style/MyStateViewTheme">
    
</com.kishannareshpal.stateview.StateView>
```



### TextAppearance Styling \(text color, font, size, spacing, ...\)

To customise the default text appearance for title, description and actionButton you can override the following styles:

| Style names | Description |
| :--- | :--- |
| `StateViewTheme.TitleTextAppearance` | Styles the title text. |
| `StateViewTheme.DescriptionTextAppearance` | Styles the description text. |
| `StateViewTheme.ActionButtonTextAppearance` | Styles the action button text. |



For example, if you want to style the title, add this to your `styles.xml`

```markup
<styles name="MyTitleTextAppearance" parent="StateViewTheme.TitleTextAppearance">
    <!-- Customize the text appearance for the title -->

    <!-- Example: -->
    <item name="android:fontFamily">@font/times_new_roman</item>
    <item name="android:textColor">@color/blue</item>
    <item name="android:textSize">48sp</item>
    …    
</styles>
```

Now either apply it to the attribute `app:stateTitleTextAppearance` in your layout:

```markup
<!-- The same applies to
      • app:stateDescriptionTextAppearance
      • app:stateActionButtonTextAppearance 
 -->
<com.kishannareshpal.stateview.StateView
     …
     app:stateTitleTextAppearance="@style/MyTitleTextAppearance">
     
</com.kishannareshpal.stateview.StateView>
```

Or in your custom StateView theme:

```markup
<styles name="MyStateViewTheme" parent="StateViewTheme">
    …    
    <item name="stateTitleTextAppearance">@style/MyTitleTextAppearance</item>
    <!-- The same applies to
      • <item name="stateDescriptionTextAppearance">…</item>
      • <item name="stateActionButtonTextAppearance">…</item> 
     -->
    
</styles>
```



### Methods: List

#### Getters

| Name | Return type | Description |
| :--- | :--- | :--- |
| **`getState()`** | **`State`** | Gets the currently set state. |
| **`getTitleText()`** | **`@Nullable CharSequence`** | Gets the currently set title text. |
| **`getDescriptionText()`** | **`@Nullable CharSequence`** | Gets the currently set description text. |
| **`getActionButton()`** | **`MaterialButton`** | Exposes the action button. May be used to customise stuff like adding an icon. |

#### 

#### Setter

<table>
  <thead>
    <tr>
      <th style="text-align:left">Name</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b><code>void backgroundColor(@ColorInt int backgroundColor)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void contentPadding(int left, int top, int right, int bottom)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void contentPadding(int padding)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void gravity(ComponentGravity gravity)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void mainIcon(@Nullable @DrawableRes Integer iconRes, boolean isGif)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void mainProgressEnabled(boolean mainProgressEnabled)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void smallProgressEnabled(boolean smallProgressEnabled)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void progressStrokeColor(@ColorInt int progressStrokeColor)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void progressBackgroundColor(@ColorInt int progressBackgroundColor)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left">
        <p><b><code>void state(State state)</code></b>
        </p>
        <p><b><code>void state(State state, boolean animated)</code></b>
        </p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left">
        <p><b><code>void title(@Nullable CharSequence title)</code></b>
        </p>
        <p><b><code>void title(@Nullable CharSequence title, AnimationType titleTextChangeAnimationType)</code></b>
        </p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void titleTextSize(int titleTextSize)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void titleTextColor(@ColorInt int titleTextColor)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void description(@Nullable CharSequence description)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void description(@Nullable CharSequence description, AnimationType descriptionTextChangeAnimationType) </code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void descriptionTextColor(@ColorInt int descriptionTextColor)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void descriptionTextSize(int descriptionTextSize) </code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void actionButtonText(@Nullable CharSequence actionButtonText)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void actionButtonCornerRadius(int actionButtonCornerRadius)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void actionButtonTextColor(@ColorInt int actionButtonTextColor) </code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void actionButtonColor(@ColorInt int actionButtonColor)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void onActionButtonClick(OnActionButtonClickListener onActionButtonClickListener)</code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void actionButtonEnabled(boolean enabled) </code></b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b><code>void actionButtonIsVisible(boolean isVisible) </code></b>
      </td>
    </tr>
  </tbody>
</table>

### Methods: Details

#### `backgroundColor`

Changes the default background color of the state view in **`State#NORMAL`**.

```java
public void backgroundColor (@ColorInt int backgroundColor)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>backgroundColor</b>
      </td>
      <td style="text-align:left">
        <p><b>@ColorInt int: </b>the background color you want to set.</p>
        <p>Use <a href="https://developer.android.com/reference/android/support/v4/content/ContextCompat#getcolor">ContextCompat#getColor(Context, int)</a>
        </p>
      </td>
    </tr>
  </tbody>
</table>

#### `contentPadding`

Change the content padding.

```java
public void contentPadding (int left, int top, int right, int bottom)
public void contentPadding (int padding)
```

| Parameter | Description |
| :--- | :--- |
| **padding** | **int:** all sides padding. |
| **left** | **int:** left side padding. |
| **top** | **int:** top side padding. |
| **right** | **int:** right side padding. |
| **bottom** | **int:** bottom side padding. |





#### `gravity`

Change the gravity of the state view components in `State#ALTERNATE`.

```java
public void gravity (ComponentGravity gravity)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>gravity</b>
      </td>
      <td style="text-align:left">
        <p><b>ComponentGravity: </b>the gravity of title, main progress, description
          and button on <b><code>State#ALTERNATE</code></b> .
          <br />This is an enum consisting of three (3) values:</p>
        <ul>
          <li><b>LEFT</b>: to set the gravity to the left (start)</li>
          <li><b>CENTER</b>: to set the gravity to the center.</li>
          <li><b>RIGHT</b>: to set the gravity to the right (end).</li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>



#### `mainIcon`

Change the icon.

```java
public void mainIcon (@Nullable @DrawableRes Integer iconRes, boolean isGif)
```

| Parameter | Description |
| :--- | :--- |
| **iconRes** | **@Nullable @DrawableRes Integer:** the drawable resource identifier. If `null`, icon will be hidden. |
| **isGif** | **boolean**: set this to true if you are passing a **.gif** file to iconRes to view it animate infinitely. |



#### `mainProgressEnabled`

Shows/hides the main circular progress.

```java
public void mainProgressEnabled (boolean mainProgressEnabled)
```

| Parameter | Description |
| :--- | :--- |
| **mainProgressEnabled** | **boolean:** set this to true to show the main circular progress \(this will replace the icon\). If false, the main progress will be hidden \(and be replaced with the icon if `mainIcon` is set\). |



#### `smallProgressEnabled`

Shows/hides the small circular progress.

```java
public void smallProgressEnabled (boolean smallProgressEnabled)
```

| Parameter | Description |
| :--- | :--- |
| **smallProgressEnabled** | **boolean:** set this to true to show the small circular progress in front of the description text. If false, the small progress will be hidden. |



#### `progressStrokeColor` <a id="smallprogressenabled"></a>

Change all circular progress stroke color.

```text
public void progressStrokeColor (@ColorInt int progressStrokeColor)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>progressStrokeColor</b>
      </td>
      <td style="text-align:left">
        <p><b>@ColorInt int:</b> the stroke color of the main and small circular progress.</p>
        <p>Use <a href="https://developer.android.com/reference/android/support/v4/content/ContextCompat#getcolor">ContextCompat#getColor(Context, int)</a>
        </p>
      </td>
    </tr>
  </tbody>
</table>

#### `progressBackgroundColor` <a id="smallprogressenabled"></a>

Change all circular progress background color.



```text
public void progressBackgroundColor (@ColorInt int progressBackgroundColor)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>progressBackgroundColor</b>
      </td>
      <td style="text-align:left">
        <p><b>@ColorInt int:</b> the background color of the main and small circular
          progress.</p>
        <p>Use <a href="https://developer.android.com/reference/android/support/v4/content/ContextCompat#getcolor">ContextCompat#getColor(Context, int)</a>
        </p>
      </td>
    </tr>
  </tbody>
</table>

#### `state` <a id="smallprogressenabled"></a>

Change the state.

```java
public void state (State state)
public void state (State state, boolean animated)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>state</b>
      </td>
      <td style="text-align:left">
        <p><b>State:</b> the state to which you want to change.</p>
        <p>This is an enum consisting of two (2) values:</p>
        <ul>
          <li><b>NORMAL</b>: use this to view the original view.</li>
          <li><b>ALTERNATE</b>: use this for showing the StateView Title, Description
            and other components.</li>
        </ul>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b>animated</b>
      </td>
      <td style="text-align:left">
        <p><b>boolean: </b>if true, show a slight fade animation on the changing
          of states.</p>
        <p></p>
      </td>
    </tr>
  </tbody>
</table>

#### `title` <a id="smallprogressenabled"></a>

Change the title text.

```java
public void title (@Nullable CharSequence title)
public void title (@Nullable CharSequence title, AnimationType titleTextChangeAnimationType)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>title</b>
      </td>
      <td style="text-align:left">
        <p><b>@Nullable CharSequence:</b> the title text.</p>
        <p>If <code>null</code>, title will be hidden.</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b>titleTextChangeAnimationType</b>
      </td>
      <td style="text-align:left">
        <p><b>AnimationType: </b>the animation type to use when changing the title
          text.
          <br />This is an enum consisting of four (4) values:</p>
        <ul>
          <li><b>NO_ANIMATION</b>: does not animate the text change. Same as <code>title(CharSequence)</code>
          </li>
          <li><b>SLIDE_TO_TOP</b>: slides the old text to top and back to normal with
            the new text.</li>
          <li><b>SLIDE_TO_BOTTOM</b>: slides the old text to bottom and back to normal
            with the new text.</li>
          <li><b>FADE</b>: fades out the old text and fades in the new text.</li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>

#### `titleTextSize` <a id="smallprogressenabled"></a>

Change the title text size.

```java
public void titleTextSize (int titleTextSize)
```

| Parameter | Description |
| :--- | :--- |
| **titleTextSize** | **int:** the title text size in `sp`. |



#### `titleTextColor` <a id="smallprogressenabled"></a>

Change the title text color

```java
public void titleTextColor (@ColorInt int titleTextColor)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>titleTextColor</b>
      </td>
      <td style="text-align:left">
        <p><b>@ColorInt int:</b> the title text color.</p>
        <p>Use <a href="https://developer.android.com/reference/android/support/v4/content/ContextCompat#getcolor">ContextCompat#getColor(Context, int)</a>
        </p>
      </td>
    </tr>
  </tbody>
</table>

#### `description` <a id="smallprogressenabled"></a>

Change the description text.

```java
public void description (@Nullable CharSequence description)
public void description (@Nullable CharSequence description, AnimationType descriptionTextChangeAnimationType)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>description</b>
      </td>
      <td style="text-align:left">
        <p><b>@Nullable CharSequence:</b> the description text.</p>
        <p>If <code>null</code>, description will be hidden.</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left"><b>descriptionTextChangeAnimationType</b>
      </td>
      <td style="text-align:left">
        <p><b>AnimationType: </b>the animation type to use when changing the description
          text.
          <br />This is an enum consisting of four (4) values:</p>
        <ul>
          <li><b>NO_ANIMATION</b>: does not animate the text change. Same as <code>title(CharSequence)</code>
          </li>
          <li><b>SLIDE_TO_TOP</b>: slides the old text to top and back to normal with
            the new text.</li>
          <li><b>SLIDE_TO_BOTTOM</b>: slides the old text to bottom and back to normal
            with the new text.</li>
          <li><b>FADE</b>: fades out the old text and fades in the new text.</li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>

#### `descriptionTextSize` <a id="smallprogressenabled"></a>

Change the description text size.

```java
public void descriptionTextSize (int descriptionTextSize)
```

| Parameter | Description |
| :--- | :--- |
| **descriptionTextSize** | **int:** the description text size in `sp`. |



#### `descriptionTextColor` <a id="smallprogressenabled"></a>

Change the description text color

```java
public void descriptionTextColor (@ColorInt int descriptionTextColor)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>descriptionTextColor</b>
      </td>
      <td style="text-align:left">
        <p><b>@ColorInt int:</b> the description text color.</p>
        <p>Use <a href="https://developer.android.com/reference/android/support/v4/content/ContextCompat#getcolor">ContextCompat#getColor(Context, int)</a>
        </p>
      </td>
    </tr>
  </tbody>
</table>



#### `actionButtonText` <a id="smallprogressenabled"></a>

Change the action button text.

```java
public void actionButtonText (@Nullable CharSequence actionButtonText)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>actionButtonText</b>
      </td>
      <td style="text-align:left">
        <p><b>@Nullable CharSequence:</b> the action button text.</p>
        <p>If <code>null</code> , action button will be hidden.</p>
      </td>
    </tr>
  </tbody>
</table>

#### `actionButtonColor` <a id="smallprogressenabled"></a>

Change the action button background color.

```java
public void actionButtonColor (@ColorInt int actionButtonColor)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>actionButtonColor</b>
      </td>
      <td style="text-align:left">
        <p><b>@ColorInt int:</b> the action button background color.</p>
        <p>Use <a href="https://developer.android.com/reference/android/support/v4/content/ContextCompat#getcolor">ContextCompat#getColor(Context, int)</a>
        </p>
      </td>
    </tr>
  </tbody>
</table>

#### `actionButtonCornerRadius` <a id="smallprogressenabled"></a>

Change the action button corner radius.

```java
public void actionButtonCornerRadius (int actionButtonCornerRadiues)
```

| Parameter | Description |
| :--- | :--- |
| **actionButtonCornerRadius** | **int:** corner radius in sp. |



#### `actionButtonTextColor` <a id="smallprogressenabled"></a>

Change the action button text color.

```java
public void actionButtonTextColor (@ColorInt int actionButtonTextColor)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>actionButtonTextColor</b>
      </td>
      <td style="text-align:left">
        <p><b>@ColorInt int:</b> the action button text color.</p>
        <p>Use <a href="https://developer.android.com/reference/android/support/v4/content/ContextCompat#getcolor">ContextCompat#getColor(Context, int)</a>
        </p>
      </td>
    </tr>
  </tbody>
</table>

#### `onActionButtonClickListener` <a id="smallprogressenabled"></a>

The action to execute when the button is clicked.

```java
public void onActionButtonClickListener (OnActionButtonClickListener onActionButtonClickListener)
```

<table>
  <thead>
    <tr>
      <th style="text-align:left">Parameter</th>
      <th style="text-align:left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left"><b>onActionButtonClickListener</b>
      </td>
      <td style="text-align:left">
        <p><b>OnActionButtonClickListener:</b> Interface definition for a callback
          to be invoked when the action button is clicked.</p>
        <p></p>
        <p><b><code>void OnActionButtonClick(StateView sv, View actionBtn)</code></b>
        </p>
      </td>
    </tr>
  </tbody>
</table>\*\*\*\*



#### `actionButtonEnabled` <a id="smallprogressenabled"></a>

Change the enabled or disabled state of the action button.

```java
public void actionButtonEnabled (boolean actionButtonEnabled)
```

| Parameter | Description |
| :--- | :--- |
| **actionButtonEnabled** | **boolean:** if false, action button will be disabled of any touch input, and will have a visually light grey background. Otherwise, enabled. |



#### `actionButtonIsVisible` <a id="smallprogressenabled"></a>

Changes the action button visibility only.

```java
public void actionButtonIsVisible (boolean isVisible)
```

| Parameter | Description |
| :--- | :--- |
| **isVisible** | **boolean:** if true, action button will be visible, otherwise, hidden. |

## 🩺 Public Enums

### `enum State`

State consists of two \(2\) enum values:

* **NORMAL**: original view.
* **ALTERNATE**: state view alternate view with custom Title, Description and other components.



### `enum ComponentGravity`

ComponentGravity consists of four \(3\) enum values:

* **LEFT**: to set the gravity to the left \(start\)
* **CENTER**: to set the gravity to the center/middle.
* **RIGHT**: to set the gravity to the right \(end\).



### `enum AnimationType`

AnimationType consists of four \(4\) enum values:

* **NO\_ANIMATION**: no animation!
* **SLIDE\_TO\_TOP**: slides the old text to top and back to normal with the new text.
* **SLIDE\_TO\_BOTTOM**: slides the old text to bottom and back to normal with the new text.
* **FADE**: fades out the old text and fades in the new text.





