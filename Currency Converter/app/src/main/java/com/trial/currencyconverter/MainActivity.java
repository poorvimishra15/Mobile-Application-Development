package com.trial.currencyconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    EditText inputAmount;
    Spinner fromCurrency, toCurrency;
    TextView resultText;
    Button convertBtn, settingsBtn;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 🔥 STEP 5: Apply Theme Globally
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("darkMode", false);

        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputAmount = findViewById(R.id.inputAmount);
        fromCurrency = findViewById(R.id.fromCurrency);
        toCurrency = findViewById(R.id.toCurrency);
        resultText = findViewById(R.id.resultText);
        convertBtn = findViewById(R.id.convertBtn);
        settingsBtn = findViewById(R.id.settingsBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, currencies);

        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);

        convertBtn.setOnClickListener(v -> convertCurrency());

        settingsBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    private void convertCurrency() {
        String amountStr = inputAmount.getText().toString();

        if (amountStr.isEmpty()) {
            resultText.setText("Enter amount");
            return;
        }

        double amount = Double.parseDouble(amountStr);

        String from = fromCurrency.getSelectedItem().toString();
        String to = toCurrency.getSelectedItem().toString();

        double result = amount * getRate(from, to);

        resultText.setText("Result: " + result + " " + to);
    }

    private double getRate(String from, String to) {

        if (from.equals(to)) return 1;

        if (from.equals("INR") && to.equals("USD")) return 0.012;
        if (from.equals("USD") && to.equals("INR")) return 83;

        if (from.equals("INR") && to.equals("EUR")) return 0.011;
        if (from.equals("EUR") && to.equals("INR")) return 90;

        if (from.equals("INR") && to.equals("JPY")) return 1.8;
        if (from.equals("JPY") && to.equals("INR")) return 0.55;

        return 1;
    }
}