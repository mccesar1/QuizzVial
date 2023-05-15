package com.example.asistentevial.ui.precios;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.asistentevial.R;
import com.example.asistentevial.ui.precios.PreciosActivity;

public class PreciosActivity extends AppCompatActivity {

    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precios);

        // Establecer el modo de pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Ocultar la barra de navegación
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cerrar la actividad actual
                // Navegar hacia el fragmento DashboardFragment
                NavController navController = Navigation.findNavController(PreciosActivity.this, R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_dashboard);
                finish();
            }
        });
    }
}
