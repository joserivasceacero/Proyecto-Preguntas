package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.widget.ImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class Pregunta extends AppCompatActivity {

    private TextView textEnunciado, textNumPregunta;
    private Button btnOpcion1, btnOpcion2, btnOpcion3, btnContinuar;
    private List<Pregunto> preguntas;
    private int preguntaActual = 0;
    private int puntos = 0;
    private String nombreUsuario;
    private ImageView imagePregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);
        nombreUsuario = getIntent().getStringExtra("nombre_usuario");

        textNumPregunta = findViewById(R.id.text_numpregunta);
        textEnunciado = findViewById(R.id.text_enunciado);
        btnOpcion1 = findViewById(R.id.btn_opcion1);
        btnOpcion2 = findViewById(R.id.btn_opcion2);
        btnOpcion3 = findViewById(R.id.btn_opcion3);
        btnContinuar = findViewById(R.id.btn_continuar);
        imagePregunta = findViewById(R.id.imageView); // Inicializar el ImageView

        // Inicializar preguntas
        preguntas = crearPreguntas();

        // Mostrar la primera pregunta
        mostrarPregunta();

        // Configurar eventos de clic para las opciones
        View.OnClickListener opcionListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta((Button) v);
            }
        };

        btnOpcion1.setOnClickListener(opcionListener);
        btnOpcion2.setOnClickListener(opcionListener);
        btnOpcion3.setOnClickListener(opcionListener);

        // Configurar evento para el botón Continuar
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preguntaActual < preguntas.size() - 1) {
                    preguntaActual++;
                    mostrarPregunta();
                } else {
                    Intent intent = new Intent(Pregunta.this, Final.class);
                    intent.putExtra("nombre_usuario", nombreUsuario);
                    intent.putExtra("puntos", puntos);
                    intent.putExtra("total_preguntas", preguntas.size());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void mostrarPregunta() {
        resetearBotones();
        Pregunto pregunta = preguntas.get(preguntaActual);
        textEnunciado.setText(pregunta.getEnunciado());

        textNumPregunta.setText((preguntaActual + 1) + "/" + preguntas.size());

        String nombreImagen = "i" + (preguntaActual + 1); // Crear el nombre de la imagen (i1, i2, i3...)
        int imageResId = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
        imagePregunta.setImageResource(imageResId);

        List<String> opciones = new ArrayList<>(pregunta.getOpciones().keySet());

        Collections.shuffle(opciones);

        btnOpcion1.setText(opciones.get(0));
        btnOpcion2.setText(opciones.get(1));
        btnOpcion3.setText(opciones.get(2));

        btnContinuar.setVisibility(View.GONE);
        habilitarBotones(true);
    }

    private void verificarRespuesta(Button botonSeleccionado) {
        Pregunto pregunta = preguntas.get(preguntaActual);
        String respuesta = botonSeleccionado.getText().toString();
        boolean esCorrecta = pregunta.getOpciones().get(respuesta);

        if (esCorrecta) {
            botonSeleccionado.setBackgroundColor(ContextCompat.getColor(this, R.color.verde));
            puntos++;
        } else {
            botonSeleccionado.setBackgroundColor(ContextCompat.getColor(this, R.color.rojo));
        }

        // Deshabilitar botones y mostrar "Continuar"
        habilitarBotones(false);
        btnContinuar.setVisibility(View.VISIBLE);
    }

    private void habilitarBotones(boolean habilitar) {
        btnOpcion1.setEnabled(habilitar);
        btnOpcion2.setEnabled(habilitar);
        btnOpcion3.setEnabled(habilitar);
    }

    private void resetearBotones() {
        btnOpcion1.setBackgroundColor(ContextCompat.getColor(this, R.color.base));
        btnOpcion2.setBackgroundColor(ContextCompat.getColor(this, R.color.base));
        btnOpcion3.setBackgroundColor(ContextCompat.getColor(this, R.color.base));
    }

    private List<Pregunto> crearPreguntas() {
        List<Pregunto> listaPreguntas = new ArrayList<>();

        Pregunto p1 = new Pregunto("¿Qué función tiene el infinitivo de esta frase en latín?: \n Homines ab inmortalibus ignem petebant sed in perpetuum servare nesciebant \n");
        p1.agregarOpcion("complemento directo", true);
        p1.agregarOpcion("sujeto", false);
        p1.agregarOpcion("oración subordinada de infinitivo", false);

        Pregunto p2 = new Pregunto("¿Cual de los siguientes tiburones es mas pequeño?");
        p2.agregarOpcion("Tiburón toro", true);
        p2.agregarOpcion("Tiburón tigre ", false);
        p2.agregarOpcion("Tiburón ballena", false);

        Pregunto p3 = new Pregunto("¿En el Honkai Star Rail el mejor personaje de Break es:");
        p3.agregarOpcion("Firefly", true);
        p3.agregarOpcion("Himeko", false);
        p3.agregarOpcion("Boothill", false);

        Pregunto p4 = new Pregunto("En que canción aparece la frase \"cogollo violeta como freezer\"");
        p4.agregarOpcion("Ayer me llamó mi ex", false);
        p4.agregarOpcion("I wake Up i bake Up", true);
        p4.agregarOpcion("HOLA PERDIDA", false);

        Pregunto p5 = new Pregunto("¿Que personaje se sacrifico para conseguir unas manzanas en hora de aventuras?");
        p5.agregarOpcion("Giuseppe", true);
        p5.agregarOpcion("Tronquitos", false);
        p5.agregarOpcion("Tiffany", false);

        listaPreguntas.add(p1);
        listaPreguntas.add(p2);
        listaPreguntas.add(p3);
        listaPreguntas.add(p4);
        listaPreguntas.add(p5);

        return listaPreguntas;
    }
}