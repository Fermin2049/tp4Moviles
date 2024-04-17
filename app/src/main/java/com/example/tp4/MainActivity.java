package com.example.tp4;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.tp4.ui.LlamadaReceiver;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.tp4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private com.example.tp4.MainViewModel mv;
    private ActivityMainBinding binding;
    private LlamadaReceiver llamada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        solitarPermiso();
        registrarBrodcast();

        Button botonlogin = findViewById(R.id.botonlogin);
        final EditText etiquetaUsuario = findViewById(R.id.etiquetaUsuario);
        final EditText etiquetaContrasena = findViewById(R.id.etiquetaContrasena);
        final TextView tvError = findViewById(R.id.tvError);

        botonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etiquetaUsuario.getText().toString();
                String contrasena = etiquetaContrasena.getText().toString();
                vm.buscarUsuario(user,contrasena).observe(MainActivity.this,new Observer<Boolean>()){
                    @Override
                            public void onChanged(Boolean resultado){
                        if (resultado){
                            Intent inten = new Intent(MainActivity.this,MenuActivity.class);
                            startActivities(inten);
                        }else{
                            tvError.setText("Usuario o contrasena incorrecta");
                        }
                    }
                }
            }
        });


}

    private void registrarBrodcast() {
        this.llamada = new LlamadaReceiver();
        registerReceiver(llamada,new IntentFilter("android.net.wifi.suplicant.CONNECTION_CHANGE"));
    }

    private void solitarPermiso() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions( new String[]{CALL_PHONE},1000);
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        unregisterReceiver(llamada);
    }
    }