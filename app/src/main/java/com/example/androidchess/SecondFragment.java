package com.example.androidchess;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidchess.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SecondFragment extends Fragment {


    private FragmentSecondBinding binding;
    ArrayList<MoveList> allGames;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = (ListView) view.findViewById(R.id.gamesList);

        GameList gameList = new GameList();
        gameList = getArguments().getParcelable("gamelist");
        allGames = gameList.getGames();
        //allGames = new ArrayList<>();

        final ListAdapter adapter = new ListAdapter(allGames, getContext());
        listView.setAdapter(adapter);

        binding.dateSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);

                 */
            }
        });

        binding.nameSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);

                 */
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}