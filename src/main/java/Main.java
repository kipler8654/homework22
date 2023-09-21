import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        final String username = "adfd130@gmail.com";
        final String password = "nozl zqmp qcnl sogo";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    //вместо youremail@gmail.com надо написать почту на которую мы хотим отправит сообщение
                    InternetAddress.parse("youremail@gmail.com")
            );
            message.setSubject("Test");
            message.setText("""
                    Hello world!
                    """);
            for (int i = 0; i < 10; ++i) {
                Transport.send(message);
            }

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}