package com.jaysen.leagueoflegendmanual.ui.vod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaysen.leagueoflegendmanual.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VodFragment extends Fragment {


    public VodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vod, container, false);
    }

}