package com.firstapp.pizzapartyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText numPeopleEditText;
    private RadioGroup hungerRadioGroup;
    private TextView totalPizzasTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up button click event handler
        Button calculateButton = findViewById(R.id.calculate_button);
        numPeopleEditText = findViewById(R.id.num_people_edittext);
        hungerRadioGroup = findViewById(R.id.hunger_radio_group);
        totalPizzasTextView = findViewById(R.id.total_pizzas_textview);

        calculateButton.setOnClickListener(v -> {
            int numPeople;
            int selectedId = hungerRadioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(getApplicationContext(), "Please select hunger level", Toast.LENGTH_SHORT).show();
                return;
            }

            String selectedHunger = ((RadioButton) findViewById(selectedId)).getText().toString();

            try {
                numPeople = Integer.parseInt(numPeopleEditText.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Please enter a valid number of people", Toast.LENGTH_SHORT).show();
                return;
            }

            int numPizzas;
            switch (selectedHunger) {
                case "Light":
                    numPizzas = numPeople / 3;
                    break;
                case "Medium":
                    numPizzas = numPeople / 2;
                    break;
                case "Ravenous":
                    numPizzas = numPeople;
                    break;
                default:
                    numPizzas = 0;
            }

            totalPizzasTextView.setText(getString(R.string.total_pizzas_text, numPizzas));
        });
    }
}
