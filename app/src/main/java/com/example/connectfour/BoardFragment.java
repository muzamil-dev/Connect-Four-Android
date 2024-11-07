package com.example.connectfour;

import android.content.Context;
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

    private static final String GAME_STATE = "gameState";
    private ConnectFourGame mGame;
    private GridLayout mGrid;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        mGame = new ConnectFourGame();
        mGrid = view.findViewById(R.id.gridLayout);

        for (int i = 0; i < mGrid.getChildCount(); i++) {
            View child = mGrid.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(this::onButtonClick);
            }
        }

        if (savedInstanceState == null) {
            startGame();
        } else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setState(gameState);
            setDisc();
        }

        return view;
    }

    private void onButtonClick(View view) {
        int buttonIndex = mGrid.indexOfChild(view);
        int row = buttonIndex / ConnectFourGame.COL;
        int col = buttonIndex % ConnectFourGame.COL;

        mGame.selectDisc(row, col);
        setDisc();

        if (mGame.isGameOver()) {
            Toast.makeText(getActivity(), "Congratulations! You won!", Toast.LENGTH_SHORT).show();
            mGame.newGame();
            setDisc();
        }
    }

    private void startGame() {
        mGame.newGame();
        setDisc();
    }

    private void setDisc() {
        for (int buttonIndex = 0; buttonIndex < mGrid.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) mGrid.getChildAt(buttonIndex);
            int row = buttonIndex / ConnectFourGame.COL;
            int col = buttonIndex % ConnectFourGame.COL;

            Drawable emptyDisc = DrawableCompat.wrap(ContextCompat.getDrawable(getActivity(), R.drawable.circle_white));
            Drawable blueDisc = DrawableCompat.wrap(ContextCompat.getDrawable(getActivity(), R.drawable.circle_blue));
            Drawable redDisc = DrawableCompat.wrap(ContextCompat.getDrawable(getActivity(), R.drawable.circle_red));

            int disc = mGame.getDisc(row, col);
            if (disc == ConnectFourGame.BLUE) {
                gridButton.setBackground(blueDisc);
            } else if (disc == ConnectFourGame.RED) {
                gridButton.setBackground(redDisc);
            } else {
                gridButton.setBackground(emptyDisc);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }
}
