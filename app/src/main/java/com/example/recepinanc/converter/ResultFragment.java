package com.example.recepinanc.converter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Recepinanc on 01.09.2015.
 */
public class ResultFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private TextView resultTextView;

    private String inputText;
    private String[] words;
    private String letter;
    ArrayList<String> outputText;

    HashMap<String, String> binaries;
    private String tempWord;
    private String changedWord;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binaries = new HashMap<String, String>();
        binaries.put("01000001", "A");
        binaries.put("01000010", "B");
        binaries.put("01000011", "C");
        binaries.put("01000100", "D");
        binaries.put("01000101", "E");
        binaries.put("01000110", "F");
        binaries.put("01000111", "G");
        binaries.put("01001000", "H");
        binaries.put("01001001", "I");
        binaries.put("01001010", "J");
        binaries.put("01001011", "K");

        outputText = new ArrayList<>();
        words = new String[]{};
        tempWord = null;
        changedWord = null;

        if (getArguments() != null) {
            inputText = getArguments().getString(ARG_PARAM);
            if (inputText == null)
                inputText = "Null d�nd�.";
            Log.i("ResultFr", "inputText belirlendi");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.result_fragment_layout, container, false);

        resultTextView = (TextView) v.findViewById(R.id.result_texView);
        resultTextView.setText(stringGenerator(inputText));

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //Default Const.
    public ResultFragment() {

    }

    public static ResultFragment newInstance(String inputText) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, inputText);
        resultFragment.setArguments(args);
        return resultFragment;
    }

    /**
     * Convert String type binary input to ascii
     *
     * @param inputText data the user set in ConverterFragment
     */
    public String stringGenerator(String inputText) {

            words = inputText.split(" ");

        //More complicated but more faster,elimates the irrelavant words
        //that doesn't contain enough digit
        for (int i = 0; i < words.length; i++) {
            if (words[i].replaceAll("\\D", "").length() >= 8
                    && (words[i].contains("010") || words[i].contains("011"))) {

                int wordLength = words[i].length();
                boolean letterFound = false;


                while(wordLength >= 8) {
                    letterFound = false;
                    Outer:
                    for (String key : binaries.keySet()) {
                        boolean check = words[i].contains(key.toString());
                        tempWord = words[i];
                        if (check) {
                            changedWord = words[i].replace(key.toString(), binaries.get(key).toString());
                            wordLength -= 8;
                            letterFound = true;
                        }
                        if (letterFound) {
                            words[i] = changedWord;
                            break Outer;
                        } else {
                            words[i] = tempWord;
                        }
                    }
                }
                outputText.add(changedWord);
            } else {
                outputText.add(words[i]);
            }
            outputText.add(" ");
        }

        return printArrayList(outputText);
    }

    public boolean isNumeric(String inputText) {

        try {
            int number = Integer.parseInt(inputText);

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public String printArrayList(ArrayList<String> outputText) {
        String finalProduct;
        finalProduct = outputText.toString().replace("[","").replace("]","").replace(",","");
        return finalProduct;
    }

    public void detectConvertAdd (int wordLength,int i,boolean letterFound,String[] words,HashMap<String,String> binaries,String tempWord,String changedWord) {

    }
}
