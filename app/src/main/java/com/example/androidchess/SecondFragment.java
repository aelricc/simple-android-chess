package com.example.androidchess;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidchess.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
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
        if(!allGames.isEmpty()){
            for(int i = 0; i < allGames.size(); i++){
                allGames.get(i).setOrderAdded(i);
            }
        }

        final ListAdapter adapter = new ListAdapter(allGames, getContext());
        listView.setAdapter(adapter);

        binding.dateSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(allGames, MoveList.time);
                adapter.notifyDataSetChanged();
            }
        });

        binding.nameSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(allGames, MoveList.StuNameComparator);
                adapter.notifyDataSetChanged();
            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                MoveList game = allGames.get(position);
                System.out.println(game);
                Intent i = new Intent(getActivity(), ViewActivity.class);
                i.putExtra("game", game);
                startActivity(i);

            }
        });
    }

    @Override
    public void onDestroyView() {
        Collections.sort(allGames, MoveList.time);
        super.onDestroyView();
        binding = null;
    }

}