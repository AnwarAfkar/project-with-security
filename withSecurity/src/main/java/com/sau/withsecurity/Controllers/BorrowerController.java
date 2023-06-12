package com.sau.withsecurity.Controllers;

import com.sau.withsecurity.DTOs.BorrowerDTO;
import com.sau.withsecurity.Models.Borrower;
import com.sau.withsecurity.Models.Member;
import com.sau.withsecurity.Repositories.BookRepository;
import com.sau.withsecurity.Repositories.BorrowerRepository;
import com.sau.withsecurity.Repositories.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class BorrowerController {
    private BorrowerRepository borrowerRepository;
    private MemberRepository memberRepository;
    private BookRepository bookRepository;

    public BorrowerController(BorrowerRepository borrowerRepository, MemberRepository memberRepository, BookRepository bookRepository) {
        this.borrowerRepository = borrowerRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/borrow")
    public String getIndex(Model model){
        Iterable<Borrower> borrowerList = borrowerRepository.getAll();
        model.addAttribute("borrowerList", borrowerList);
        return "/borrower/index";
    }

    @PostMapping("/borrower/del")
    public String borrowerDelete(Borrower borrower){
        if(borrower == null){
            System.out.println("Borrower is null");
            return "redirect:/borrower";
        }
        borrowerRepository.setDeleted(borrower.getId());
        return "redirect:/borrower";
    }

    @PostMapping("/borrower/bor")
    public String borrowerReturn(Borrower borrower){
        if(borrower == null) {
            System.out.println("Borrower is null");
            return "redirect:/borrower";
        }
        borrowerRepository.setReturned(borrower.getId());
        return "redirect:/borrower";
    }


    @GetMapping("/borrower/borrow")
    public String borrow(Model model){
        BorrowerDTO borrowerDTO = new BorrowerDTO();
        borrowerDTO.setMemberList(memberRepository.findAll());
        borrowerDTO.setBookList(bookRepository.findAll());
        model.addAttribute("borrowerDTO", borrowerDTO);
        return "borrower/borrow";
    }
    @PostMapping("borrower/borrow")
    public String borrowABook(BorrowerDTO borrowerDTO){
        if(borrowerDTO == null) {
            System.out.println("Borrower DTO is null");
            return "redirect:/borrower";
        }
        Borrower borrower = new Borrower();
        borrower.setMember(memberRepository.findById(borrowerDTO.getMemberId()).get());
        borrower.setBook(bookRepository.findById(borrowerDTO.getBookId()).get());
        borrower.setCheckout_date(borrowerDTO.getCheckout_Date());
        borrower.setReturn_date(borrowerDTO.getCheckout_Date());
        borrower.set_deleted(false);
        borrower.set_returned(false);

        borrowerRepository.save(borrower);
        return "redirect:/borrower";
    }
}
