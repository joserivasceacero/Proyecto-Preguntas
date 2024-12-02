package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
public class Final extends AppCompatActivity {

    private TextView textResultado;
    private TextView textRecord;
    private DatabaseHelper dbHelper;
    private ImageView imageResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        textResultado = findViewById(R.id.text_resultado);
        textRecord = findViewById(R.id.text_record);
        imageResultado = findViewById(R.id.imageView2);

        // Inicializar base de datos
        dbHelper = new DatabaseHelper(this);

        // Obtener datos del Intent
        String nombreUsuario = getIntent().getStringExtra("nombre_usuario");
        int puntos = getIntent().getIntExtra("puntos", 0);
        int totalPreguntas = getIntent().getIntExtra("total_preguntas", 0);

        // Comprobar si es un nuevo récord personal
        boolean esNuevoRecord = dbHelper.recordPorJugador(nombreUsuario, puntos);

        if (esNuevoRecord) {
            dbHelper.insertarganador(nombreUsuario, puntos);
            textRecord.setText("¡Nuevo récord personal! Felicidades, " + nombreUsuario + ".");
        } else {
            int recordActual = dbHelper.getRecordPorJugador(nombreUsuario);
            textRecord.setText("Tu récord personal es: " + recordActual + " puntos, " + nombreUsuario + ".");
        }

        // Generar mensaje de resultado
        String mensaje;
        if (puntos == totalPreguntas) {
            mensaje = "¡Enhorabuena, " + nombreUsuario + "! Eres una leyenda como Giuseppe. Puntuacion: " + puntos + "/" + totalPreguntas;
            imageResultado.setImageResource(R.drawable.giuseppeenhorabuena);
        } else if (puntos >= 3) {
            mensaje = "Muy bien, " + nombreUsuario + ". Tu puntuacion es " + puntos + "/" + totalPreguntas + ".";
            imageResultado.setImageResource(R.drawable.gatotiburon);
        } else {
            mensaje = "Has sacado " + puntos + "/" + totalPreguntas + ". Que malo, tienes que seguir practicando, " + nombreUsuario + "!";
            imageResultado.setImageResource((R.drawable.hamster));
        }

        // Mostrar el mensaje en el TextView
        textResultado.setText(mensaje);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}