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


public class PreguntasActivity2 extends AppCompatActivity {

    private TextView preguntaTextView;
    private Button opcion1Button, opcion2Button, opcion3Button;
    private static final int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private ImageView gifImageView;
    private long tiempoRestante = TIEMPO_TOTAL;
    private static final long TIEMPO_TOTAL = 3000;
    private double puntuacionActual = 0;
    double puntuacion = 0;
    private String[][] preguntas;
    private int preguntaActual = 0;
    private TextView tiempoTextView;
    private Button siguienteButton;

    // Lista de índices de preguntas para mostrar en un orden aleatorio
    private List<Integer> listaIndicesPreguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //guardar cual fue la ultima actividad
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ultimaActividad", "preguntasActivity2");
        editor.apply();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.preguntas);

        establecerPantallaCompleta();
        inicializarVistas();

        // Ocultar la barra de navegación
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(3);
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
                        {"¿Cuánto alcohol se debe tener para considerar que se maneja en estado de ebriedad?", "0.05 gramos por litro de sangre", "0.08 gramos por litro de sangre", "0.10 gramos por litro de sangre", "Opción 2", "Respuesta correcta: 0.08 gramos por litro de sangre"},

                        {"¿Qué edad debe tener un niño para viajar en el asiento delantero?", "8 años", "10 años", "12 años", "Opción 3", "Respuesta correcta: 12 años"},

                        {"¿Cuántos pasajeros pueden viajar dentro de un vehículo?", "Solo el conductor y un acompañante", "Hasta la capacidad máxima del vehículo", "Hasta tres pasajeros, además del conductor", "Opción 2", "Respuesta correcta: Hasta la capacidad máxima del vehículo"},

                        {"Cuando conduce un menor de edad con permiso provisional, ¿en qué momento debe ir acompañado de un adulto?", "Siempre debe ir acompañado de un adulto", "Solo en horas pico de tráfico", "No es necesario que vaya acompañado de un adulto", "Opción 1", "Respuesta correcta: Siempre debe ir acompañado de un adulto"},

                        {"¿Puedes usar el teléfono celular mientras conduces?", "No, está prohibido en todas las circunstancias", "Sí, pero solo si usas un dispositivo manos libres", "Sí, siempre y cuando no esté en tus manos mientras conduces", "Opción 1", "Respuesta correcta: No, está prohibido en todas las circunstancias"},

                        {"¿De qué color se distingue a los lugares de estacionamiento destinados a discapacitados?", "Azul", "Rojo", "Verde", "Opción 1", "Respuesta correcta: Azul"},

                        {"¿Quién tiene prioridad en las calles?", "Los peatones", "Los vehículos de emergencia", "Los vehículos que ya están en la intersección", "Opción 3", "Respuesta correcta: Los vehículos que ya están en la intersección"},

                        {"¿Quiénes están obligados a usar el cinturón de seguridad?", "Todos los ocupantes del vehículo", "Solo el conductor", "Solo los pasajeros en los asientos delanteros", "Opción 1", "Respuesta correcta: Todos los ocupantes del vehículo"},

                        {"¿Cuál es el límite de velocidad en vías de acceso controlado y sus laterales?", "100 km/h", "120 km/h", "80 km/h", "Opción 2", "Respuesta correcta: 120 km/h"},

                        {"¿Hasta cuántos metros está permitido circular en reversa?", "25 metros", "50 metros", "100 metros", "Opción 2", "Respuesta correcta: 50 metros"},

                        {"¿Cuál es el límite de velocidad en puntos de reunión como escuelas u hospitales?", "30 km/h", "40 km/h", "50 km/h", "Opción 1", "Respuesta correcta: 30 km/h"},

                        {"¿Bajo qué circunstancias está permitida la vuelta a la derecha?", "Cuando el semáforo está en verde y no hay peatones cruzando", "Solo en intersecciones con señalización específica permitiendo la vuelta a la derecha", "Siempre está permitida, incluso sin semáforo o señalización", "Opción 2", "Respuesta correcta: Solo en intersecciones con señalización específica permitiendo la vuelta a la derecha"},

                        {"¿En qué lugares está prohibido el estacionamiento de cualquier vehículo?", "En las aceras y zonas peatonales", "En las áreas de carga y descarga", "En las zonas de estacionamiento regulado con parquímetros", "Opción 1", "Respuesta correcta: En las aceras y zonas peatonales"},

                        {"En las glorietas sin semáforos, ¿quién tiene preferencia?", "Los vehículos que ya están dentro de la glorieta", "Los vehículos que se aproximan a la glorieta", "Los peatones que cruzan cerca de la glorieta", "Opción 1", "Respuesta correcta: Los vehículos que ya están dentro de la glorieta"},

                        {"¿Qué tipo de vehículos pueden circular a contraflujo?", "Los vehículos de emergencia y transporte público", "Solo las bicicletas y motocicletas", "Todos los vehículos están permitidos a circular a contraflujo", "Opción 3", "Respuesta correcta: Todos los vehículos están permitidos a circular a contraflujo"},

                        {"¿En qué momento tienen preferencia los vehículos de emergencia?", "Siempre tienen preferencia, incluso sobre los semáforos", "Solo cuando las luces y sirenas están encendidas", "Solo cuando se dirigen a una emergencia médica", "Opción 1", "Respuesta correcta: Siempre tienen preferencia, incluso sobre los semáforos"},

                        {"¿En un camino con tres o más carriles para la misma dirección, el carril de la izquierda está destinado para vehículos más rápidos, lentos o de emergencia?", "Vehículos más rápidos", "Vehículos más lentos", "Vehículos de emergencia", "Opción 1", "Respuesta correcta: Vehículos más rápidos"},

                        {"¿Cuál es la sanción por rebasar el límite de velocidad?", "Multa y puntos de penalización en la licencia de conducir", "Suspensión temporal de la licencia de conducir", "Confiscación del vehículo", "Opción 1", "Respuesta correcta: Multa y puntos de penalización en la licencia de conducir"},

                        {"¿Cuál es la distancia que debe haber entre un ciclista y un auto?", "1 metro", "1.5 metros", "2 metros", "Opción 2", "Respuesta correcta: 1.5 metros"},

                        {"¿Cómo se dividen las señales de tránsito?", "Se dividen en señales de advertencia, señales de prohibición y señales de obligación", "Se dividen en señales de prioridad, señales de orientación y señales de información", "Se dividen en señales de dirección, señales de precaución y señales de atención", "Opción 1", "Respuesta correcta: Se dividen en señales de advertencia, señales de prohibición y señales de obligación"},

                        {"¿Qué deben hacer los conductores cuando el semáforo está en ámbar?", "Acelerar para cruzar rápidamente", "Detenerse si pueden hacerlo de manera segura", "Pasar con precaución si no hay vehículos cercanos", "Opción 3", "Respuesta correcta: Pasar con precaución si no hay vehículos cercanos"},

                        {"¿Qué significan las líneas blancas discontinuas en el pavimento?", "Se puede rebasar si es seguro hacerlo", "No se permite rebasar bajo ninguna circunstancia", "Indican una zona de estacionamiento permitido", "Opción 1", "Respuesta correcta: Se puede rebasar si es seguro hacerlo"},

                        {"¿Qué significan dos líneas amarillas sólidas a lo largo del camino?", "No se permite rebasar en ninguna circunstancia", "Se permite rebasar si no hay vehículos en sentido contrario", "Indican una zona de carga y descarga exclusiva", "Opción 1", "Respuesta correcta: No se permite rebasar en ninguna circunstancia"},

                        {"¿En qué momento está permitido detenerte y tapar un cruce peatonal?", "Nunca se permite detenerse en un cruce peatonal", "Solo está permitido hacerlo en horas de menor tráfico", "Solo está permitido detenerse si hay un semáforo en rojo", "Opción 1", "Respuesta correcta: Nunca se permite detenerse en un cruce peatonal"}
                };
                break;
            case 2:
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
            case 3:
                preguntas = new String[][]{
                        {"2Pregunta 1 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"2Pregunta 2 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"},
                        {"2Pregunta 3 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 1", "retroalimentacion"},
                        {"2Pregunta 4 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 2", "retroalimentacion"},
                        {"2Pregunta 5 del botón 3", "Opción 1", "Opción 2", "Opción 3", "Opción 3", "retroalimentacion"}
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

        // Agrega onClickListeners a los botones de opción para manejar la selección de respuesta
        opcion1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificarRespuesta(0);
            }
        });
        opcion2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificarRespuesta(1);
            }
        });
        opcion3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificarRespuesta(2);
            }
        });

    }
    private void verificarRespuesta(int opcionSeleccionada) {
        // Detener el cronómetro
        countDownTimer.cancel();

        // Verifica si la respuesta seleccionada es correcta
        String respuestaCorrecta = preguntas[preguntaActual][4];
        String retroalimentacion = preguntas[preguntaActual][5];

      if (opcionSeleccionada == 0 && opcion1Button.getText().equals(respuestaCorrecta)) {

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreguntasActivity2.this);
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

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreguntasActivity2.this);
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
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreguntasActivity2.this);
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
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreguntasActivity2.this);
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
        progressBar.setMax(5);
        puntuacionActual = puntuacion;
        //actualizar el valor maximo de la barra de progreso

        progressBar.setProgress(preguntaActual + 1);
        preguntaActual++;
        if (preguntaActual < 5) {
            mostrarPregunta(preguntaActual);
        } else {
            countDownTimer.cancel();
            finish();
            mostrarFinalActivity(null);
        }
    }
    public void mostrarFinalActivity (View view){
        countDownTimer.cancel();
        Intent intent = new Intent(PreguntasActivity2.this, FinalActivity.class);
        intent.putExtra("puntuacion", puntuacionActual);
        startActivity(intent);
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
    public void cronometro () {
        countDownTimer = new CountDownTimer(20000, 1000) {
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
                verificarRespuesta(-1); // indica que no se seleccionó ninguna respuesta
            }
        }.start();
    }
}

