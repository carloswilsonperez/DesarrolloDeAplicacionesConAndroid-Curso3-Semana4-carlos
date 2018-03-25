package com.example.administrador.curso3_tarea4;

import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrador.curso3_tarea4.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FormularioActivity extends AppCompatActivity {

    String correo;
    String password;
    EditText etNombre;
    EditText etMail;
    EditText etMensaje;
    Button btnEnviarComentario;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etMail = (EditText) findViewById(R.id.etEmail);
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnEnviarComentario = (Button) findViewById(R.id.btnEnviarComentario);

        correo = "email@gmail.com";
        password = "password";

        btnEnviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Aviso que el correo se está procesando
            Snackbar.make(v, etNombre.getText().toString()+" "+getResources().getString(R.string.aviso_enviando_mail), Snackbar.LENGTH_SHORT).show();

            //politicas de seguridad
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //Propiedades
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.googlemail.com");
            properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.port", "465");

            try {

                //*** Iniciamos la sesión en gmail ****
                session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correo, password);
                    }
                });

                // *** Mintras la sesión no sea nula ****
                if (session != null) {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(correo)); //Correo del emisor
                    message.setSubject("Tarea 4, correo de pruebas"); //Asunto del msj
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(etMail.getText().toString()));//Destinatarios
                    message.setContent(etMensaje.getText().toString(), "text/html; charset=utf-8");// Tipo de contenido que vamos a enviar

                    //Enviamos el correo
                    Transport.send(message);

                    // Aviso que el correo se ha enviado con exito
                    Snackbar.make(v, getResources().getString(R.string.aviso_mail_enviado)+" "+etMail.getText().toString(), Snackbar.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();

                // Aviso que el correo falló
                Snackbar.make(v, getResources().getString(R.string.aviso_faltan_datos), Snackbar.LENGTH_SHORT).show();
            }
            }

        });
    }
}
