package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class
MainActivity extends AppCompatActivity {

    public EditText n1;
    public EditText n2;
    public Spinner spinner;
    public TextView sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = findViewById(R.id.num1);
        n2 = findViewById(R.id.num2);
        spinner = findViewById(R.id.spinner);
        sum = findViewById(R.id.average);
        Button calculateButton = findViewById(R.id.calculate_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.FC_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    public void calculateResult() {
        String op1String = n1.getText().toString();
        String op2String = n2.getText().toString();

        if (op1String.isEmpty() || op2String.isEmpty()) {
            Toast.makeText(this, "Please Enter Both The Numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        int n1, n2;
        try {
            n1 = Integer.parseInt(op1String);
            n2 = Integer.parseInt(op2String);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid The Number Format", Toast.LENGTH_SHORT).show();
            return;
        }

        String Calculations_NUmber = spinner.getSelectedItem().toString();
        double Sum = 0;
        boolean NUmber = true;

        try {
            switch (Calculations_NUmber) {
                case "+":
                    Sum = n1 + n2;
                    break;
                case "-":
                    Sum =   n1 - n2;
                    break;
                case "*":
                    Sum = n1 * n2;
                    break;
                case "/":
                    if (n2 == 0) {
                        Toast.makeText(this, "Cannot The divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                   Sum = n1 / n2;
                    break;
                case "^":
                    Sum = (int) Math.pow(n1, n2);
                    break;
                default:
                    NUmber = false;
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error performing calculation", Toast.LENGTH_SHORT).show();
            return;
        }

        if (NUmber) {
            sum.setText("Average:"  + Sum);
        } else {
            Toast.makeText(this, "Invalid operation", Toast.LENGTH_SHORT).show();
        }
    }
}
