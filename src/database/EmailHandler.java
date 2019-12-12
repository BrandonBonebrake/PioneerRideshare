package database;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import ride.Ride;
import student.Student;

public class EmailHandler
{
    public static void send(String to, String subject, String content)
    {
        Email mail = new SimpleEmail();
        String from = "PioneerRideshareNoreply@gmail.com";
        String appPassword = "qniufxguzrcisnvx";

        try
        {
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
        catch (Exception ignored) {}
    }

    public static void rideFilled(Ride ride, Student studentRec, Student studentSent)
    {
        String subject = "Your Ride ";
        String content = studentRec.getFirstName() + " " + studentRec.getLastName() + ",\n\n"
                + studentSent.getFirstName() + " " + studentSent.getLastName() + " would like to ";

        if(ride.getIsOffer())
        {
            subject += "Offer";
            content += "join";
        }
        else
        {
            subject += "Request";
            content += "drive";
        }
        subject += " Has Been Accepted";
        content += " you from " + ride.getDepartLocation() + " on " + ride.getLeaveDate() + " at " + ride.getLeaveTime() + ".\n\n";
        content += "The destination location is " + ride.getReturnLocation() + ".\n";
        content += "If you wish to travel back together, they will be returning on " + ride.getReturnDate() + " at " + ride.getReturnTime() + ".\n\n";
        content += "Contact them by their campus email: " + studentSent.getEmail() + " ASAP\n\n";
        content += "Thank you for using Pioneer Rideshare! Have a wonderful drive!";

        send(ride.getStudentEmail(),
            subject,
            content);
    }
}
