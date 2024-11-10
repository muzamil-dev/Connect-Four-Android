package com.example.connectfour;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

public class BoardFragment extends Fragment {

    // Step 3: Define the required members
    private static final String GAME_STATE = "gameState"; // Constant for game state key
    private ConnectFourGame mGame; // Game logic instance
    private GridLayout mGrid; // Reference to the GridLayout

    // Step 4: Replace onCreate with onCreateView
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_board layout
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        // Initialize mGame and mGrid
        mGame = new ConnectFourGame();
        mGrid = view.findViewById(R.id.gridLayout);

        // Step 1: Start a new game or set saved state
        if (savedInstanceState == null) {
            startGame();
        } else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setState(gameState);
            setDisc();
        }

        // Step 2: Set OnClickListener for each button in GridLayout
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            View child = mGrid.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(this::onButtonClick);
            }
        }

        return view;
    }

    // Step 5: Implement onButtonClick
    private void onButtonClick(View view) {
        // Find button's position (row and column)
        int buttonIndex = mGrid.indexOfChild(view);
        int row = buttonIndex / ConnectFourGame.COL;
        int col = buttonIndex % ConnectFourGame.COL;

        // Perform actions in ConnectFourGame based on button click
        mGame.selectDisc(row, col);
        setDisc();

        // Check if game is over
        if (mGame.isGameOver()) {
            Toast.makeText(getActivity(), "Congratulations! You won!", Toast.LENGTH_SHORT).show();
            mGame.newGame();
            setDisc();
        }
    }

    // Step 3: Write startGame method
    private void startGame() {
        mGame.newGame();
        setDisc();
    }

    // Step 4: Write setDisc method
    private void setDisc() {
        for (int buttonIndex = 0; buttonIndex < mGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mGrid.getChildAt(buttonIndex);

            // Find button's position (row and column)
            int row = buttonIndex / ConnectFourGame.COL;
            int col = buttonIndex % ConnectFourGame.COL;

            // Load drawable resources for the discs
            Drawable emptyDisc = DrawableCompat.wrap(ContextCompat.getDrawable(getActivity(), R.drawable.circle_white));
            Drawable blueDisc = DrawableCompat.wrap(ContextCompat.getDrawable(getActivity(), R.drawable.circle_blue));
            Drawable redDisc = DrawableCompat.wrap(ContextCompat.getDrawable(getActivity(), R.drawable.circle_red));

            // Set background based on disc color
            int discValue = mGame.getDisc(row, col);
            if (discValue == ConnectFourGame.BLUE) {
                gridButton.setBackground(blueDisc);
            } else if (discValue == ConnectFourGame.RED) {
                gridButton.setBackground(redDisc);
            } else {
                gridButton.setBackground(emptyDisc);
            }
        }
    }

    // Step 6: Override onSaveInstanceState to save the game state
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState()); // Save the game state as a string
    }
}
