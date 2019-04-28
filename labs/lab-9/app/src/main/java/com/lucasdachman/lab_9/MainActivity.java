package com.lucasdachman.lab_9;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView dateTextView;
    private ImageView imageView;
    private ProgressBar progressBar;
    private final String url = "https://api.nasa.gov/planetary/apod?api_key=bAkFAhEuczMBGt6gs6JONbmfKH1INpdfsjTpghGi&count=1";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Astronomy Picture of the Day");

        titleTextView = findViewById(R.id.title_text);
        descriptionTextView = findViewById(R.id.description_text);
        imageView = findViewById(R.id.imageView);
        dateTextView = findViewById(R.id.date_text);
        progressBar = findViewById(R.id.progress_bar);
        requestQueue = Volley.newRequestQueue(this);

        resetView();
        requestAPOD(requestQueue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_apod_action) {
            resetView();
            requestAPOD(requestQueue);
        }
        return true;
    }

    private void requestAPOD(RequestQueue queue) {
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        APOD apod = parseResponse(response);
                        titleTextView.setText(apod.title);
                        descriptionTextView.setText(apod.description);
                        dateTextView.setText(apod.date);
                        requestApodImage(apod.imageUrl, requestQueue);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                descriptionTextView.setText("That didn't work!");
                System.out.println(error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }

    private void requestApodImage(final String url, RequestQueue queue) {
        progressBar.setVisibility(View.VISIBLE);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                System.out.println("Received APOD Image");
                progressBar.setVisibility(View.GONE);
                imageView.setImageBitmap(response);
            }
        },
        0,
        0,
        ImageView.ScaleType.CENTER_INSIDE,
        null,
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println("Error requesting APOD image: " + url);
                System.err.println(error);
            }
        });
        queue.add(imageRequest);
    }

    private APOD parseResponse(JSONArray response) {
        try {
            JSONObject object = response.getJSONObject(0);
            String date = object.getString("date");
            String description = object.getString("explanation");
            String title = object.getString("title");
            String imageUrl = object.getString("url");
            return new APOD(date, description, title, imageUrl);

        } catch (JSONException e) {
            System.err.println("Error parsing APOD response");
            System.err.println(e);
        }
        return null;
    }

    private void resetView() {
        titleTextView.setText("");
        descriptionTextView.setText("");
        dateTextView.setText("");
        imageView.setImageResource(android.R.color.transparent);
    }

    private class APOD {
        String date;
        String description;
        String title;
        String imageUrl;

        public APOD(String date, String description, String title, String imageUrl) {
            this.date = date;
            this.description = description;
            this.title = title;
            this.imageUrl = imageUrl;
        }
    }
}
