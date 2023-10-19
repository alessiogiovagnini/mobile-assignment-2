package com.example.stepappv2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.stepappv2.R;
import com.example.stepappv2.databinding.FragmentHomeBinding;
import com.google.android.material.progressindicator.CircularProgressIndicator;

// Android documentation said to implement OnClickListener, otherwise the buttons could not refer to the methods in the fragments
public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;
    private TextView stepCountsView;
    private CircularProgressIndicator progressBar;

    private int currentCount = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button startButton = (Button) root.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);

        Button increaseButton = (Button) root.findViewById(R.id.count_button);
        increaseButton.setOnClickListener(this);

        stepCountsView = (TextView) root.findViewById(R.id.counter);
        stepCountsView.setText("0");

        progressBar = (CircularProgressIndicator) root.findViewById(R.id.progressBar);
        progressBar.setMax(6000);
        progressBar.setProgress(0);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_button) {
            this.resetCounter(v);
        } else if (v.getId() == R.id.count_button) {
            this.increaseCount(v);
        }
    }

    public void resetCounter(View view) {
        currentCount = 0;
        stepCountsView.setText("0");
        progressBar.setProgress(0);
    }

    public void increaseCount(View view) {
        currentCount++;
        stepCountsView.setText(String.valueOf(currentCount));
        progressBar.setProgress(currentCount);
    }
}