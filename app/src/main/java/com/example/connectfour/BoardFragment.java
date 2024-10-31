package com.example.connectfour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

        // Iterate through all buttons in the GridLayout and set an OnClickListener
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            View child = mGrid.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(this::onButtonClick);
            }
        }

        return view;
    }

    // Step 5: Define onButtonClick as a stub
    private void onButtonClick(View view) {
        // This is a stub. No implementation required as per the instructions.
    }

    // Step 6: Override onSaveInstanceState to save the game state
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState()); // Save the game state as a string
    }
}