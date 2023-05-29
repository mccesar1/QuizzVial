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
    private int tipo;

    // Lista de índices de preguntas para mostrar en un orden aleatorio
    private List<Integer> listaIndicesPreguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
                        {"¿Cuánto alcohol se debe tener para considerar que se maneja en estado de ebriedad?",
                                "0.05 gramos por litro de sangre",
                                "0.08 gramos por litro de sangre",
                                "0.10 gramos por litro de sangre",
                                "0.08 gramos por litro de sangre",
                                "Respuesta correcta: 0.08 gramos por litro de sangre"},

                        {"¿Qué edad debe tener un niño para viajar en el asiento delantero?",
                                "8 años",
                                "10 años",
                                "12 años",
                                "12 años",
                                "Respuesta correcta: 12 años"},

                        {"¿Cuántos pasajeros pueden viajar dentro de un vehículo?",
                                "Solo el conductor y un acompañante",
                                "Hasta la capacidad máxima del vehículo",
                                "Hasta tres pasajeros, además del conductor",
                                "Hasta la capacidad máxima del vehículo",
                                "Respuesta correcta: Hasta la capacidad máxima del vehículo"},

                        {"Cuando conduce un menor de edad con permiso provisional, ¿en qué momento debe ir acompañado de un adulto?",
                                "Siempre debe ir acompañado de un adulto",
                                "Solo en horas pico de tráfico",
                                "No es necesario que vaya acompañado de un adulto",
                                "Siempre debe ir acompañado de un adulto",
                                "Respuesta correcta: Siempre debe ir acompañado de un adulto"},

                        {"¿Puedes usar el teléfono celular mientras conduces?",
                                "No, está prohibido en todas las circunstancias",
                                "Sí, pero solo si usas un dispositivo manos libres",
                                "Sí, siempre y cuando no esté en tus manos mientras conduces",
                                "No, está prohibido en todas las circunstancias",
                                "Respuesta correcta: No, está prohibido en todas las circunstancias"},


                        {"¿Quién tiene prioridad en las calles?",
                                "Los peatones",
                                "Los vehículos de emergencia",
                                "Los vehículos que ya están en la intersección",
                                "Los peatones",
                                "Respuesta correcta: Los peatones"},

                        {"¿Quiénes están obligados a usar el cinturón de seguridad?",
                                "Todos los ocupantes del vehículo",
                                "Solo el conductor",
                                "Solo los pasajeros en los asientos delanteros",
                                "Todos los ocupantes del vehículo",
                                "Respuesta correcta: Todos los ocupantes del vehículo"},

                        {"¿Cuál es el límite de velocidad en vías de acceso controlado y sus laterales?",
                                "100 km/h",
                                "120 km/h",
                                "80 km/h",
                                "120 km/h",
                                "Respuesta correcta: 120 km/h"},

                        {"¿Hasta cuántos metros está permitido circular en reversa?",
                                "25 metros",
                                "50 metros",
                                "100 metros",
                                "50 metros",
                                "Respuesta correcta: 50 metros"},

                        {"¿Cuál es el límite de velocidad en puntos de reunión como escuelas u hospitales?",
                                "30 km/h",
                                "40 km/h",
                                "50 km/h",
                                "30 km/h",
                                "Respuesta correcta: 30 km/h"},

                        {"¿Bajo qué circunstancias está permitida la vuelta a la derecha?",
                                "Cuando el semáforo está en verde y no hay peatones cruzando",
                                "Solo en intersecciones con señalización específica permitiendo la vuelta a la derecha",
                                "Siempre está permitida, incluso sin semáforo o señalización",
                                "Solo en intersecciones con señalización específica permitiendo la vuelta a la derecha",
                                "Respuesta correcta: Solo en intersecciones con señalización específica permitiendo la vuelta a la derecha"},

                        {"¿En qué lugares está prohibido el estacionamiento de cualquier vehículo?",
                                "En las aceras y zonas peatonales",
                                "En las áreas de carga y descarga",
                                "En las zonas de estacionamiento regulado con parquímetros",
                                "En las aceras y zonas peatonales",
                                "Respuesta correcta: En las aceras y zonas peatonales"},

                        {"En las glorietas sin semáforos, ¿quién tiene preferencia?",
                                "Los vehículos que ya están dentro de la glorieta",
                                "Los vehículos que se aproximan a la glorieta",
                                "Los peatones que cruzan cerca de la glorieta",
                                "Los vehículos que ya están dentro de la glorieta",
                                "Respuesta correcta: Los vehículos que ya están dentro de la glorieta"},

                        {"¿Qué tipo de vehículos pueden circular a contraflujo?",
                                "Los vehículos de emergencia",
                                "Solo las bicicletas y motocicletas",
                                "Todos los vehículos están permitidos a circular a contraflujo",
                                "Los vehículos de emergencia",
                                "Respuesta correcta: Solo los vehículos de emergencia tienen permitido circular a contraflujo"},

                        {"¿En qué momento tienen preferencia los vehículos de emergencia?",
                                "Siempre tienen preferencia, incluso sobre los semáforos",
                                "Solo cuando las luces y sirenas están encendidas",
                                "Solo cuando se dirigen a una emergencia médica",
                                "Siempre tienen preferencia, incluso sobre los semáforos",
                                "Respuesta correcta: Siempre tienen preferencia, incluso sobre los semáforos"},

                        {"¿En un camino con tres o más carriles para la misma dirección, el carril de la izquierda está destinado para vehículos más rápidos, lentos o de emergencia?",
                                "Vehículos más rápidos",
                                "Vehículos más lentos",
                                "Vehículos de emergencia",
                                "Vehículos más rápidos",
                                "Respuesta correcta: Vehículos más rápidos"},

                        {"¿Cuál es la sanción por rebasar el límite de velocidad?",
                                "Multa y puntos de penalización en la licencia de conducir",
                                "Suspensión temporal de la licencia de conducir",
                                "Confiscación del vehículo",
                                "Multa y puntos de penalización en la licencia de conducir",
                                "Respuesta correcta: Multa y puntos de penalización en la licencia de conducir"},

                        {"¿Cuál es la distancia que debe haber entre un ciclista y un auto?",
                                "1 metro",
                                "1.5 metros",
                                "2 metros",
                                "1.5 metros",
                                "Respuesta correcta: 1.5 metros"},

                        {"¿Cómo se dividen las señales de tránsito?",
                                "Se dividen en señales de advertencia, señales de prohibición y señales de obligación",
                                "Se dividen en señales de prioridad, señales de orientación y señales de información",
                                "Se dividen en señales de dirección, señales de precaución y señales de atención",
                                "Se dividen en señales de advertencia, señales de prohibición y señales de obligación",
                                "Respuesta correcta: Se dividen en señales de advertencia, señales de prohibición y señales de obligación"},

                        {"¿Qué deben hacer los conductores cuando el semáforo está en ámbar fija?",
                                "Acelerar para cruzar rápidamente",
                                "Detenerse si pueden hacerlo de manera segura",
                                "Pasar con precaución si no hay vehículos cercanos",
                                "Detenerse si pueden hacerlo de manera segura",
                                "Respuesta correcta: Detenerse si pueden hacerlo de manera segura"},

                        {"¿Qué significan las líneas blancas discontinuas en el pavimento?",
                                "Se puede rebasar si es seguro hacerlo",
                                "No se permite rebasar bajo ninguna circunstancia",
                                "Indican una zona de estacionamiento permitido",
                                "Se puede rebasar si es seguro hacerlo",
                                "Respuesta correcta: Se puede rebasar si es seguro hacerlo"},

                        {"¿Qué significan dos líneas amarillas sólidas a lo largo del camino?",
                                "No se permite rebasar en ninguna circunstancia",
                                "Se permite rebasar si no hay vehículos en sentido contrario",
                                "Indican una zona de carga y descarga exclusiva",
                                "No se permite rebasar en ninguna circunstancia",
                                "Respuesta correcta: No se permite rebasar en ninguna circunstancia"},

                        {"¿En qué momento está permitido detenerte y tapar un cruce peatonal?",
                                "Nunca se permite detenerse en un cruce peatonal",
                                "Solo está permitido hacerlo en horas de menor tráfico",
                                "Solo está permitido detenerse si hay un semáforo en rojo",
                                "Nunca se permite detenerse en un cruce peatonal",
                                "Respuesta correcta: Nunca se permite detenerse en un cruce peatonal"},

                        { "¿Cuál es el significado de una señal de tránsito triangular con borde rojo?",
                                "Advertencia de peligro",
                                "Prohibición",
                                "Indicación de dirección",
                                "Advertencia de peligro",
                                "Respuesta correcta: Advertencia de peligro"},

                        {"¿Qué distancia debo de llevar del vehículo que circula delante de mí?",
                                "30 metros",
                                "20 metros",
                                "Usando la regla de los tres segundos",
                                "Usando la regla de los tres segundos",
                                "Respuesta correcta: Usando la regla de los tres segundos"},

                        {"En qué horario puede conducir un menor de edad?",
                                "De 6:00 am a 22:00 horas",
                                "De 6:00 am a 24:00 horas",
                                "De 6:00 am a 20:00 horas",
                                "De 6:00 am a 22:00 horas",
                                "Respuesta correcta: De 6:00 am a 22:00 horas"},

                };
                break;
            case 2:
                preguntas = new String[][]{
                        {"2Pregunta 1 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"2Pregunta 2 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"},
                        {"2Pregunta 3 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 1", "retroalimentacion"},
                        {"2Pregunta 4 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"2Pregunta 5 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"}
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

        preguntaTextView.setText(preguntas[indiceRealPregunta][0]);

        List<String> opciones = Arrays.asList(preguntas[indiceRealPregunta][1], preguntas[indiceRealPregunta][2], preguntas[indiceRealPregunta][3]);
        Collections.shuffle(opciones);
        opcion1Button.setText(opciones.get(0));
        opcion2Button.setText(opciones.get(1));
        opcion3Button.setText(opciones.get(2));
        retroalimentacion = preguntas[indiceRealPregunta][5];
        respuestaCorrecta = preguntas[indiceRealPregunta][4];

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
        progressBar.setMax(25);
        puntuacionActual = puntuacion;
        //progres bar
        progressBar.setProgress(preguntaActual + 1);
        preguntaActual++;
        if (preguntaActual < 25) {
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
        tipo=1;
        intent.putExtra("tipo", tipo);
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




