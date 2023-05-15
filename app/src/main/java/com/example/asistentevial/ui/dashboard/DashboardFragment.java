package com.example.asistentevial.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.asistentevial.R;
import com.example.asistentevial.databinding.FragmentDashboardBinding;
import com.example.asistentevial.ui.Preguntas.PreguntasActivity;
import com.example.asistentevial.ui.Preguntas.PreguntasActivity2;
import com.example.asistentevial.ui.precios.PreciosActivity;
import com.example.asistentevial.ui.requisitos.Requisitos;
import com.example.asistentevial.ui.senales.SenalesActivity;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button btnSenales;
    private Button btnPrecios;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false); // Inflate the layout for this fragment
        View root = binding.getRoot();
        Button1 = root.findViewById(R.id.button1);
        Button2 = root.findViewById(R.id.button2);
        Button3 = root.findViewById(R.id.button3);
        btnSenales = root.findViewById(R.id.btnSenales);
        btnPrecios = root.findViewById(R.id.btnPrecios);

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //abrir nueva actividad con varios botones para seleccionar el tipo de reporte
                Intent intent = new Intent(getActivity(), PreguntasActivity.class);
                intent.putExtra("BOTON_PRESIONADO", 1);
                startActivity(intent);
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //abrir nueva actividad con varios botones para seleccionar el tipo de reporte
                Intent intent = new Intent(getActivity(), Requisitos.class);
//                intent.putExtra("BOTON_PRESIONADO", 2);
                startActivity(intent);
            }
        });
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //abrir nueva actividad con varios botones para seleccionar el tipo de reporte
                Intent intent = new Intent(getActivity(), PreguntasActivity2.class);
                intent.putExtra("BOTON_PRESIONADO", 2);
                startActivity(intent);
            }
        });

        btnSenales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //abrir nueva actividad con varios botones para seleccionar el tipo de reporte
                Intent intent = new Intent(getActivity(), SenalesActivity.class);

                startActivity(intent);
            }
        });

        btnPrecios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //abrir nueva actividad con varios botones para seleccionar el tipo de reporte
                Intent intent = new Intent(getActivity(), PreciosActivity.class);

                startActivity(intent);
            }
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}