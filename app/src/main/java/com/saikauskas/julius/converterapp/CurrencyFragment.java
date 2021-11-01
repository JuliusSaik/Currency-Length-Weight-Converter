package com.saikauskas.julius.converterapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrencyFragment extends Fragment {

    public static BreakIterator data;
    List<String> keysList;
    TextView toCurrencyBttn, baseCurrencyBttn;
    TextView tvCurrencyValue;
    SpinnerDialog toCurrencySpinner, baseCurrencySpinner;

    String toCurr, fromCurr;

    SharedPref sharedPref;


    public CurrencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency, container, false);

        toCurrencyBttn = view.findViewById(R.id.spinnerToCurrency);
        baseCurrencyBttn = view.findViewById(R.id.spinnerBaseCurrency);
        final EditText etBaseValue = view.findViewById(R.id.baseCurrency);
        final Button btnConvert = view.findViewById(R.id.bttnConvert);
        tvCurrencyValue = view.findViewById(R.id.tvCurrencyValue);

        ImageView regularCards = view.findViewById(R.id.ivCardsRegular);
        ImageView minimalCards = view.findViewById(R.id.ivCardsMinimal);
        ImageView mintCards = view.findViewById(R.id.ivCardsMint);
        ImageView lightCards = view.findViewById(R.id.ivCardsLight);

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


        try {
            //From the API loads the conversion types from a url and makes a request(gets all of the currencies)
            loadConversionTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }


        baseCurrencyBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseSpinnerSearchDialog();
            }
        });

        toCurrencyBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSpinnerSearchDialog();
            }
        });


        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etBaseValue.getText().toString().isEmpty() || etBaseValue.getText().toString().equals(".")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter a value first.", Toast.LENGTH_SHORT).show();
                }
                //Check if network is on and string not empty to carry on
                else if (!networkOn()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Convy requires internet connectivity to fetch the latest currency exchange rates. Please turn on Wi-Fi or Mobile data.", Toast.LENGTH_LONG).show();
                } else {
                    double baseCurrValue = Double.valueOf(etBaseValue.getText().toString());

                    try {
                        //calls the convertCurrency() method and takes in the parameters of the currrency to convert to, and the
                        //currency to convert from(2nd paramter)
                        convertCurrency(toCurr, fromCurr, baseCurrValue);
                    }
                    //will catch an error if the conversion doesn't work
                    catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        // Inflate the layout for this fragment
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void loadConversionTypes() throws IOException {

        String url = "https://api.frankfurter.app/latest?from=TRY";
        //String url2 = "https://api.frankfurter.app/latest?from=USD&currencies";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                //.url(url2)
                .cacheControl(new CacheControl.Builder().maxStale(365, TimeUnit.DAYS).noCache().build())
                .header("Content-Type", "application/json")
                .build();


        //passes in parameter request from the api(code above)
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //takes the failure message and displays it as a toast
                String mMessage = e.getMessage();
                Log.w("failure Response", mMessage);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String mMessage = response.body().string();


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject b = obj.getJSONObject("rates"); //b is a collection of rate data

                            Iterator keysToCopyIterator = b.keys();


                            //a new scalable ArrayList of keys is created, the keys are stored as strings
                            keysList = new ArrayList<>();


                            //while the key iterator keeps returning true for any tokens to be scanned it will keep on checking
                            //for the next keys possible until it returns false(all tokens have been scanned)
                            while (keysToCopyIterator.hasNext()) {

                                //finds and returns the complete key and saves it a string, which is later saved to an arraylist
                                String key = (String) keysToCopyIterator.next();

                                //adds the key to the ArrayList
                                keysList.add(key);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }


        });
    }

    public void baseSpinnerSearchDialog() {

        if (!networkOn()) {
            Toast.makeText(getActivity().getApplicationContext(), "Convy requires internet connectivity to fetch the latest currency exchange rates. Please turn on Wi-Fi or Mobile data.", Toast.LENGTH_LONG).show();
        } else if (networkOn()) {

            ArrayList<String> currencies = (ArrayList<String>) keysList;
            baseCurrencySpinner = new SpinnerDialog(getActivity(), currencies, "Select Currency");

            baseCurrencySpinner.showSpinerDialog();

            baseCurrencySpinner.bindOnSpinerListener(new OnSpinerItemClick() {
                @Override
                public void onClick(String baseItem, int pos) {
                    baseCurrencyBttn.setText(baseItem);
                    fromCurr = baseItem;
                }
            });
        }
    }

    public void toSpinnerSearchDialog() {

        if (!networkOn()) {
            Toast.makeText(getActivity().getApplicationContext(), "Convy requires internet connectivity to fetch the latest currency exchange rates. Please turn on Wi-Fi or Mobile data.", Toast.LENGTH_LONG).show();
        } else if (networkOn()) {

            ArrayList<String> currencies = new ArrayList<>(keysList);
            toCurrencySpinner = new SpinnerDialog(getActivity(), currencies, "Select Currency");

            toCurrencySpinner.showSpinerDialog();

            toCurrencySpinner.bindOnSpinerListener(new OnSpinerItemClick() {
                @Override
                public void onClick(String toItem, int pos) {
                    toCurrencyBttn.setText(toItem);
                    toCurr = toItem;
                }
            });

        }
    }

    private boolean networkOn() {

        boolean wifiOn = false;
        boolean mobileDataOn = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    wifiOn = true;

            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    mobileDataOn = true;
        }

        return mobileDataOn || wifiOn;
    }

    public void convertCurrency(final String toCurrency, final String baseCurrency, final double baseValue) throws IOException {

        String url = "https://api.frankfurter.app/latest?from=TRY";
        //String url2 = "https://api.frankfurter.app/latest?from=TRY";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                //.url(url2)
                .cacheControl(new CacheControl.Builder().maxStale(365, TimeUnit.DAYS).noCache().build())
                .header("Content-Type", "application/json")
                .build();


        //catches execption incase of failure or error
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String mMessage = e.getMessage();
                Log.w("failure Response", mMessage);
                Toast.makeText(getActivity().getApplicationContext(), mMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String mMessage = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject b = obj.getJSONObject("rates");

                            //keysList.toArray(getResources().getStringArray(R.array.currencies));


                            //set the same rates to the 2 spinners
                            String val = b.getString(toCurrency);
                            String fromCurrVal = b.getString(baseCurrency);

                            //Takes the base value of the inserted number and divides it by the selected currencies value
                            //and then times it by the value you want to convert to.
                            double output = baseValue / Double.valueOf(fromCurrVal) * Double.valueOf(val);

                            DecimalFormat roundedValue = new DecimalFormat("####0.00");


                            tvCurrencyValue.setText(roundedValue.format(output));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }


        });
    }

}
