package com.saikauskas.julius.converterapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class TemperatureFragment extends Fragment {

    Spinner spinnerBaseTemp, spinnerToTemp;
    EditText etBaseTemp;
    TextView tvToTemp;

    SharedPref sharedPref;

    public TemperatureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);

        spinnerBaseTemp = view.findViewById(R.id.spinnerBaseTemp);
        spinnerToTemp = view.findViewById(R.id.spinnerToTemp);
        etBaseTemp = view.findViewById(R.id.baseTemp);
        tvToTemp = view.findViewById(R.id.tvTempValue);


        ImageView regularCards = view.findViewById(R.id.ivCardsRegularTemp);
        ImageView minimalCards = view.findViewById(R.id.ivCardsMinimalTemp);
        ImageView mintCards = view.findViewById(R.id.ivCardsMintTemp);
        ImageView lightCards = view.findViewById(R.id.ivCardsLightTemp);

        sharedPref = new SharedPref(getActivity().getApplicationContext());

        if (sharedPref.loadLightTheme() == true) {
            lightCards.setVisibility(View.VISIBLE);
            minimalCards.setVisibility(View.GONE);
            mintCards.setVisibility(View.GONE);
            regularCards.setVisibility(View.GONE);

        } else if (sharedPref.loadRegularTheme() == true) {
            lightCards.setVisibility(View.GONE);
            minimalCards.setVisibility(View.GONE);
            mintCards.setVisibility(View.GONE);
            regularCards.setVisibility(View.VISIBLE);
        } else if (sharedPref.loadMinimalTheme() == true) {
            lightCards.setVisibility(View.GONE);
            minimalCards.setVisibility(View.VISIBLE);
            mintCards.setVisibility(View.GONE);
            regularCards.setVisibility(View.GONE);
        } else if (sharedPref.loadMintTheme() == true) {
            lightCards.setVisibility(View.GONE);
            minimalCards.setVisibility(View.GONE);
            mintCards.setVisibility(View.VISIBLE);
            regularCards.setVisibility(View.GONE);
        }


        String[] tempItems = {"Celsius", "Fahrenheit", "Kelvin"};

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.colored_spinner_layout, tempItems);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinnerBaseTemp.setAdapter(spinnerArrayAdapter);
        spinnerToTemp.setAdapter(spinnerArrayAdapter);


        Button bttnConvertTemp = view.findViewById(R.id.bttnConvertTemp);

        bttnConvertTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etBaseTemp.getText().toString().isEmpty() || etBaseTemp.getText().toString().equals(".") || etBaseTemp.getText().toString().equals("-")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter a value first.", Toast.LENGTH_SHORT).show();
                } else {
                    Double baseTempValue = Double.valueOf(etBaseTemp.getText().toString());
                    convertTemp(baseTempValue);
                }
            }
        });

        return view;
    }

    private void convertTemp(final double baseTempValue) {

        //Celsius conversion

        if (spinnerBaseTemp.getSelectedItem() == "Celsius" && spinnerToTemp.getSelectedItem() == "Fahrenheit") {

            double output = baseTempValue * 1.8 + 32;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvToTemp.setText(roundedValue.format(output));

        } else if (spinnerBaseTemp.getSelectedItem() == "Celsius" && spinnerToTemp.getSelectedItem() == "Kelvin") {

            double output = baseTempValue + 273.15;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvToTemp.setText(roundedValue.format(output));

        }


        //Fahrenheit conversion

        else if (spinnerBaseTemp.getSelectedItem() == "Fahrenheit" && spinnerToTemp.getSelectedItem() == "Celsius") {

            double output = (baseTempValue - 32) / 1.80;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvToTemp.setText(roundedValue.format(output));

        } else if (spinnerBaseTemp.getSelectedItem() == "Fahrenheit" && spinnerToTemp.getSelectedItem() == "Kelvin") {

            double output = (5d / 9d) * (baseTempValue - 32) + 273.15;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvToTemp.setText(roundedValue.format(output));

        }

        //Kelvin conversion

        else if (spinnerBaseTemp.getSelectedItem() == "Kelvin" && spinnerToTemp.getSelectedItem() == "Celsius") {

            double output = baseTempValue - 273.15;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvToTemp.setText(roundedValue.format(output));

        } else if (spinnerBaseTemp.getSelectedItem() == "Kelvin" && spinnerToTemp.getSelectedItem() == "Fahrenheit") {

            double output = (9d / 5d) * (baseTempValue - 273.15) + 32;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvToTemp.setText(roundedValue.format(output));

        }

    }


}
