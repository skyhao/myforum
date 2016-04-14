package skyhao.util;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {

    public static void sendHtmlEmail(String subject, String context, String toAddress){
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(ConfigProperties.get("mail.smtp"));
        htmlEmail.setAuthentication(ConfigProperties.get("mail.username"),
                ConfigProperties.get("mail.password"));
        htmlEmail.setCharset("mail.encoding");
        htmlEmail.setStartTLSEnabled(true);

        try {
            htmlEmail.setFrom(ConfigProperties.get("mail.from"));
            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(context);
            htmlEmail.addTo(toAddress);
        } catch (EmailException e) {
            e.printStackTrace();
            throw new RuntimeException("给"+toAddress+"发送电子邮件异常",e);
        }

    }

}
