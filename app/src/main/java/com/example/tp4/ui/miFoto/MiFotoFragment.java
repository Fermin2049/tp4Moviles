package com.example.tp4.ui.miFoto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp4.databinding.FragmentGalleryBinding;

public class MiFotoFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MiFotoViewModel miFotoViewModel =
                new ViewModelProvider(this).get(MiFotoViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Ya no necesitas el TextView para la lógica de la imagen
        // final TextView textView = binding.textGallery;
        // miFotoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Configura tu ImageView aquí si es necesario o si quieres realizar alguna acción específica en el Fragment.
        // De lo contrario, solo mostrará la imagen que definiste en el XML.

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}