package com.example.asistentevial.ui.requisitos;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.asistentevial.R;
import com.example.asistentevial.ui.Subtemas.subtemas;

public class Requisitos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requisitos);


        // Establecer el modo de pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Ocultar la barra de navegación
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // Navegar hacia el fragmento DashboardFragment
        NavController navController = Navigation.findNavController(Requisitos.this, R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_dashboard);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Navegar hacia el fragmento DashboardFragment
        NavController navController = Navigation.findNavController(Requisitos.this, R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_dashboard);
        finish();
    }

}
