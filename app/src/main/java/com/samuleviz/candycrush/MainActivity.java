package com.samuleviz.candycrush;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int[] candies = {
            R.drawable.blu,
            R.drawable.rossa,
            R.drawable.gialla,
            R.drawable.verde,
            R.drawable.liquirizia,
            R.drawable.cioccolata,
            R.drawable.viola,
            R.drawable.arancia
    };
    int widthOfBlock, noOfBlocks = 8, widthOfScreen;
    ArrayList<ImageView> candyImages = new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        widthOfBlock = widthOfScreen / noOfBlocks;
        createBoard();
        for (ImageView imageView : candyImages) {
            imageView.setOnTouchListener(new OnSwipeListener(this) {
                @Override
                void SwipeLeft() {
                    super.SwipeLeft();
                    Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
                }

                @Override
                void SwipeRight() {
                    super.SwipeRight();
                    Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                }

                @Override
                void SwipeUp() {
                    super.SwipeUp();
                    Toast.makeText(MainActivity.this, "Up", Toast.LENGTH_SHORT).show();
                }

                @Override
                void SwipeDown() {
                    super.SwipeDown();
                    Toast.makeText(MainActivity.this, "Down", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void createBoard() {
        GridLayout gridLayout = findViewById(R.id.board);
        gridLayout.setRowCount(noOfBlocks);
        gridLayout.setColumnCount(noOfBlocks);
        gridLayout.getLayoutParams().width = widthOfScreen;
        gridLayout.getLayoutParams().height = widthOfScreen;
        for (int i = 0; i < noOfBlocks * noOfBlocks; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(widthOfBlock, widthOfBlock));
            imageView.setMaxHeight(widthOfBlock);
            imageView.setMaxWidth(widthOfBlock);
            int randomCandyIndex = (int) Math.floor(Math.random() * candies.length);
            imageView.setImageResource(candies[randomCandyIndex]);
            candyImages.add(imageView);
            gridLayout.addView(imageView);
        }
    }
}
