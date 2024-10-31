package com.example.connectfour;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GameOptionsFragment extends Fragment {

    private RadioGroup radioGroupDifficulty;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "GamePreferences";
    private static final String KEY_DIFFICULTY_LEVEL = "DifficultyLevel";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_game_options layout
        View view = inflater.inflate(R.layout.fragment_game_options, container, false);

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Reference to RadioGroup
        radioGroupDifficulty = view.findViewById(R.id.radioGroupDifficulty);

        // Load saved difficulty level and check corresponding radio button
        int savedDifficultyId = sharedPreferences.getInt(KEY_DIFFICULTY_LEVEL, R.id.radioButtonEasy);
        RadioButton savedRadioButton = view.findViewById(savedDifficultyId);
        if (savedRadioButton != null) {
            savedRadioButton.setChecked(true);
        }

        // Set a listener for radio group to handle selection changes
        radioGroupDifficulty.setOnCheckedChangeListener((group, checkedId) -> onLevelSelected(checkedId));

        return view; // Return the inflated view
    }

    // Method to handle radio button selection and save the level in SharedPreferences
    private void onLevelSelected(int checkedId) {
        // Save selected difficulty level in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_DIFFICULTY_LEVEL, checkedId);
        editor.apply();
    }
}
