package Model;

public class Mail {
    private String mail;

    public Mail(String mail) {
        this.mail = mail;
    }
    
    public String getDomain() {
        return mail.substring(mail.indexOf("@") + 1);
    }
    
    public static boolean isMail(String line){
        return line.matches("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
    }
}
