package com.example.asistentevial.ui.senales;

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

        imagenList.add(new Imagen(R.drawable.senal24, "Alto\n" +
                "Es una de las señales de tránsito más conocidas cuyo significado. Se utiliza principalmente en cuatro situaciones:"));
        imagenList.add(new Imagen(R.drawable.senal25, "Esta señal es una advertencia para los conductores para reducir su velocidad y ceder el paso siempre que sea necesario. Es ocupada en zonas de basto tránsito de peatones y vehículos o en entronques con avenidas principales."));
        imagenList.add(new Imagen(R.drawable.senal26, "Indica la presencia de autoridades de tránsito. Normalmente se usa para advertir sobre retenes de revisión."));
        imagenList.add(new Imagen(R.drawable.senal27, "Este tipo de señal de tránsito indica el límite de velocidad máximo para circular. Recuerda que con el nuevo reglamento de tránsito de la CDMX el límite de velocidad es de 80 km/h."));

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
