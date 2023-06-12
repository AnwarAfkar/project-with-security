package com.sau.withsecurity.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrower")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "checkoutDate")
    private Date checkout_date;
    @Column(name = "returnDate")
    private Date return_date;
    @Column(name = "isReturned", columnDefinition = "boolean default false")
    private boolean is_returned;
    @Column(name = "isDeleted", columnDefinition = "boolean default false")
    private boolean is_deleted;

    @ManyToOne
    //@JoinTable(name = "member")
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    //@JoinTable(name = "book")
    @JoinColumn(name = "book_id")
    private Book book;


}
