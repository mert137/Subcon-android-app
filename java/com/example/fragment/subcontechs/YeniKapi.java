package com.example.fragment.subcontechs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mert on 24.09.2017.
 */

public class YeniKapi extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.yenikapi, container, false);

        CommonMovieClass obj = new CommonMovieClass();

        TextView yenikapitext = view.findViewById(R.id.yenikapitext);
        yenikapitext.setText(R.string.yenikapi);

        return view;
    }
}
