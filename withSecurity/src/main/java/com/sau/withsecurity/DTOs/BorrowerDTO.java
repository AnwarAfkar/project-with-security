package com.sau.withsecurity.DTOs;

import com.sau.withsecurity.Models.Book;
import com.sau.withsecurity.Models.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerDTO {
    private Iterable<Member> memberList;
    private int memberId;
    private Iterable<Book> bookList;
    private int bookId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkout_Date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date return_Date;
}
