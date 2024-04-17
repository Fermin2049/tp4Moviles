package com.example.tp4.ui.llamar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp4.databinding.FragmentLlamarBinding;

public class LlamarFragment extends Fragment {

    private FragmentLlamarBinding binding;
    private LlamarViewModel llamarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        llamarViewModel =
                new ViewModelProvider(this).get(LlamarViewModel.class);
        binding = FragmentLlamarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLlamar; // Asegúrate de tener un TextView en tu layout con este ID
        llamarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final EditText editTextNumber = binding.editTextNumber; // Asegúrate de tener un EditText en tu layout para el número
        final Button buttonCall = binding.buttonCall; // Asegúrate de tener un Button en tu layout para llamar

        buttonCall.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(
                    getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                // Aquí llamamos al método makePhoneCall del ViewModel
                llamarViewModel.makePhoneCall(editTextNumber.getText().toString());
            }
        });

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes realizar la llamada si es necesario
            } else {
                // Permiso denegado, maneja la situación
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}