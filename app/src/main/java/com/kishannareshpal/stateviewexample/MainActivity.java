package com.kishannareshpal.stateviewexample;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.kishannareshpal.stateview.ComponentGravity;
import com.kishannareshpal.stateview.State;
import com.kishannareshpal.stateview.StateView;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private StateView sv;
    private TextView tv;
    private MaterialButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        sv  = findViewById(R.id.stateview);
        tv  = findViewById(R.id.tv);

        // setup stateview
        sv.onActionButtonClick((stateView, actionButton) -> {
                fetchRandomCatFact();
            })
            .gravity(ComponentGravity.LEFT);





        // setup button
        btn.setEnabled(false);
        btn.setOnClickListener((view) -> {
            // fetch
            fetchRandomCatFact();
            btn.setEnabled(false);
        });


        // auto-fetch on start.
//        fetchRandomCatFact();
        sv.state(State.ALTERNATE);
    }


    /**
     *
     * Fetches a random fact from https://catfact.ninja
     * and displays it into a textview.
     *
     * While it fetches, the state view changes to {@link State#ALTERNATE} to show progress,
     * and when it completes it changes back to {@link State#NORMAL} which shows the text view with the cat fact,
     * or when fetching fails it
     */
    private void fetchRandomCatFact() {
        sv.smallProgressEnabled(true);
        sv.state(State.ALTERNATE)
            .mainIcon(null, false)
            .title("Loading..")
            .titleTextSize(56)
            .description("Facts proudly provided by catfact.ninja");
//            .actionButtonText(null);
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
                        .description("Something went wrong!\nPlease try again.")
                        .actionButtonText("Try again")
                        .titleTextColor(Color.RED);
                btn.setEnabled(true);
            });

        queue.cancelAll("cancel_tag");
        queue.add(jsonObjectRequest);
    }
}

