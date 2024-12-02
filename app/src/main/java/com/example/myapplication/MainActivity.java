package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editNombre;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombre = findViewById(R.id.text_nombre);
        btnContinuar = findViewById(R.id.btn_comenzar);

        // Acción del botón Continuar
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editNombre.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, Pregunta.class);
                intent.putExtra("nombre_usuario", nombre); // Pasar el nombre a la siguiente actividad
                startActivity(intent);
            }
        });
    }
}