package com.example.administrador.curso3_tarea4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrador.curso3_tarea4.R;

public class AcercadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnEnviarMail = (Button)findViewById(R.id.btnEnviarComentario);
        final EditText etNombre = (EditText)findViewById(R.id.etNombre);
        final EditText etEmail = (EditText)findViewById(R.id.etEmail);
        final EditText etMensaje = (EditText)findViewById(R.id.etMensaje);
        btnEnviarMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String email = etEmail.getText().toString();
                String mensaje = etMensaje.getText().toString();

                SendMail sm = new SendMail(AcercadeActivity.this, email, nombre, mensaje);
                sm.execute();
            }
        });

    }

}
