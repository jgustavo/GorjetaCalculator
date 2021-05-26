package com.example.gorjetacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GorjetaCalculator";
    TextView gorjeta;
    TextView valorGorjeta;
    EditText valorConta;
    TextView totalConta;
    SeekBar seekBar;

    float fValorConta;

    private static final NumberFormat currencyFormat =  NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =  NumberFormat.getPercentInstance();

    private void calcular(int progress) {
        // int iGorjeta =;

        try {
            fValorConta = Float.valueOf(valorConta.getText().toString());
        } catch (Exception e) {
            fValorConta = 0;
        }

        float percentual = (float) progress / 100;

        gorjeta.setText(percentFormat.format(percentual));

        Log.i(TAG, "PERCENTUAL " + percentual);

        float fValorGorjeta = percentual * fValorConta;
       // valorGorjeta.setText(Float.toString(fValorGorjeta));
        valorGorjeta.setText(currencyFormat.format(fValorGorjeta));
        float fTotalConta = fValorGorjeta + fValorConta;
     //   totalConta.setText(Float.toString(fTotalConta));
        totalConta.setText(currencyFormat.format(fTotalConta));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "I Like it!", Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        //    botaoLike.setOnClickListener(clickListener);

        gorjeta = findViewById(R.id.textView4);
        valorGorjeta = findViewById(R.id.valorGorjeta);
        valorConta = findViewById(R.id.valorConta);
        totalConta = findViewById(R.id.totalConta);
        seekBar = findViewById(R.id.seekBar);

        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                calcular(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };



        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);


        TextWatcher amountEditTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // calcular();
            }

            @Override
            public void afterTextChanged(Editable s) {

                calcular(seekBar.getProgress());

            }
        };

        valorConta.addTextChangedListener(amountEditTextWatcher);
    }
}