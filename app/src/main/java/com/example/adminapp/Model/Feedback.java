package com.example.adminapp.Model;

public class Feedback {
    private String subject,message,lastname;

    public Feedback() {
    }

    public Feedback(String subject, String message, String lastname) {
        this.subject = subject;
        this.message = message;
        this.lastname = lastname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}