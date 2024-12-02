package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

public class Final extends AppCompatActivity {

    private TextView textResultado;
    private TextView textRecord;
    private DatabaseHelper dbHelper;
    private ImageView imageResultado;
    private Button btnReiniciar, btnVolverEmpezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);



        textResultado = findViewById(R.id.text_resultado);
        textRecord = findViewById(R.id.text_record);
        imageResultado = findViewById(R.id.imageView2);
        btnReiniciar = findViewById(R.id.btn_reiniciar);
        btnVolverEmpezar = findViewById(R.id.btn_volverEmpezar);

        // Inicializar base de datos
        dbHelper = new DatabaseHelper(this);

        dbHelper.revisar();

        String nombreUsuario = getIntent().getStringExtra("nombre_usuario");
        int puntos = getIntent().getIntExtra("puntos", 0);
        int totalPreguntas = getIntent().getIntExtra("total_preguntas", 0);

        // Compruebo si es nuevo record
        boolean esNuevoRecord = dbHelper.recordPorJugador(nombreUsuario, puntos*(10/totalPreguntas));

        if (esNuevoRecord) {
            dbHelper.insertarganador(nombreUsuario, puntos*(10/totalPreguntas));
            textRecord.setText("¡Nuevo récord personal! Felicidades, " + nombreUsuario + ".");
        } else {
            int recordActual = dbHelper.getRecordPorJugador(nombreUsuario);
            textRecord.setText("Tu récord personal es: " + recordActual + " puntos, " + nombreUsuario + ".");
        }

        // Dependiendo de la nota el mensaje es diferente
        String mensaje;
        if (puntos == totalPreguntas) {
            mensaje = "¡Enhorabuena, " + nombreUsuario + "! Eres una leyenda como Giuseppe. Has sacado un " + puntos*(10/totalPreguntas);
            imageResultado.setImageResource(R.drawable.giuseppeenhorabuena);
        } else if (puntos >= 3) {
            mensaje = "Muy bien, " + nombreUsuario + ". Has sacado un  " + puntos*(10/totalPreguntas);
            imageResultado.setImageResource(R.drawable.gatotiburon);
        } else {
            mensaje = "Has sacado un " + puntos*(10/totalPreguntas) + ". Que malo, tienes que seguir practicando, " + nombreUsuario + "!";
            imageResultado.setImageResource((R.drawable.hamster));
        }

        textResultado.setText(mensaje);

        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.reiniciar();
                dbHelper.revisar();
                Toast.makeText(Final.this, "Leaderboard reiniciado correctamente", Toast.LENGTH_SHORT).show();
            }
        });
        btnVolverEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Final.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}