package com.example.administrador.curso3_tarea4;

/**
 * Created by carlo on 3/25/2018.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail extends AsyncTask<String, Void, Void> {
    private Context context;

    public SendMail(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        String username = Config.EMAIL;
        String password = Config.PASSWORD;

        String subject = params[0];
        String recipient = params[1];
        String text = params[2];

        Properties props = new Properties();

        props.setProperty("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");

        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setFrom("carloswilsonperez@gmail.com");
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
            msg.setSubject(subject);
            msg.setText(text);
            msg.setHeader("X-Mailer", "Petagram");
            msg.setSentDate(new Date());

            Transport.send(msg, username, password);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Toast.makeText(context, "Mensaje enviado", Toast.LENGTH_SHORT).show();
    }
}