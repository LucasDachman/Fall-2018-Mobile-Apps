package com.lucasdachman.lab6;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    int currentPage = 0;
    final int NUM_PAGES = 16;
    Page [] pages = new Page[NUM_PAGES];
    TextView titleTextView;
    TextView pTextView;
    ImageView imageView;
    Button prevButton;
    Button nextButton;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpPages();
        setContentView(R.layout.activity_main);

        titleTextView = findViewById(R.id.titleTextView);
        pTextView = findViewById(R.id.pTextView);
        imageView = findViewById(R.id.imageView);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(NUM_PAGES - 1);
        setPage(0);
        setButtons();
    }


    /** onClick Methods **/

    public void navPrev(View view) {
        setPage(--currentPage);
        setButtons();
    }

    public void navNext(View view) {
        setPage(++currentPage);
        setButtons();
    }

    /** Helper functions **/

    public void makeToast() {

    }

    public void setButtons() {
        seekBar.setProgress(currentPage);
        if (currentPage == 0) {
           prevButton.setEnabled(false);
        }
        else {
            prevButton.setEnabled(true);
        }

        if (currentPage == NUM_PAGES - 1) {
            nextButton.setEnabled(false);
        }
        else {
            nextButton.setEnabled(true);
        }
    }

    public void setPage(int index) {
        titleTextView.setText(pages[index].title);
        pTextView.setText(pages[index].description);
        int id = getResources().getIdentifier(pages[index].imageName, "drawable", getApplicationContext().getPackageName());
        imageView.setImageResource(id);

        if (index == NUM_PAGES - 1) {
            Toast toast = Toast.makeText(getApplicationContext(), "Last Page!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 250);
            toast.show();
        }
    }

    public void setUpPages() {
        pages = new Page[]{
            new Page("Texture", "Texture can add tactility and depth. It can also make a design feel more tangible.", "texture"),
            new Page("Time & Motion", "Motion can bring life to a design. Blurring, waving or motion lines can help create this effect.", "time_and_motion" ),
            new Page("Figure And Ground", "Negative space can create interesting and engaging designs.", "figure_and_ground" ),
            new Page("Framing", "Framing helps to highlight, crop or decorate elements. Boxes and outlines can call attention to individual elements of a design.", "framing" ),
            new Page("Grid", "The grid helps with aligning and arranging elements. Typically, a grid is implemented through an invisible system of rows and columns.", "grid" ),
            new Page("Hierarchy", "Hierarchy helps a user navigate a design. Attributes like size and position can easily contribute to hierarchy.", "hierarchy" ),
            new Page("Layers", "Layers help create multi-dimensional space. This can be achieved through stacking and opacity effects.", "layers" ),
            new Page("Modularity", "Modularity involves using shared constraints or characteristics across multiple designs or design elements.", "modularity" ),
            new Page("Pattern", "Patterns involve repetition and prediction. This can make a design fun and engaging.", "pattern" ),
            new Page("Point, Line & Plane", "These elements can create emphasis and movement by directing the eye and highlighting specific parts of a design.", "point_line_plane" ),
            new Page("Rhythm & Balance", "Rhythm and balance help to create space and hierarchy in a design. The key to achieving good balance is to consider the relationship between elements in a design.", "rhythm_and_balance" ),
            new Page("Rules & Randomness", "Rules make a design ordered and predictable. Randomness can make a design more organic. Used together, rules and randomness can create engaging contrast.", "rules_and_randomness" ),
            new Page("Scale", "Scale can draw attention to and from certain elements. This principle is essential to creating emphasis and hierarchy.", "scale" ),
            new Page("Transparency", "Transparency helps elements interact on a new Page. This can also be used to create a sense of movement. This is also known as opacity.", "transparency" ),
            new Page("Color", "A strong color palette will create a strong design. Color can create moods and atmospheres.", "color" ),
            new Page("Diagram", "Diagrams are a visual representation of data. They can simplify complex concepts.", "diagram" )
        };
    }

    /** Seek Bar implementations **/

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            currentPage = progress;
            setPage(currentPage);
            setButtons();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

