package com.example.asistentevial.ui.Preguntas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.asistentevial.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PreguntasActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MisPreferencias";
    private static final String ULTIMA_ACTIVIDAD = "preguntasActivity";
    private static final int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
    private TextView preguntaTextView;
    private Button opcion1Button, opcion2Button, opcion3Button;
    private ImageView gifImageView;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private long tiempoRestante = TIEMPO_TOTAL;
    private static final long TIEMPO_TOTAL = 4000;
    private double puntuacionActual = 0;
    double puntuacion = 0;
    private String[][] preguntas;
    private int preguntaActual = 0;
    private TextView tiempoTextView;
    private Button siguienteButton;
    private   String retroalimentacion;
    private   String respuestaCorrecta;

    // Lista de índices de preguntas para mostrar en un orden aleatorio
    private List<Integer> listaIndicesPreguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        cronometro();

        //guardar cual fue la ultima actividad
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ultimaActividad", "preguntasActivity");
        editor.apply();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.preguntas);

        establecerPantallaCompleta();
        inicializarVistas();

        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(3, retroalimentacion, respuestaCorrecta);

            }
        });

        Glide.with(this).load(R.raw.timer).into(gifImageView);

        // Lee el identificador del botón presionado del Intent
        Intent intent = getIntent();
        int botonPresionado = intent.getIntExtra("BOTON_PRESIONADO", 0);

        // Elige las preguntas correspondientes según el botón presionado
        switch (botonPresionado) {
            case 1:
                preguntas = new String[][]{
                        {"¿A que velocidad debe circular al pasar por una zona escolar?",
                                "50 km/h",
                                "70km/h",
                                "30km/h",
                                "30km/h",
                                "retroalimentacion"},
                        {"¿En que se tiene que fijar a la hora de hacer la compra inteligente de un vehículo nuevo?",
                                "En el color",
                                "En los dispositivos de seguridad",
                                "En las bocinas",
                                "En los dispositivos de seguridad",
                                "retroalimentacion",},
                        {"¿Qué distancia debo de llevar del vehículo que circula delante de mí?",
                                "30 metros",
                                "20 metros",
                                "Usando la regla de los tres segundos",
                                "Usando la regla de los tres segundos",
                                "retroalimentacion"},
                        {"¿Qué es lo primero que tengo que hacer antes de dar marcha a un vehículo?",
                                "Ponerme el cinturón de seguridad",
                                "Prender el estéreo",
                                "Limpiar el vidrio delantero",
                                "Ponerme el cinturón de seguridad",
                                "retroalimentacion"},
                        {"¿Qué significa las líneas discontinuas?",
                                "Que puedes rebasar",
                                "Que no puedes rebasar",
                                "Qué debes hacer alto total",
                                "Que puedes rebasar",
                                "retroalimentacion"},
                        {"Los puntos ciegos son los espacios del entorno del propio del vehículo, ocultos a la vista del conductor por la propia configuración del mismo.",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"¿Qué hacer para disminuir los puntos ciegos?",
                                "Limpiar los parabrisas",
                                "Ajustar espejos",

                                "Ajustar el asiento del conductor",
                                "Ajustar espejos",
                                "retroalimentacion"},
                        {"El acotamiento es un estacionamiento momentáneo que se utiliza en caso de una falla mecánica del vehículo o en caso de una emergencia",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"¿Cuál es el distractor más frecuente en los accidentes de tránsito?",
                                "Maquillarse mientras conduce",
                                "Comer mientras conduce",
                                "Uso del celular mientras conduce",
                                "Uso del celular mientras conduce",
                                "retroalimentacion"},
                        {"¿Qué debes de hacer en caso de un accidente?",
                                "Revisar si hay personas lesionadas",
                                "Llamar al número de emergencia 911",
                                "Todas las anteriores",
                                "Todas las anteriores",
                                "retroalimentacion"},
                        {"¿Cuántos metros está permitido circular en reversa?",
                                "No más de 10 metros",
                                "No más de 6 metros",
                                "No más de 20 metros",
                                "No más de 10 metros",
                                "retroalimentacion"},
                        {"Basta con manipular el celular durante la conducción para que sea considerada  motivo de infracción",
                                "Falso",
                                "Verdadero",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"La  velocidad establecida es la que  se indica en  los señalamientos viales y la que establece la ley de vialidad y tránsito.",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"La velocidad adecuada es la que se ajusta dependiendo del estado físico del camino o por cuestiones climatológicas.",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"¿Por dónde debe de cruzar la calle un peatón?",
                                "Por los puentes peatonales",
                                "Pasos peatonales",
                                "Todas las anteriores",
                                "Todas las anteriores",
                                "retroalimentacion"},
                        {"¿Qué indica la luz verde del semáforo?",
                                "Siga",
                                "Alto",
                                "Disminuya su velocidad",
                                "Siga",
                                "retroalimentacion"},
                        {"¿Qué indica la luz amarilla del semáforo?",
                                "Prevención",
                                "Alto",
                                "Siga",
                                "Prevención",
                                "retroalimentacion"},
                        {"¿Qué indica el semáforo rojo?",
                                "Alto Total",
                                "Disminuya su velocidad",
                                "Siga",
                                "Alto Total",
                                "retroalimentacion"},
                        {"Las señales preventivas   previenen de los riesgos y peligros en el camino.",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"Cuales son los requisitos legales para que un vehículo pueda circular en la vía pública?",
                                "Tarjeta",
                                "Placa de circulación",
                                "Todas las anteriores",
                                "Todas las anteriores",
                                "retroalimentacion"},
                        {"Los niños no deben de viajar en el asiento delantero para evitar lesiones en caso de activación de las bolsas de aire.",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"¿Cuál es la edad mínima para poder viajar en el asiento delantero del vehículo?",
                                "3 años",
                                "6 años",
                                "12 años",
                                "12 años",
                                "retroalimentacion"},
                        {"La vuelta en U está permitida en los retornos",
                                "Falso",
                                "Verdadero",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"Que indica el pecho y espalda del policía vial?",
                                "Siga marcha",
                                "Alto total",
                                "Disminuya velocidad",
                                "Alto total",
                                "retroalimentacion"},
                        {"¿A qué distancia debe encender la luz direccional?",
                                "30 metros de anticipación",
                                "20 metros de anticipación",
                                "10 metros de anticipación",
                                "30 metros de anticipación",
                                "retroalimentacion"},
                        {"¿Qué debe hacer ante un cruce de ferrocarril?",
                                "Alto total",
                                "Acelerar para alcanzar a cruzar",
                                "Cruzar despacio",
                                "Alto total",
                                "retroalimentacion"},
                        {"Al escuchar una sirena de un vehículo de emergencia debe de darle el paso y orillarse a la derecha y hacer alto total",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"¿En qué caso es correcto rebasar una doble línea continua?",
                                "En una carretera con más de dos carriles",
                                "En una curva muy cerrada",
                                "En ningún caso",
                                "En ningún caso",
                                "retroalimentacion"},
                        {"Para indicar vuelta a la izquierda con una señal humana se utiliza el brazo izquierdo extendido",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"La línea de tensión o frenaje es donde el vehículo debe de detener  marcha ante una señal de alto o antes de cruzar el paso de peatones.",
                                "Falso",
                                "Verdadero",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"Mencione los 3 tipos de señales de tránsito",
                                "Preventivas",
                                "Restrictivas",
                                "Todas las anteriores",
                                "Todas las anteriores",
                                "retroalimentacion"},
                        {"En caso de una falla mecánica o ponchadura del vehículo debe de orillarse a lado derecho y abanderar con triángulos reflejante a una distancia de 30 a 50 metros atrás de donde está el vehículo descompuesto, más aun si se encuentra en una curva.",
                                "Falso",
                                "Verdadero",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"Las señales sonoras son todas aquellas que emiten sonidos",
                                "Falso",
                                "Verdadero",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"Cuando los neumáticos tienen poca presión de aire, ahorra combustible",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Falso",
                                "retroalimentacion"},
                        {"La diferencia entre una licencia de chofe particular y una de automovilista particular es  que la de automovilista le permite conducir vehículos hasta 3.5 toneladas",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"a qué edad pueden tramitar la licencia los menores de edad?",
                                "A partir de los 16 años cumplidos",
                                "A partir de los 15  años cumplidos",
                                "A partir de los 18 años cumplidos",
                                "A partir de los 16 años cumplidos",
                                "retroalimentacion"},
                        {"En qué horario puede conducir un menor de edad?",
                                "De 6:00 am a 22:00 horas",
                                "De 6:00 am a 24:00 horas",
                                "De 6:00 am a 20:00 horas",
                                "De 6:00 am a 22:00 horas",
                                "retroalimentacion"},
                        {"El cinturón de seguridad sirve para prevenir lesiones, evitar golpearse con las partes duras del vehículo y/o  salir disparados del vehículo en caso de un accidente?",
                                    "Verdadero",
                                    "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"Quiénes deben de utilizar el cinturón de seguridad?",
                                "Todos los ocupantes del vehículo",
                                "Solo los ocupantes de adelante",
                                "Solo los ocupantes de atrás",
                                "Todos los ocupantes del vehículo",
                                "retroalimentacion"},
                        {"Características fisicas de las señales restrictivas son círculo rojo, fondo blanco con sus caracteres negros ",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"La luz amarilla intermitente nos indica cruzar con precaución",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"¿Cuál es la velocidad máxima de circulación en el periférico de la juventud? ",
                                "100 km/h",
                                "70 km/h",
                                "80 km/h",
                                "70 km/h",
                                "retroalimentacion"},
                        {"El manejo defensivo es estar alerta y anticiparse a los errores de los demás",
                                "Verdadero",
                                "Falso",
                                "opcion3",
                                "Verdadero",
                                "retroalimentacion"},
                        {"Usted no puede detenerse ni estacionarse:",

                                "Pasos a nivel",
                                "Puentes",
                                "Todas las anteriores",
                                "Todas las anteriores",
                                "retroalimentacion"},

                };
                break;
            case 2:
                preguntas = new String[][]{
                        {"Pregunta 1 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"Pregunta 2 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"},
                        {"Pregunta 3 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 1", "retroalimentacion"},
                        {"Pregunta 4 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"Pregunta 5 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"},
                        {"Pregunta 6 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 1", "retroalimentacion"},
                        {"Pregunta 7 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"Pregunta 8 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"},
                        {"Pregunta 9 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 1", "retroalimentacion"},
                        {"Pregunta 10 del botón 2", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                };
                break;
            case 3:
                preguntas = new String[][]{
                        {"Pregunta 1 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"Pregunta 2 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"},
                        {"Pregunta 3 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 1", "retroalimentacion"},
                        {"Pregunta 4 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"Pregunta 5 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"},
                        {"Pregunta 6 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 1", "retroalimentacion"},
                        {"Pregunta 7 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"Pregunta 8 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"},
                        {"Pregunta 9 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 1", "retroalimentacion"},
                        {"Pregunta 10 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                };
                break;

    }
    // Inicializa la lista de índices de preguntas y la mezcla
    listaIndicesPreguntas = new ArrayList<>();
        for (int i = 0; i < preguntas.length; i++) {
        listaIndicesPreguntas.add(i);
    }
        Collections.shuffle(listaIndicesPreguntas);

    // Muestra la primera pregunta
    mostrarPregunta(preguntaActual);

}

    private void mostrarPregunta(int indicePregunta) {
        cronometro();

        // Obtiene el índice de la pregunta a mostrar
        int indiceRealPregunta = listaIndicesPreguntas.get(indicePregunta);

        // Muestra la pregunta y opciones correspondientes
//        preguntaTextView.setText(preguntas[indicePregunta][0]);
        preguntaTextView.setText(preguntas[indiceRealPregunta][0]);
//        List<String> opciones = Arrays.asList(preguntas[indicePregunta][1], preguntas[indicePregunta][2], preguntas[indicePregunta][3]);
        List<String> opciones = Arrays.asList(preguntas[indiceRealPregunta][1], preguntas[indiceRealPregunta][2], preguntas[indiceRealPregunta][3]);
        Collections.shuffle(opciones);
        opcion1Button.setText(opciones.get(0));
        opcion2Button.setText(opciones.get(1));
        opcion3Button.setText(opciones.get(2));
        retroalimentacion = preguntas[indiceRealPregunta][5];
        respuestaCorrecta = preguntas[indiceRealPregunta][4];
//        opcion4Button.setText(opciones.get(3));

        // Agrega onClickListeners a los botones de opción para manejar la selección de respuesta
        opcion1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(0, retroalimentacion, respuestaCorrecta);
            }
        });
        opcion2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(1, retroalimentacion, respuestaCorrecta);
            }
        });
        opcion3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(2, retroalimentacion, respuestaCorrecta);
            }
        });
    }

    private void verificarRespuesta(int opcionSeleccionada, String retroalimentacion, String respuestaCorrecta) {
        // Detener el cronómetro
        countDownTimer.cancel();

        if (opcionSeleccionada == 0 && opcion1Button.getText().equals(respuestaCorrecta)) {

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreguntasActivity.this);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet);
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //Establecer el listener de cancelación
            bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //Aquí es donde se ejecuta la función cuando se oculta el BottomSheetDialog
                    mostrarSiguientePregunta(null, puntuacion);
                }
            });
            bottomSheetDialog.show();
            //Obtener el TextView donde se mostrará el mensaje
            TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
            //Asignar el mensaje al TextView
            mensajeTextView.setText("Correcto\n" + retroalimentacion);
            puntuacion += 1;

            //set background color of bottom sheet
            LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
            bottomSheetLayout.setBackgroundResource(android.R.color.holo_green_light); // Establece el color de fondo a verde

        } else if (opcionSeleccionada == 1 && opcion2Button.getText().equals(respuestaCorrecta)) {

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreguntasActivity.this);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet);
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //Establecer el listener de cancelación
            bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //Aquí es donde se ejecuta la función cuando se oculta el BottomSheetDialog
                    mostrarSiguientePregunta(null, puntuacion);
                }
            });
            bottomSheetDialog.show();
            //Obtener el TextView donde se mostrará el mensaje
            TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
            //Asignar el mensaje al TextView
            mensajeTextView.setText("Correcto\n" + retroalimentacion);
            puntuacion += 1;

            LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
            bottomSheetLayout.setBackgroundResource(android.R.color.holo_green_light); // Establece el color de fondo a verde

        } else if (opcionSeleccionada == 2 && opcion3Button.getText().equals(respuestaCorrecta)) {

            //mostrar el bottom sheet y mostrar el mensaje de respuesta correcta
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreguntasActivity.this);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet);
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //Establecer el listener de cancelación
            bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //Aquí es donde se ejecuta la función cuando se oculta el BottomSheetDialog
                    mostrarSiguientePregunta(null, puntuacion);
                }
            });
            bottomSheetDialog.show();
            //Obtener el TextView donde se mostrará el mensaje
            TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
            //Asignar el mensaje al TextView
            mensajeTextView.setText("Correcto\n" + retroalimentacion);
            puntuacion += 1;

            LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
            bottomSheetLayout.setBackgroundResource(android.R.color.holo_green_light); // Establece el color de fondo a verde

        } else {
            //muestra el bottom sheet
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreguntasActivity.this);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet);
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //Establecer el listener de cancelación

            bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //Aquí es donde se ejecuta la función cuando se oculta el BottomSheetDialog
                    mostrarSiguientePregunta(null, puntuacion);
                }
            });
            bottomSheetDialog.show();

            //Obtener el TextView donde se mostrará el mensaje
            TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
            //Asignar el mensaje al TextView
            mensajeTextView.setText("Incorrecto\n" + retroalimentacion);
            LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
            bottomSheetLayout.setBackgroundResource(android.R.color.holo_red_light); // Establece el color de fondo a verde
        }
    }
    public void mostrarSiguientePregunta (View view,double puntuacion){

        puntuacionActual = puntuacion;

        //progres bar
        progressBar.setProgress(preguntaActual + 1);
        preguntaActual++;
        if (preguntaActual < preguntas.length) {
            mostrarPregunta(preguntaActual);
        } else {
            countDownTimer.cancel();
            finish();
            mostrarFinalActivity(null);
        }
    }

    public void mostrarFinalActivity (View view){
        countDownTimer.cancel();
        Intent intent = new Intent(PreguntasActivity.this, FinalActivity.class);
        intent.putExtra("puntuacion", puntuacionActual);
        startActivity(intent);
    }

    public void cronometro () {
        countDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                tiempoTextView.setText("Tiempo restante: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                tiempoTextView.setText("Tiempo restante: 0");
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator.hasVibrator()) {
                    // Crear un efecto de vibración
                    VibrationEffect vibrationEffect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);

                    // Vibra el teléfono con el efecto creado
                    vibrator.vibrate(vibrationEffect);
                }
                verificarRespuesta(-1, retroalimentacion, respuestaCorrecta); // indica que no se seleccionó ninguna respuesta
            }
        }.start();
    }

    private void inicializarVistas() {
        preguntaTextView = findViewById(R.id.preguntaTextView);
        opcion1Button = findViewById(R.id.opcion1Button);
        opcion2Button = findViewById(R.id.opcion2Button);
        opcion3Button = findViewById(R.id.opcion3Button);
        progressBar = findViewById(R.id.progressBar);
        tiempoTextView = findViewById(R.id.tiempoTextView);
        gifImageView = findViewById(R.id.gifImageView);
        siguienteButton = findViewById(R.id.siguienteButton);
    }

    private void establecerPantallaCompleta() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(UI_OPTIONS);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();

        finish(); // Destruye la actividad actual (ActivityB) y regresa a la anterior (ActivityA)
    }

    public void salir (View view){
        countDownTimer.cancel();
        finish();
    }

}




