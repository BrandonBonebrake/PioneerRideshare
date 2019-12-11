package database;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class EmailHandler
{
    public static void send(String to, String subject, String content)
    {
        Email mail = new SimpleEmail();
        String from = "pioneerridesharenoreply@gmail.com";
        String appPassword = "qniufxguzrcisnvx";
        try {
            mail.setFrom(from);
            mail.setSubject(subject);
            mail.setMsg(content);
            mail.addTo(to);
            mail.setTLS(true);
            mail.setHostName("smtp.gmail.com");
            mail.setSmtpPort(587);
            mail.setAuthentication(from, appPassword);
            mail.send();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
