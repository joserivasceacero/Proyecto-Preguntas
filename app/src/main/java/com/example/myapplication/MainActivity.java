package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editNombre;
    private Button btnComenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombre = findViewById(R.id.text_nombre);
        btnComenzar = findViewById(R.id.btn_comenzar);

        TextView firstPlace = findViewById(R.id.Text_primero);
        TextView secondPlace = findViewById(R.id.Text_segundo);
        TextView thirdPlace = findViewById(R.id.Text_tercero);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Obtener los tres primeros jugadores
        List<String> topJugadores = dbHelper.obtenerTop3();

        // Actualizo el leaderboard
        if (topJugadores.size() > 0) {
            firstPlace.setText("1. " + topJugadores.get(0));
        } else {
            firstPlace.setText("1. ------------");
        }

        if (topJugadores.size() > 1) {
            secondPlace.setText("2. " + topJugadores.get(1));
        } else {
            secondPlace.setText("2. ------------");
        }

        if (topJugadores.size() > 2) {
            thirdPlace.setText("3. " + topJugadores.get(2));
        } else {
            thirdPlace.setText("3. ------------");
        }



        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editNombre.getText().toString().trim().equals("")){ //Compruebo que no esta vacio o tiene solo espacios
                    Toast.makeText(MainActivity.this, "Tienes que introducir un nombre", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editNombre.getText().toString().length()>20){ //Compruebo que no esta vacio o tiene solo espacios
                    Toast.makeText(MainActivity.this, "Introduce un nombre mas corto", Toast.LENGTH_SHORT).show();
                    return;
                }
                String nombre = editNombre.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, Pregunta.class);
                intent.putExtra("nombre_usuario", nombre); // Uso put Extra para ir pasando las variables
                startActivity(intent);
            }
        });
    }
}