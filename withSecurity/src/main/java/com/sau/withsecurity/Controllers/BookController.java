package com.sau.withsecurity.Controllers;

import com.sau.withsecurity.DTOs.BookBorrowerDTO;
import com.sau.withsecurity.Models.Book;
import com.sau.withsecurity.Models.Borrower;
import com.sau.withsecurity.Repositories.BookRepository;
import com.sau.withsecurity.Repositories.BorrowerRepository;
import com.sau.withsecurity.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BookController {
    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private BorrowerRepository borrowerRepository;

    public BookController(BookRepository bookRepository, MemberRepository memberRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.borrowerRepository = borrowerRepository;
    }


    @GetMapping("/book")
    public String getIndex(Model model){
        Iterable<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList", bookList);
        return "book/index";
    }

    @GetMapping("/book/add")
    public String addBook(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "/book/addbook";
    }

    @PostMapping("/book/add")
    public String bookAdd (Book book){
        if (book == null){
            System.out.println("Book is null");
            return "redirect:/book";
        }
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book/del")
    public String deleteBook(@RequestParam("id") int id, Model model){
        Optional<Book> book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "/book/deletebook";
    }
    @PostMapping("/book/del")
    public String bookDelete(Book book){
        if(book == null) {
            System.out.println("Book is null");
            return "redirect:/book";
        }
        bookRepository.delete(book);
        return "redirect:/book";
    }

    @GetMapping("/book/update")
    public String updateBook(@RequestParam("id") int id, Model model){
        Optional<Book> book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "/book/updatebook";
    }
    @PostMapping("/book/update")
    public String bookUpdate(Book book){
        if(book == null) {
            System.out.println("Book is null");
            return "redirect:/book";
        }
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book/borrower")
    public String borrowBookList(int id, Model model){
        BookBorrowerDTO bookBorrowerDTO = new BookBorrowerDTO();
        Optional<Book> book = bookRepository.findById(id);
        bookBorrowerDTO.setBookId(book.get().getId());
        bookBorrowerDTO.setBookTitle(book.get().getTitle());
        bookBorrowerDTO.setMemberList(memberRepository.findAll());
        model.addAttribute("bookBorrowerDTO", bookBorrowerDTO);
        return "/book/borrower";
    }
    @PostMapping("book/borrower")
    public String borrwerBook(BookBorrowerDTO bookBorrowerDTO){
        if(bookBorrowerDTO == null) {
            System.out.println("Book Borrower DTO is null");
            return "redirect:/book";
        }
        Borrower borrower = new Borrower();
        borrower.setMember(memberRepository.findById(bookBorrowerDTO.getMemberId()).get());
        borrower.setBook(bookRepository.findById(bookBorrowerDTO.getBookId()).get());
        borrower.setCheckout_date(bookBorrowerDTO.getCheckout_date());
        borrower.setReturn_date(bookBorrowerDTO.getReturn_date());
        borrower.set_deleted(false);
        borrower.set_returned(false);

        borrowerRepository.save(borrower);
        return "redirect:/borrower";
    }
}
