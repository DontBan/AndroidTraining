package jp.mixi.practice.fragment.beg;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by tomohiro on 16/04/15.
 */
public class OmikujiFragment extends Fragment {

    public OmikujiFragment(){}

    TextView omikujiResultTextView;
    public enum OmikujiResult{
        大吉,
        吉,
        末吉,
        凶,
        大凶
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_omikuji, container);
        omikujiResultTextView = (TextView) v.findViewById(R.id.omikuji_result_textView);
        Button drawOmikujiButton = (Button) v.findViewById(R.id.draw_omikuji_Button);
        drawOmikujiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OmikujiResult result = drawOmikuji();
                omikujiResultTextView.setText(result.toString());
                if (result == OmikujiResult.大吉) {
                    Toast.makeText(getActivity(), "おめでとうございます！", Toast.LENGTH_SHORT).show();
                }
                if (result == OmikujiResult.吉) {
                    Toast.makeText(getActivity(), "まあまあですね", Toast.LENGTH_SHORT).show();
                }
                if (result == OmikujiResult.末吉) {
                    Toast.makeText(getActivity(), "まあ普通ですね", Toast.LENGTH_SHORT).show();
                }
                if (result == OmikujiResult.凶) {
                    Toast.makeText(getActivity(), "先が思いやられますね", Toast.LENGTH_SHORT).show();
                }
                if (result == OmikujiResult.大凶) {
                    Toast.makeText(getActivity(), "非常に残念です", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    private OmikujiResult drawOmikuji() {
        Random rnd = new Random();
        int ran = rnd.nextInt(10);
        OmikujiResult result = OmikujiResult.大凶;
        if (ran < 2) {
            result = OmikujiResult.大吉;
        } else if (ran >= 2 && ran < 4) {
            result = OmikujiResult.吉;
        } else if (ran >= 4 && ran < 6) {
            result = OmikujiResult.末吉;
        } else if (ran >= 6 && ran < 8) {
            result = OmikujiResult.凶;
        }
        return result;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
