package com.example.tp4.ui.llamar;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class LlamarViewModel extends AndroidViewModel  {

    private final MutableLiveData<String> mText;

    public LlamarViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Ingrese el número a llamar:");
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Método para iniciar una llamada, debe ser invocado desde el Fragment
    public void makePhoneCall(String phoneNumber) {
        if (!phoneNumber.trim().isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Se requiere cuando se inicia una actividad fuera de un contexto de actividad
            try {
                getApplication().startActivity(intent);
            } catch (SecurityException e) {
                // No se tiene permiso para hacer la llamada
                mText.setValue("Error: Permiso de llamada no concedido.");
            }
        } else {
            // No se ingresó un número
            mText.setValue("Por favor, ingrese un número de teléfono.");
        }
    }
}