package com.example.fantasygame.fragments;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fantasygame.R;
import com.example.fantasygame.adapters.MatchStateListAdapter;
import com.example.fantasygame.asynctask.MatchStateData;
import com.example.fantasygame.utils.HelperUtils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    private ListView mlv_match_state;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_matchstate, container, false);

        mlv_match_state = (ListView) root.findViewById(R.id.lv_match_state);
        mlv_match_state.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayMap<String, String> arrMatchState = (ArrayMap<String, String>) adapterView.getItemAtPosition(i);
                String matchId = arrMatchState.get("matchId");
                Bundle bundle = new Bundle();
                bundle.putString("matchId",matchId);
                Fragment someFragment = new MatchScoreFragment();
                someFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        new MatchStateData(getActivity(), HomeFragment.this, "live").execute();

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_live:
                new MatchStateData(getActivity(), HomeFragment.this, item.getTitle().toString().toLowerCase()).execute();
                return true;
            case R.id.action_recent:
                new MatchStateData(getActivity(), HomeFragment.this, item.getTitle().toString().toLowerCase()).execute();
                return true;
            case R.id.action_upcoming:
                new MatchStateData(getActivity(), HomeFragment.this, item.getTitle().toString().toLowerCase()).execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setMatchStateAdapter(final ArrayList<ArrayMap<String, String>> arrMatchState) {
        MatchStateListAdapter matchStateListAdapter = new MatchStateListAdapter(getActivity(), arrMatchState);
        mlv_match_state.setAdapter(matchStateListAdapter);
    }
}