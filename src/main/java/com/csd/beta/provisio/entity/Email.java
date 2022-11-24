package com.csd.beta.provisio.entity;

public class Email {
    private long id;
    private String date_sent;
    private long reservation_num;
    private String user_email;
    private String user_firstname;
    private String user_phone;
    private String subject;
    private String message;

    public Email() {}

    public Email(long id, String date_sent, long reservation_num, String user_email, String user_firstname, String user_phone, String subject, String message) {
        this.id = id;
        this.date_sent = date_sent;
        this.reservation_num = reservation_num;
        this.user_email = user_email;
        this.user_firstname = user_firstname;
        this.user_phone = user_phone;
        this.subject = subject;
        this.message = message;
    }

    public Email(String date_sent, long reservation_num, String user_email, String user_firstname, String user_phone, String subject, String message) {
        this.date_sent = date_sent;
        this.reservation_num = reservation_num;
        this.user_email = user_email;
        this.user_firstname = user_firstname;
        this.user_phone = user_phone;
        this.subject = subject;
        this.message = message;
    }

    public void setID(long newID) { this.id = newID; }
    public void setDateSent(String x) { this.date_sent = x; }
    public void setReservationNum(long newID) { this.reservation_num = newID; }
    public void setUserEmail(String x) {this.user_email=x;}
    public void setUserFirstName(String x) {this.user_firstname=x;}
    public void setUserPhone(String x) {this.user_phone=x;}
    public void setSubject(String x) {this.subject=x;}
    public void setMessage(String x) {this.message=x;}

    public long getID() {return this.id;}
    public String getDateSent() {return this.date_sent;}
    public long getReservationNum() {return this.reservation_num;}
    public String getUserEmail() {return this.user_email;}
    public String getUserFirstName() {return this.user_firstname;}
    public String getUserPhone() {return this.user_phone;}
    public String getSubject() {return this.subject;}
    public String getMessage() {return this.message;}
}
