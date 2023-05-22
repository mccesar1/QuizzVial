package com.example.asistentevial.ui.precios;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.asistentevial.R;
import com.example.asistentevial.ui.precios.PreciosActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PreciosActivity extends AppCompatActivity {

    private Button btnVolver;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;

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
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);

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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mostrar un bottom sheet dialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreciosActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet);
                bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Establecer el listener de cancelación

                bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //cerrar el bottom sheet dialog
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();
                //Obtener el TextView donde se mostrará el mensaje
                TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
                //Asignar el mensaje al TextView
                mensajeTextView.setText("1 año    $496\n3 años   $1,242\n6 años   $1,663");
                mensajeTextView.setTextSize(30); // Cambia el tamaño de la fuente a 20 sp
                LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
                bottomSheetLayout.setBackgroundResource(android.R.color.holo_green_light); // Establece el color de fondo a verde

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mostrar un bottom sheet dialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreciosActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet);
                bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Establecer el listener de cancelación

                bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //cerrar el bottom sheet dialog
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();
                //Obtener el TextView donde se mostrará el mensaje
                TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
                //Asignar el mensaje al TextView
                mensajeTextView.setText("1 año    $507\n3 años   $1,288\n6 años   $1,728");
                mensajeTextView.setTextSize(30); // Cambia el tamaño de la fuente a 20 sp
                LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
                bottomSheetLayout.setBackgroundResource(android.R.color.holo_green_light); // Establece el color de fondo a verde

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mostrar un bottom sheet dialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreciosActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet);
                bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Establecer el listener de cancelación

                bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //cerrar el bottom sheet dialog
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();
                //Obtener el TextView donde se mostrará el mensaje
                TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
                //Asignar el mensaje al TextView
                mensajeTextView.setText("1 año    $359\n3 años   $666\n6 años   $840");
                mensajeTextView.setTextSize(30); // Cambia el tamaño de la fuente a 20 sp
                LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
                bottomSheetLayout.setBackgroundResource(android.R.color.holo_green_light); // Establece el color de fondo a verde

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mostrar un bottom sheet dialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreciosActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet);
                bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Establecer el listener de cancelación

                bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //cerrar el bottom sheet dialog
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();
                //Obtener el TextView donde se mostrará el mensaje
                TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
                //Asignar el mensaje al TextView
                mensajeTextView.setText("1 año    N/A\n3 años   N/A\n6 años   $2,253");
                mensajeTextView.setTextSize(30); // Cambia el tamaño de la fuente a 20 sp
                LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
                bottomSheetLayout.setBackgroundResource(android.R.color.holo_green_light); // Establece el color de fondo a verde

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mostrar un bottom sheet dialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreciosActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet);
                bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Establecer el listener de cancelación

                bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //cerrar el bottom sheet dialog
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();
                //Obtener el TextView donde se mostrará el mensaje
                TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
                //Asignar el mensaje al TextView
                mensajeTextView.setText("1 año    N/A\n3 años   N/A\n6 años   $2,596");
                mensajeTextView.setTextSize(30); // Cambia el tamaño de la fuente a 20 sp
                LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
                bottomSheetLayout.setBackgroundResource(android.R.color.holo_green_light); // Establece el color de fondo a verde

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mostrar un bottom sheet dialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreciosActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet);
                bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Establecer el listener de cancelación

                bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //cerrar el bottom sheet dialog
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();
                //Obtener el TextView donde se mostrará el mensaje
                TextView mensajeTextView = bottomSheetDialog.findViewById(R.id.mensajeTextView);
                //Asignar el mensaje al TextView
                mensajeTextView.setText("1 año    $1,081\n3 años   $1,530\n6 años   N/A");
                mensajeTextView.setTextSize(30); // Cambia el tamaño de la fuente a 20 sp

                LinearLayout bottomSheetLayout = bottomSheetDialog.findViewById(R.id.bottom_sheet);
                bottomSheetLayout.setBackgroundResource(android.R.color.holo_green_light); // Establece el color de fondo a verde

            }
        });
    }
}
