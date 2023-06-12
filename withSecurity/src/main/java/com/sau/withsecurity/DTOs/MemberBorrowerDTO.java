package com.sau.withsecurity.DTOs;

import com.sau.withsecurity.Models.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberBorrowerDTO {
    private int memberId;
    private String memberName;
    private Iterable<Book>bookList;
    private int bookId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkout_Date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date return_Date;

}
