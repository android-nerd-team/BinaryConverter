package com.example.recepinanc.converter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Recepinanc on 01.09.2015.
 */
public class ConvertFragment extends Fragment {

    private String input;

    private EditText binaryEditText;
    private TextView label;
    private Button convertButton;

    Communicator comm;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            comm = (Communicator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    "must implement onFragmentInteractionListener");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.convert_fragment_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binaryEditText = (EditText) view.findViewById(R.id.binary_input);
        label = (TextView) view.findViewById(R.id.binary_input_label_textView);
        convertButton = (Button) view.findViewById(R.id.convert_button);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = binaryEditText.getText().toString();
                comm.passData(input);
            }
        });

    }

    public interface Communicator {
        public void passData(String inputText);
    }
}
