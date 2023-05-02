package com.example.androidchess;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidchess.databinding.FragmentWelcomeBinding;
import com.google.android.material.snackbar.Snackbar;

public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;
    public GameList gamelist = new GameList();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActivityResultLauncher<Intent> startForGame = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            MoveList pog = intent.getParcelableExtra("savedGame");
                            gamelist.addGames(pog);
                            System.out.println(gamelist.isEmpty());
                            System.out.println(gamelist.size());
                            System.out.println(gamelist.games.get(0).getDate());
                        }
                    }
                });

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("gamelist", gamelist);
                NavHostFragment.findNavController(WelcomeFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);

            }
        });

        binding.buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                Intent intent = new Intent(getActivity(), ChessGameActivity.class;
                intent.putExtra("gamelist", gamelist);
                startActivity(intent);
                 */
                startForGame.launch(new Intent(getActivity(), ChessGameActivity.class));
                    }
                }
        );


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}