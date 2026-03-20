package com.dogi.lab4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerBack;
    private Spinner spinnerBtn;
    private Spinner spinnerColor;
    private ConstraintLayout activityMain;
    private Button button;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Сначала EdgeToEdge и ContentView
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 2. Инициализация View (строго ПОСЛЕ setContentView)
        activityMain = findViewById(R.id.main);
        button = findViewById(R.id.button3);
        spinnerBack = findViewById(R.id.spinner2);
        spinnerBtn = findViewById(R.id.spinner3);
        spinnerColor = findViewById(R.id.spinner);
        text = findViewById(R.id.textView);
        ViewCompat.setOnApplyWindowInsetsListener(activityMain, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 3. Настройка адаптеров
        setupSpinner(spinnerBack, R.array.style_list);
        setupSpinner2(spinnerBtn, R.array.btn_list);
        setupSpinner3(spinnerColor, R.array.text_list);

        // 4. Слушатель выбора для spinnerBack
        spinnerBack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateBackground(position);
                String selectedItem = parent.getItemAtPosition(position).toString();
                text.setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerBtn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateBtn(position);
                String selectedItem = parent.getItemAtPosition(position).toString();
                text.setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateColor(position);
                String selectedItem = parent.getItemAtPosition(position).toString();
                text.setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }


    private void setupSpinner2(Spinner spinner, int arrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void updateBtn(int position) {
        if (position == 0) {
            button.setBackgroundResource(R.drawable.btn2);
        } else if (position == 1) {
            button.setBackgroundResource(R.drawable.button);
        }
    }

    private void setupSpinner(Spinner spinner, int arrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void updateBackground(int position) {
        if (position == 0) {
            activityMain.setBackgroundResource(R.drawable.space);
        } else if (position == 1) {
            activityMain.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_back));
        }
        else if (position == 2){
            activityMain.setBackgroundColor(ContextCompat.getColor(this, R.color.red_back));
        }
    }

    private void setupSpinner3(Spinner spinner, int arrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void updateColor(int position) {
        if (position == 0) {
            text.setTextColor(ContextCompat.getColor(this, R.color.frame));
            button.setTextColor(ContextCompat.getColor(this, R.color.frame));
            text.setText("Синий");
        } else if (position == 1) {
            // Установка изображения (используйте setBackgroundResource для drawable)
            text.setTextColor(ContextCompat.getColor(this, R.color.Color2));
            button.setTextColor(ContextCompat.getColor(this, R.color.Color2));
            text.setText("Розовый");
        }
    }

    public void onClick(View view) {
        this.finish();
    }
}
