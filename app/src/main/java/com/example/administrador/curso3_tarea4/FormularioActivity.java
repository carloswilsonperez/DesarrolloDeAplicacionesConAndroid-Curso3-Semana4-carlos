package com.example.administrador.curso3_tarea4;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.administrador.curso3_tarea4.SendMail;

public class FormularioActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void enviarMensaje(View view) {
        TextInputEditText tiNombre = findViewById(R.id.tiNombre);
        TextInputEditText tiCorreo = findViewById(R.id.tiCorreo);
        TextInputEditText tiTextoCorreo = findViewById(R.id.tiTextoCorreo);

        String nombre = tiNombre.getText().toString();
        String correo = tiCorreo.getText().toString();
        String texto = tiTextoCorreo.getText().toString();
        String asunto = "Saludos desde Petagram";
        String mensaje = "Hola " + nombre + "\n\n" + texto;

        SendMail task = new SendMail(this);
        task.execute(asunto, correo, mensaje);
    }
}
