package com.gmail.noraware29.myfragmentviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by KOBAYASHI Tomohiro on 16/05/24.
 */
public class SampleFragment extends Fragment{
    @SuppressWarnings("unused")
    private static final String TAG = SampleFragment.class.getSimpleName();

    public static SampleFragment newInstance(int position) {
        SampleFragment sampleFragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        sampleFragment.setArguments(bundle);

        return sampleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int position = 0;
        if (bundle != null) {
            position = bundle.getInt("position");
        }

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        TextView text = (TextView) view.findViewById(R.id.TextView1);
        text.setText(String.valueOf(position));

        return view;
    }
}
