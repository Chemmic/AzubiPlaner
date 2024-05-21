package de.dhbw.softwareengineering.azubiplaner.domain.values;

import java.util.Objects;
import java.util.regex.Pattern;

import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.InvalidEmailException;
import jakarta.persistence.Embeddable;

@Embeddable
public class Email {

    private String mail;
   
    protected Email() {
		this.mail = "";
        // JPA braucht diesen für tests.
    }
    
    
    public void setMail(String mail) throws InvalidEmailException {
    	 if (isValid(mail)) {
             this.mail = mail;
         } else {
             throw new InvalidEmailException(mail);
         }
    }
    public Email(String mail) throws InvalidEmailException {
        if (isValid(mail)) {
            this.mail = mail;
        } else {
            throw new InvalidEmailException(mail);
        }
    }

    public String getMail() {
        return mail;
    }

    private boolean isValid(String email) {
        return email != null && Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE)
                .matcher(email)
                .matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return mail.equals(email.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }

    @Override
    public String toString() {
        return mail;
    }
}