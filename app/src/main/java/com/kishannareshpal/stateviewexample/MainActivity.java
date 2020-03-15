package com.kishannareshpal.stateviewexample;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kishannareshpal.stateview.ComponentGravity;
import com.kishannareshpal.stateview.State;
import com.kishannareshpal.stateview.StateView;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private StateView sv;
    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        sv         = findViewById(R.id.stateview);
        tv         = findViewById(R.id.tv);


        fetchRandomCatFact();
        btn.setEnabled(false);

        sv.backgroundColor(Color.WHITE)
                .descriptionColor(Color.GRAY)
                .gravity(ComponentGravity.LEFT);

        btn.setOnClickListener((view) -> {
            // fetch
            fetchRandomCatFact();
            btn.setEnabled(false);
       });
    }

    private void fetchRandomCatFact() {
        sv.state(State.ALTERNATE)
                .mainIcon(null, false)
                .title("Loading..")
                .titleColor(Color.BLACK)
                .description("Facts proudly provided by catfact.ninja")
                .smallProgressEnabled(true);
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://catfact.ninja/fact", null,
            response -> {
                // got response...
                try {
                    String fact = response.getString("fact");
                    tv.setText(fact);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                sv.state(State.NORMAL);
                btn.setEnabled(true);

            }, error -> {
                // got error...
                sv.title("oooops!")
                        .mainIcon(R.drawable.ic_badcat, false)
                        .smallProgressEnabled(false)
                        .description("something went wrong!\nPlease try again.")
                        .titleColor(Color.RED);
                btn.setEnabled(true);
            });

        queue.cancelAll("cancel_tag");
        queue.add(jsonObjectRequest);
    }
}

