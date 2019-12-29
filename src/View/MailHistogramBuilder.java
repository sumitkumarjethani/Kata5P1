package View;
import Model.*;
import java.util.List;

public class MailHistogramBuilder {
    
    public static Histogram<String> build(List<Mail> mailList){
        Histogram<String> result = new Histogram();
        for (Mail mail : mailList) {
            result.increment(mail.getDomain());
        }
        return result;
    }
}