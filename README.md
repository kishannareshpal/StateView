# StateView
An alternate state control for views for Android. Can be used to show that
a RecyclerView or any other View has no data

## 🌶 Want to use it in your project? Here's how to install

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)<br>
![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)
![Download](https://api.bintray.com/packages/kishannareshpal/maven/stateview/images/download.svg?version=1.1)


Add the library to the **dependencies { ... }** section of your **app** level `build.gradle` file:

```groovy
// Check the badge above to replace the version :)
implementation 'com.kishannareshpal:stateview:<version>'
```

## 🐌 Start using it

### **XML**

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

**NOTE: You can only add one \(1\) child view to StateView.**  
If you try adding more than one child view, you will get an IllegalStateException.

![](.gitbook/assets/artboard%20%281%29.png)



<table>
  <thead>
    <tr>
      <th style="text-align:left"><b>XML Attributes</b>
      </th>
      <th style="text-align:left"><b>Description</b>
      </th>
      <th style="text-align:left"><b>Data Type</b>
      </th>
      <th style="text-align:left"><b>Possible Values</b>
      </th>
      <th style="text-align:left"><b>Default Value</b>
      </th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left">stateMode</td>
      <td style="text-align:left">
        <p>Sets the state mode.</p>
        <p><b>normal</b> mode, shows the child view normally.</p>
      </td>
      <td style="text-align:left">enum</td>
      <td style="text-align:left">
        <ul>
          <li><b>normal</b>
          </li>
          <li><b>alternate</b>
          </li>
        </ul>
      </td>
      <td style="text-align:left"><b>normal</b>
      </td>
    </tr>
    <tr>
      <td style="text-align:left">stateBackgroundColor</td>
      <td style="text-align:left">Sets the background color for the <b>alternate </b>mode.</td>
      <td style="text-align:left">color</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">
        <p>#FF000000</p>
        <p>(transparent)</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left">stateIcon</td>
      <td style="text-align:left">Sets a<b> 36x36dp </b>icon image.</td>
      <td style="text-align:left">
        <p>drawable</p>
        <p>reference</p>
      </td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">null</td>
    </tr>
    <tr>
      <td style="text-align:left">stateTitle</td>
      <td style="text-align:left">Sets the title.</td>
      <td style="text-align:left">string</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">null</td>
    </tr>
    <tr>
      <td style="text-align:left">stateTitleColor</td>
      <td style="text-align:left">Sets the title color.</td>
      <td style="text-align:left">color</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">
        <p>#000000</p>
        <p>(black)</p>
      </td>
    </tr>
    <tr>
      <td style="text-align:left">stateTitleTextSize</td>
      <td style="text-align:left">Sets the title text size.</td>
      <td style="text-align:left">dimension (sp)</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">24sp</td>
    </tr>
    <tr>
      <td style="text-align:left">stateTitleFontFilename</td>
      <td style="text-align:left">Custom font file name to be used on the title. (The font should be placed
        inside <b>assets/fonts/</b>)</td>
      <td style="text-align:left">string</td>
      <td style="text-align:left">n/a</td>
      <td style="text-align:left">null</td>
    </tr>
  </tbody>
</table>

```groovy
StateView sv = findViewById(R.id.stateview);
sv.title("Loading content");
sv.mainProgressEnabled(true);
sv.state(State.ALTERNATE);
```

