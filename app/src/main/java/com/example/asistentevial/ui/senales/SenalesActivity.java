package com.example.asistentevial.ui.senales;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.asistentevial.R;

import java.util.ArrayList;
import java.util.List;


public class SenalesActivity extends AppCompatActivity {


    private Button btnVolver;
    private ImageView imageView;
    private TextView txtDescripcion;
    private Button btnSiguiente;
    private int currentIndex = 0; // Índice de la imagen actual
    private List<Imagen> imagenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.senales);

        // Establecer el modo de pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Ocultar la barra de navegación
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        imageView = findViewById(R.id.imageView2);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        txtDescripcion = findViewById(R.id.txtDescripcion);


        // Lee el identificador del botón presionado del Intent
        Intent intent = getIntent();
        int botonPresionado = intent.getIntExtra("BOTON_PRESIONADO", 0);


        switch(botonPresionado){

            case 1:

                // Agrega las imágenes con sus descripciones a la lista
                imagenList = new ArrayList<>();
                imagenList.add(new Imagen(R.drawable.senal1, "Curva\n" +
                        "Se utiliza para indicar curvas a la derecha o izquierda que sean de menos de 90 grados."));
                imagenList.add(new Imagen(R.drawable.senal2, "Curva cerrada\n" +
                        "Al igual que el anterior este tipo de señal de tránsito se usa para indicar curvas a la derecha o izquierda con una curvatura mayor a 90 grados."));
                imagenList.add(new Imagen(R.drawable.senal3, "Camino sinuoso\n" +
                        "Significa la presencia de dos o más curvas inversas en el camino."));
                imagenList.add(new Imagen(R.drawable.senal4, "Curva sinuosa\n" +
                        "Esta señal de tránsito se usa para indicar dos vueltas continuas que van en dirección contraria"));
                imagenList.add(new Imagen(R.drawable.ss1, "Contracurva\nEste señalamiento indica la presencia de dos curvas continuas en donde la primera de ellas es cerrada."));
                imagenList.add(new Imagen(R.drawable.ss2, "Cruce\nAdvierte sobre el cruce de dos caminos. La línea más ancha señalará el camino principal mientras las más angosta es el camino secundario."));
                imagenList.add(new Imagen(R.drawable.ss3, "Entronque\nEsta señal indica la presencia de una calle que se empalma con la principal. La línea gruesa muestra el camino principal y la angosta el camino secundario en donde los autos deben ceder el paso."));
                imagenList.add(new Imagen(R.drawable.ss4, "Bifurcación\nAdvierte de la división del camino. La señal puede ser a la izquierda o a la derecha."));
                imagenList.add(new Imagen(R.drawable.ss5, "Entronque delta\nSe utiliza para señalar una intersección de tres caminos. El camino principal será la línea más ancha, mientras los secundarios con una línea más angosta."));
                imagenList.add(new Imagen(R.drawable.ss6, "Bifurcación en Y\nEsta señal de tránsito advierte sobre la bifurcación del camino. El camino principal será la línea más gruesa."));
                imagenList.add(new Imagen(R.drawable.ss7, "Glorieta\nAdvierte sobre la presencia de una glorieta en la que se encuentran al menos dos caminos."));
                imagenList.add(new Imagen(R.drawable.ss8, "Incorporación de tránsito\nEste tipo de señalamiento avisa sobre la incorporación de tránsito que va en la misma dirección. La incorporación será por el lado que marque la línea más delgada."));
                imagenList.add(new Imagen(R.drawable.ss9, "Doble circulación\nEl señalamiento se utiliza para marcar el cambio de circulación de un sólo sentido a circulación en doble sentido."));
                imagenList.add(new Imagen(R.drawable.ss10, "Salida\nIndica la salida de un camino principal. La línea delgada indicará si la salida es por la izquierda o por la derecha."));
                imagenList.add(new Imagen(R.drawable.ss11, "Reducción del camino\nAdvierte sobre la reducción de la anchura del camino. De forma inversa indica la ampliación del camino."));
                imagenList.add(new Imagen(R.drawable.ss12, "Reducción del camino lateral\nSeñala la reducción del camino por alguno de los costados. El señalamiento indicará si la reducción es por la izquierda o por la derecha."));
                imagenList.add(new Imagen(R.drawable.ss13, "Puente levadizo\nIndica la presencia de un puente levadizo en el camino. Un puente levadizo puede desplazarse de forma horizontal para permitir el paso de embarcaciones."));
                imagenList.add(new Imagen(R.drawable.ss14, "Trabajadores en el camino\nEl señalamiento indica la presencia de trabajadores en el camino."));
                imagenList.add(new Imagen(R.drawable.ss15, "Límite de anchura\nLa señal se utiliza para indicar un camino estrecho que no permite la circulación de dos vehículos de forma simultánea. Suele ir acompañada de un tablero adicional que indica la anchura exacta del camino."));
                imagenList.add(new Imagen(R.drawable.ss16, "Límite de altura\nIndica el límite de espacio de forma vertical cuando es menor a 4.30 metros. Normalmente un segundo tablero indica la altura máxima."));
                imagenList.add(new Imagen(R.drawable.ss17, "Corriente de agua\nAdvierte sobre una corriente de agua baja por la cual se puede circular."));
                imagenList.add(new Imagen(R.drawable.ss18, "Termina pavimento\nSeñala el fin del pavimento y la presencia de un camino de terracería."));
                imagenList.add(new Imagen(R.drawable.ss19, "Superficie derrapante\nEsta señal de tránsito indicará un tramo de pavimento resbaladizo. Es una señal temporal."));
                imagenList.add(new Imagen(R.drawable.ss20, "Pendiente peligrosa\nAdvierte sobre un camino en descenso en el cual habrá que frenar constantemente."));
                imagenList.add(new Imagen(R.drawable.ss21, "Zona de derrumbes\nAdvierte sobre una zona en la cual pueden ocurrir derrumbes."));
                imagenList.add(new Imagen(R.drawable.ss22, "Alto próximo\nEs una de las señales de tránsito preventivas menos conocidas pero muy usual. Advierte sobre una señal de alto cercana."));
                imagenList.add(new Imagen(R.drawable.ss23, "Paso peatonal\nIndica un camino con constante paso peatonal o un cruce peatonal en específico."));
                imagenList.add(new Imagen(R.drawable.ss24, "Zona escolar\nAdvierte sobre una zona de escuelas cercana para que el conductor reduzca su velocidad."));
                imagenList.add(new Imagen(R.drawable.ss25, "Ganado\nIndica la posibilidad de encontrar ganado en el camino."));
                imagenList.add(new Imagen(R.drawable.ss26, "Cruce de ferrocarril\nSeñala el cruce con vías de tren al mismo nivel del camino."));
                imagenList.add(new Imagen(R.drawable.ss27, "Maquinaria agrícola\nSe utiliza para marcar una zona de tránsito de maquinaria agrícola o un cruce específico de este tipo de vehículos."));
                imagenList.add(new Imagen(R.drawable.ss28, "Semáforo\nEste señalamiento advierte sobre la presencia de un semáforo próximo, normalmente en cruces o en zonas donde no se espera hallarlos."));
                imagenList.add(new Imagen(R.drawable.ss29, "Camino dividido\nEsta señal de tránsito advierte sobre la división del camino en dos."));
                imagenList.add(new Imagen(R.drawable.ss30, "Ciclistas\nAdvierte sobre un camino frecuentado por ciclistas o un cruce específico de estos vehículos."));
                imagenList.add(new Imagen(R.drawable.ss31, "Grava suelta\nAdvierte sobre un tramo en donde hay tierra o grava en el camino."));

                break;
            case 2:


                imagenList = new ArrayList<>();
                imagenList.add(new Imagen(R.drawable.senal24, "Alto\nUtilizada para indicar a los conductores que deben detenerse completamente en el lugar donde se encuentra la señal."));
                imagenList.add(new Imagen(R.drawable.senal25, "Ceda el paso\nEsta señal es una advertencia para los conductores para reducir su velocidad y ceder el paso siempre que sea necesario. Es ocupada en zonas de basto tránsito de peatones y vehículos o en entronques con avenidas principales."));
                imagenList.add(new Imagen(R.drawable.senal26, "Autoridades de tránsito\nIndica la presencia de autoridades de tránsito. Normalmente se usa para advertir sobre retenes de revisión."));
                imagenList.add(new Imagen(R.drawable.senal27, "Límite de velocidad\nEste tipo de señal de tránsito indica el límite de velocidad máximo para circular. Recuerda que con el nuevo reglamento de tránsito de la CDMX el límite de velocidad es de 80 km/h."));
                imagenList.add(new Imagen(R.drawable.senal28, "Vuelta continua\nIndica la posibilidad de dar la vuelta. Su uso es común en cruces. La señal puede marcar vuelta a la izquierda y derecha."));
                imagenList.add(new Imagen(R.drawable.s1, "Circulación\nMuestra el sentido de la circulación. Se usa en entradas de calles a fin de evitar la invasión de carriles con circulación única."));
                imagenList.add(new Imagen(R.drawable.s2, "No rebasar\nLa señal de tránsito prohíbe a los conductores rebasar en la zona."));
                imagenList.add(new Imagen(R.drawable.s3, "Conservar la derecha\nInsta a los conductores de camiones a conservar su derecha a fin de dejar el carril izquierdo libre para los vehículos ligeros."));
                imagenList.add(new Imagen(R.drawable.s4, "Doble circulación\nSe utiliza para indicar cuando la circulación cambia de un sólo sentido a ambos. El señalamiento se coloca al inicio de la calle."));
                imagenList.add(new Imagen(R.drawable.s5, "Altura restringida\nEste tipo de señal de tránsito se utiliza para indicar el límite de altura de vehículos para circular en un camino. Es ocupada en la entrada de puentes."));
                imagenList.add(new Imagen(R.drawable.s6, "Anchura restringida\nEl señalamiento indica el límite de anchura de vehículos para circular en un camino."));
                imagenList.add(new Imagen(R.drawable.s7, "Peso restringido\nAdvierte sobre el peso máximo de los vehículos para circular en un camino."));
                imagenList.add(new Imagen(R.drawable.s8, "Prohibido rebasar\nLa señal de tránsito prohíbe rebasar vehículos en un tramo del camino. Es complementada con la línea contínua en el pavimento."));
                imagenList.add(new Imagen(R.drawable.s9, "Parada prohibida\nSe utiliza para indicar a conductores de transporte público la prohibición de subir o bajar pasaje en el lugar."));
                imagenList.add(new Imagen(R.drawable.s10, "No parar\nLa señal prohíbe a los vehículos detenerse. Se utiliza en avenidas de circulación rápida, así como en las entradas y salidas de emergencia."));
                imagenList.add(new Imagen(R.drawable.s11, "Estacionamiento\nIndica que es posible estacionarse en el lugar. Va acompañada con las limitaciones de tiempo, horarios y días donde se puede estacionar."));
                imagenList.add(new Imagen(R.drawable.s12, "Prohibido estacionarse\nSe utiliza en zonas en donde está prohibido estacionarse."));
                imagenList.add(new Imagen(R.drawable.s13, "Vuelta a la derecha prohibida\nProhíbe la vuelta a la derecha ya sea por circulación contraria o condiciones del camino."));
                imagenList.add(new Imagen(R.drawable.s14, "Retorno prohibido\nIndica que se prohíbe el retorno de autos por este cruce. Puede ser porque representa un peligro extra o causa problemas en el tránsito."));
                imagenList.add(new Imagen(R.drawable.s15, "Prohibido seguir de frente\nLa señal de tránsito indica que se prohíbe seguir circulando de frente."));
                imagenList.add(new Imagen(R.drawable.s16, "Prohibido el paso a motocicletas, vehículos pesados y bicicletas\nIndica la prohibición de circular de estos vehículos en una zona. Puede aparecer de forma individual también."));
                imagenList.add(new Imagen(R.drawable.s17, "Prohibido el paso a maquinaria agrícola\nProhíbe la circulación de maquinaria agrícola en una zona."));
                imagenList.add(new Imagen(R.drawable.s18, "Prohibido el paso a peatones\nSe usa en zonas en donde la afluencia de vehículos hace peligroso el cruce de peatones."));
                imagenList.add(new Imagen(R.drawable.s19, "Prohibido usar el claxon\nIndica que está prohibido usar el claxon en una zona."));
            break;
            case 3:


                imagenList = new ArrayList<>();
                imagenList.add(new Imagen(R.drawable.d1, "Primeros auxilios." ));
                imagenList.add(new Imagen(R.drawable.d2, "Oficinas de información."));
                imagenList.add(new Imagen(R.drawable.d3, "Caseta telefónica."));
                imagenList.add(new Imagen(R.drawable.d4, "Estación de servicio."));
                imagenList.add(new Imagen(R.drawable.d5, "Oficina de correos."));
                imagenList.add(new Imagen(R.drawable.d6, "Taller mecánico."));
                imagenList.add(new Imagen(R.drawable.d7, "Baños."));
                imagenList.add(new Imagen(R.drawable.d8, "Restaurantes."));
                imagenList.add(new Imagen(R.drawable.d9, "Hospedaje."));
                imagenList.add(new Imagen(R.drawable.d10, "Estación de ferrocarriles."));
                imagenList.add(new Imagen(R.drawable.d11, "Fin de autopista."));
                imagenList.add(new Imagen(R.drawable.d12, "Artesanías."));
                imagenList.add(new Imagen(R.drawable.d13, "Zona turística."));
                imagenList.add(new Imagen(R.drawable.d14, "Comedor al aire libre."));
                imagenList.add(new Imagen(R.drawable.d15, "Botes de basura."));
                imagenList.add(new Imagen(R.drawable.d16, "Volcán."));
                imagenList.add(new Imagen(R.drawable.d17, "Parque nacional."));
                imagenList.add(new Imagen(R.drawable.d18, "Zona de acampar."));
                imagenList.add(new Imagen(R.drawable.d19, "Juegos infantiles."));
                imagenList.add(new Imagen(R.drawable.d20, "Pesca."));
                imagenList.add(new Imagen(R.drawable.d21, "Excursión."));
                imagenList.add(new Imagen(R.drawable.d22, "Monumento religioso."));
                imagenList.add(new Imagen(R.drawable.d23, "Sitio histórico."));
                imagenList.add(new Imagen(R.drawable.d24, "Cascada."));
                imagenList.add(new Imagen(R.drawable.d25, "Buceo."));
                imagenList.add(new Imagen(R.drawable.d26, "Playa."));
                imagenList.add(new Imagen(R.drawable.d27, "Natación."));
                imagenList.add(new Imagen(R.drawable.d28, "Alpinismo."));
                break   ;



        }


//        // Agrega más imágenes con sus descripciones según sea necesario
//        imageList = new ArrayList<>();
//        imageList.add(R.drawable.senal1);
//        imageList.add(R.drawable.senal2);
//        imageList.add(R.drawable.senal3);
//        imageList.add(R.drawable.senal4);
//
//        imageList.add(R.drawable.senal24);
//        imageList.add(R.drawable.senal25);
//        imageList.add(R.drawable.senal26);
//        imageList.add(R.drawable.senal27);

        // Agrega más imágenes según sea necesario

        // Mostrar la primera imagen
        mostrarImagen();

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Incrementar el índice para mostrar la siguiente imagen
                currentIndex++;
                if (currentIndex >= imagenList.size()) {
                    // Si se alcanza el final de la lista, reiniciar al inicio
                    currentIndex = 0;
                }
                mostrarImagen();
            }
        });



        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cerrar la actividad actual

                // Navegar hacia el fragmento DashboardFragment
                NavController navController = Navigation.findNavController(SenalesActivity.this, R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_dashboard);
                finish();
            }
        });
    }

    private void mostrarImagen() {
        // Obtener la imagen y descripción correspondientes al índice actual
        Imagen imagen = imagenList.get(currentIndex);

        // Mostrar la imagen en el ImageView
        imageView.setImageResource(imagen.getResourceId());

        // Mostrar la descripción en el TextView
        txtDescripcion.setText(imagen.getDescripcion());
    }
}
