package com.rit.basa_bari.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rit.basa_bari.R;
import com.rit.basa_bari.post_activity.AdsMessPostActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPostFragment extends Fragment {
    private  static final String TAG=AddPostFragment.class.getSimpleName();
    LinearLayout messLinearLayout;


    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_add_post, container, false );
        messLinearLayout=view.findViewById( R.id.messLinearLayout );

        messLinearLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getActivity(), AdsMessPostActivity.class ) );
            }
        } );

        Log.e(TAG,"value");
        return view;
    }

}
