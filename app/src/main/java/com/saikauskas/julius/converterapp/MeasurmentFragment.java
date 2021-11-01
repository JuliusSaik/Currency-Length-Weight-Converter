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
public class MeasurmentFragment extends Fragment {


    Spinner spinnerBaseMeasur, spinnerToMeasur;
    EditText etBaseMeasur;
    TextView tvMeasur;
    //List<String> measurItems;
    //Spinner toMeasurSpinner, baseMeasurSpinner;

    SharedPref sharedPref;


    public MeasurmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_measurment, container, false);

        spinnerBaseMeasur = view.findViewById(R.id.spinnerBaseMeasur);
        spinnerToMeasur = view.findViewById(R.id.spinnerToMeasur);
        etBaseMeasur = view.findViewById(R.id.baseMeasur);
        tvMeasur = view.findViewById(R.id.tvMeasurValue);


        ImageView regularCards = view.findViewById(R.id.ivCardsRegularMeasur);
        ImageView minimalCards = view.findViewById(R.id.ivCardsMinimalMeasur);
        ImageView mintCards = view.findViewById(R.id.ivCardsMintMeasur);
        ImageView lightCards = view.findViewById(R.id.ivCardsLightMeasur);

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

        String[] measurItems = {"Kilometers", "Meters", "Centimeters", "Miles", "Yards", "Feet", "Inches", "Tonnes", "Kilograms", "Grams", "Stones", "Pounds", "Ounces"};


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.colored_spinner_layout, measurItems);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinnerBaseMeasur.setAdapter(spinnerArrayAdapter);
        spinnerToMeasur.setAdapter(spinnerArrayAdapter);

        Button bttnConvertMeasur = view.findViewById(R.id.bttnConvertMeasur);

        bttnConvertMeasur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etBaseMeasur.getText().toString().isEmpty() || etBaseMeasur.getText().toString().equals(".")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter a value first.", Toast.LENGTH_SHORT).show();
                } else {
                    Double baseMeasurValue = Double.valueOf(etBaseMeasur.getText().toString());
                    convertMeasur(baseMeasurValue);
                }
            }
        });

        return view;
    }

    private void convertMeasur(final double baseMeasurValue) {

        //Kilometres

        if (spinnerBaseMeasur.getSelectedItem() == "Kilometers" && spinnerToMeasur.getSelectedItem() == "Meters") {

            double output = baseMeasurValue * 1000;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Kilometers" && spinnerToMeasur.getSelectedItem() == "Centimeters") {

            double output = baseMeasurValue * 100000;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        }

        //To different measurments
        else if (spinnerBaseMeasur.getSelectedItem() == "Kilometers" && spinnerToMeasur.getSelectedItem() == "Miles") {

            double output = baseMeasurValue / 1.609;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Kilometers" && spinnerToMeasur.getSelectedItem() == "Yards") {

            double output = baseMeasurValue * 1094;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Kilometers" && spinnerToMeasur.getSelectedItem() == "Feet") {

            double output = baseMeasurValue * 3280.84;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Kilometers" && spinnerToMeasur.getSelectedItem() == "Inches") {

            double output = baseMeasurValue * 39370;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        }


        //Metres

        else if (spinnerBaseMeasur.getSelectedItem() == "Meters" && spinnerToMeasur.getSelectedItem() == "Kilometers") {

            double output = baseMeasurValue / 1000;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Meters" && spinnerToMeasur.getSelectedItem() == "Centimeters") {

            double output = baseMeasurValue * 100;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        }

        //diferent measurments

        else if (spinnerBaseMeasur.getSelectedItem() == "Meters" && spinnerToMeasur.getSelectedItem() == "Miles") {

            double output = baseMeasurValue / 1609;
            DecimalFormat roundedValue = new DecimalFormat("####0.0000000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Meters" && spinnerToMeasur.getSelectedItem() == "Yards") {

            double output = baseMeasurValue * 1.094;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Meters" && spinnerToMeasur.getSelectedItem() == "Feet") {

            double output = baseMeasurValue * 3.281;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Meters" && spinnerToMeasur.getSelectedItem() == "Inches") {

            double output = baseMeasurValue * 39.37;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        }


        //Centimetres
        else if (spinnerBaseMeasur.getSelectedItem() == "Centimeters" && spinnerToMeasur.getSelectedItem() == "Kilometers") {

            double output = baseMeasurValue / 100000;
            DecimalFormat roundedValue = new DecimalFormat("####0.00000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Centimeters" && spinnerToMeasur.getSelectedItem() == "Meters") {

            double output = baseMeasurValue / 100;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        }

        //Different measurments

        else if (spinnerBaseMeasur.getSelectedItem() == "Centimeters" && spinnerToMeasur.getSelectedItem() == "Miles") {

            double output = baseMeasurValue / 160934;
            DecimalFormat roundedValue = new DecimalFormat("####0.00000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Centimeters" && spinnerToMeasur.getSelectedItem() == "Yards") {

            double output = baseMeasurValue / 91.44;
            DecimalFormat roundedValue = new DecimalFormat("####0.00000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Centimeters" && spinnerToMeasur.getSelectedItem() == "Feet") {

            double output = baseMeasurValue / 30.48;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Centimeters" && spinnerToMeasur.getSelectedItem() == "Inches") {

            double output = baseMeasurValue / 2.54;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        }


        //Miles

        else if (spinnerBaseMeasur.getSelectedItem() == "Miles" && spinnerToMeasur.getSelectedItem() == "Yards") {

            double output = baseMeasurValue * 1760;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));
        } else if (spinnerBaseMeasur.getSelectedItem() == "Miles" && spinnerToMeasur.getSelectedItem() == "Feet") {

            double output = baseMeasurValue * 5280;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Miles" && spinnerToMeasur.getSelectedItem() == "Inches") {

            double output = baseMeasurValue * 63360;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Miles" && spinnerToMeasur.getSelectedItem() == "Kilometers") {

            double output = baseMeasurValue * 1.609;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Miles" && spinnerToMeasur.getSelectedItem() == "Meters") {

            double output = baseMeasurValue * 1609;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Miles" && spinnerToMeasur.getSelectedItem() == "Centimeters") {

            double output = baseMeasurValue * 160934;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));
        }


        // Yards

        else if (spinnerBaseMeasur.getSelectedItem() == "Yards" && spinnerToMeasur.getSelectedItem() == "Miles") {

            double output = baseMeasurValue / 1760;
            DecimalFormat roundedValue = new DecimalFormat("####0.00000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Yards" && spinnerToMeasur.getSelectedItem() == "Feet") {

            double output = baseMeasurValue * 3;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Yards" && spinnerToMeasur.getSelectedItem() == "Inches") {

            double output = baseMeasurValue * 36;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Yards" && spinnerToMeasur.getSelectedItem() == "Kilometers") {

            double output = baseMeasurValue / 1094;
            DecimalFormat roundedValue = new DecimalFormat("####0.0000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Yards" && spinnerToMeasur.getSelectedItem() == "Meters") {

            double output = baseMeasurValue / 1.094;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Yards" && spinnerToMeasur.getSelectedItem() == "Centimeters") {

            double output = baseMeasurValue * 91.44;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        }

        //Feet


        else if (spinnerBaseMeasur.getSelectedItem() == "Feet" && spinnerToMeasur.getSelectedItem() == "Miles") {

            double output = baseMeasurValue / 5280;
            DecimalFormat roundedValue = new DecimalFormat("####0.0000");


            tvMeasur.setText(roundedValue.format(output));


        } else if (spinnerBaseMeasur.getSelectedItem() == "Feet" && spinnerToMeasur.getSelectedItem() == "Yards") {

            double output = baseMeasurValue / 3;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Feet" && spinnerToMeasur.getSelectedItem() == "Inches") {

            double output = baseMeasurValue * 12;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Feet" && spinnerToMeasur.getSelectedItem() == "Kilometers") {

            double output = baseMeasurValue / 3281;
            DecimalFormat roundedValue = new DecimalFormat("####0.0000");


            tvMeasur.setText(roundedValue.format(output));


        } else if (spinnerBaseMeasur.getSelectedItem() == "Feet" && spinnerToMeasur.getSelectedItem() == "Meters") {

            double output = baseMeasurValue / 3.281;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Feet" && spinnerToMeasur.getSelectedItem() == "Centimeters") {

            double output = baseMeasurValue * 30.48;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        }


        //Inches


        else if (spinnerBaseMeasur.getSelectedItem() == "Inches" && spinnerToMeasur.getSelectedItem() == "Miles") {

            double output = baseMeasurValue / 63360;
            DecimalFormat roundedValue = new DecimalFormat("####0.0000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Inches" && spinnerToMeasur.getSelectedItem() == "Feet") {

            double output = baseMeasurValue / 12;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Inches" && spinnerToMeasur.getSelectedItem() == "Yards") {

            double output = baseMeasurValue / 36;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Inches" && spinnerToMeasur.getSelectedItem() == "Kilometers") {

            double output = baseMeasurValue / 39370;
            DecimalFormat roundedValue = new DecimalFormat("####0.00000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Inches" && spinnerToMeasur.getSelectedItem() == "Meters") {

            double output = baseMeasurValue / 39.37;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Inches" && spinnerToMeasur.getSelectedItem() == "Centimeters") {

            double output = baseMeasurValue * 2.54;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        }


        //Weight

        //Tonnes

        else if (spinnerBaseMeasur.getSelectedItem() == "Tonnes" && spinnerToMeasur.getSelectedItem() == "Kilograms") {

            double output = baseMeasurValue * 1000;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Tonnes" && spinnerToMeasur.getSelectedItem() == "Grams") {

            double output = baseMeasurValue * 1e+6;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Tonnes" && spinnerToMeasur.getSelectedItem() == "Stones") {

            double output = baseMeasurValue * 157;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Tonnes" && spinnerToMeasur.getSelectedItem() == "Pounds") {

            double output = baseMeasurValue * 2205;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Tonnes" && spinnerToMeasur.getSelectedItem() == "Ounces") {

            double output = baseMeasurValue * 35274;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        }


        //Kilograms

        else if (spinnerBaseMeasur.getSelectedItem() == "Kilograms" && spinnerToMeasur.getSelectedItem() == "Grams") {

            double output = baseMeasurValue * 1000;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Kilograms" && spinnerToMeasur.getSelectedItem() == "Tonnes") {

            double output = baseMeasurValue / 1000;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Kilograms" && spinnerToMeasur.getSelectedItem() == "Stones") {

            double output = baseMeasurValue / 6.35;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Kilograms" && spinnerToMeasur.getSelectedItem() == "Pounds") {

            double output = baseMeasurValue * 2.205;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Kilograms" && spinnerToMeasur.getSelectedItem() == "Ounces") {

            double output = baseMeasurValue * 35.274;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        }


        //Grams

        else if (spinnerBaseMeasur.getSelectedItem() == "Grams" && spinnerToMeasur.getSelectedItem() == "Kilograms") {

            double output = baseMeasurValue / 1000;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Grams" && spinnerToMeasur.getSelectedItem() == "Tonnes") {

            double output = baseMeasurValue / 1e+6;
            DecimalFormat roundedValue = new DecimalFormat("####0.00000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Grams" && spinnerToMeasur.getSelectedItem() == "Stones") {

            double output = baseMeasurValue / 6350;
            DecimalFormat roundedValue = new DecimalFormat("####0.00000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Grams" && spinnerToMeasur.getSelectedItem() == "Pounds") {

            double output = baseMeasurValue / 454;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Grams" && spinnerToMeasur.getSelectedItem() == "Ounces") {

            double output = baseMeasurValue / 28.35;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        }


        //diferent measurments

        else if (spinnerBaseMeasur.getSelectedItem() == "Stones" && spinnerToMeasur.getSelectedItem() == "Pounds") {

            double output = baseMeasurValue * 14;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Stones" && spinnerToMeasur.getSelectedItem() == "Ounces") {

            double output = baseMeasurValue * 224;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Stones" && spinnerToMeasur.getSelectedItem() == "Tonnes") {

            double output = baseMeasurValue / 157;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Stones" && spinnerToMeasur.getSelectedItem() == "Kilograms") {

            double output = baseMeasurValue * 6.35;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Stones" && spinnerToMeasur.getSelectedItem() == "Grams") {

            double output = baseMeasurValue * 6350;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        }


        //Pounds

        else if (spinnerBaseMeasur.getSelectedItem() == "Pounds" && spinnerToMeasur.getSelectedItem() == "Stones") {

            double output = baseMeasurValue / 14;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Pounds" && spinnerToMeasur.getSelectedItem() == "Ounces") {

            double output = baseMeasurValue * 16;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Pounds" && spinnerToMeasur.getSelectedItem() == "Tonnes") {

            double output = baseMeasurValue / 2205;
            DecimalFormat roundedValue = new DecimalFormat("####0.0000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Pounds" && spinnerToMeasur.getSelectedItem() == "Kilograms") {

            double output = baseMeasurValue / 2.205;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Pounds" && spinnerToMeasur.getSelectedItem() == "Grams") {

            double output = baseMeasurValue * 454;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        }


        //Ounces

        else if (spinnerBaseMeasur.getSelectedItem() == "Ounces" && spinnerToMeasur.getSelectedItem() == "Pounds") {

            double output = baseMeasurValue / 16;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Ounces" && spinnerToMeasur.getSelectedItem() == "Stones") {

            double output = baseMeasurValue / 224;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Ounces" && spinnerToMeasur.getSelectedItem() == "Tonnes") {

            double output = baseMeasurValue / 35274;
            DecimalFormat roundedValue = new DecimalFormat("####0.0000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Ounces" && spinnerToMeasur.getSelectedItem() == "Kilograms") {

            double output = baseMeasurValue / 35.274;
            DecimalFormat roundedValue = new DecimalFormat("####0.000");


            tvMeasur.setText(roundedValue.format(output));

        } else if (spinnerBaseMeasur.getSelectedItem() == "Ounces" && spinnerToMeasur.getSelectedItem() == "Grams") {

            double output = baseMeasurValue * 28.35;
            DecimalFormat roundedValue = new DecimalFormat("####0.00");


            tvMeasur.setText(roundedValue.format(output));

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Can't convert measurment.", Toast.LENGTH_SHORT).show();
        }


    }


}
