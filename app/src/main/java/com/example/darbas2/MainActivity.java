package com.example.darbas2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtInputText;
    private Spinner spnCountType;
    private Button btnCount;
    private TextView txtResult;

    private TxtCounter txtCounter = new TxtCounter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInputText = findViewById(R.id.edtInputText);
        spnCountType = findViewById(R.id.spnCountType);
        btnCount = findViewById(R.id.btnCount);
        txtResult = findViewById(R.id.txtResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.count_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCountType.setAdapter(adapter);

        btnCount.setOnClickListener(view -> {
            String text = edtInputText.getText().toString();

            if (text.trim().isEmpty()) {
                Toast.makeText(this, R.string.msg_empty_input, Toast.LENGTH_SHORT).show();
                return;
            }

            String result;

            if (spnCountType.getSelectedItemPosition() == 0) {
                result = getString(R.string.result_words, txtCounter.countWords(text));
            } else {
                result = getString(R.string.result_characters, txtCounter.countCharacters(text));
            }

            txtResult.setText(result);
        });
    }
}