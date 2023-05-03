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
import com.example.asistentevial.ui.Subtemas.subtemas;
import com.example.asistentevial.ui.Subtemas.subtemas2;
import com.example.asistentevial.ui.Subtemas.subtemas3;
import com.example.asistentevial.ui.Subtemas.subtemas4;
import com.example.asistentevial.ui.Subtemas.subtemas5;
import com.example.asistentevial.ui.Subtemas.subtemas6;
import com.example.asistentevial.ui.requisitos.Requisitos;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private Button Button1;
    private Button Button2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false); // Inflate the layout for this fragment
        View root = binding.getRoot();

        Button1 = root.findViewById(R.id.button1);
        Button2 = root.findViewById(R.id.button2);

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
                intent.putExtra("BOTON_PRESIONADO", 2);
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