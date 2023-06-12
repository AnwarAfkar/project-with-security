package com.sau.withsecurity.DTOs;

import com.sau.withsecurity.Models.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookBorrowerDTO {
    private int bookId;
    private String bookTitle;
    private Iterable<Member> memberList;
    private int memberId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkout_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date return_date;

}
