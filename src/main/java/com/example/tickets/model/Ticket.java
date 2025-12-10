package com.example.tickets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название мероприятия не может быть пустым")
    @Size(max = 100, message = "Название мероприятия слишком длинное")
    private String eventName;

    @NotBlank(message = "Имя покупателя не может быть пустым")
    @Size(max = 50, message = "Имя покупателя слишком длинное")
    private String buyerName;

    @NotBlank(message = "Номер места не может быть пустым")
    @Size(max = 10, message = "Номер места слишком длинный")
    private String seatNumber;

    public Ticket() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
}


//package com.example.tickets.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//@Entity
//public class Ticket {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String eventName;
//    private String buyerName;
//    private String seatNumber;
//
//    public Ticket() {}
//
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getEventName() { return eventName; }
//    public void setEventName(String eventName) { this.eventName = eventName; }
//
//    public String getBuyerName() { return buyerName; }
//    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }
//
//    public String getSeatNumber() { return seatNumber; }
//    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
//}
