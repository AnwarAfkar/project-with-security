package com.sau.withsecurity.Controllers;

import com.sau.withsecurity.DTOs.MemberBorrowerDTO;
import com.sau.withsecurity.Models.Borrower;
import com.sau.withsecurity.Models.Member;
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
public class MemberController {
    private MemberRepository memberRepository;
    private BookRepository bookRepository;
    private BorrowerRepository borrowerRepository;


    public MemberController(MemberRepository memberRepository, BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @GetMapping("/member")
    public String getIndex(Model model){
        Iterable<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);
        return "member/index";
    }

    @GetMapping("/member/add")
    public String addMember(Model model){
        Member member = new Member();
        model.addAttribute("member", member);
        return "/member/addmember";
    }
    @PostMapping("member/add")
    public String memberAdd(Member member){
        if(member == null) {
            System.out.println("Member is null");
            return "redirect:/member";
        }
        memberRepository.save(member);
        return "redirect:/member";
    }
    @GetMapping("/member/del")
    public String deleteMember(@RequestParam("id") int id, Model model){
        Optional<Member> member = memberRepository.findById(id);
        model.addAttribute("member", member);
        return "/member/delmember";
    }
    @PostMapping("/member/del")
    public String memberDelete(Member member){
        if(member == null) {
            System.out.println("Member is null");
            return "redirect:/member";
        }
        memberRepository.delete(member);
        return "redirect:/member";
    }

    @GetMapping("/member/update")
    public String memberMember(@RequestParam("id") int id, Model model){
        Optional<Member> member = memberRepository.findById(id);
        model.addAttribute("member", member);
        return "/member/updatemember";
    }
    @PostMapping("/member/update")
    public String memberUpdate(Member member){
        if(member == null) {
            System.out.println("Member is null");
            return "redirect:/member";
        }
        memberRepository.save(member);
        return "redirect:/member";
    }

    @GetMapping("/member/borrower")
    public String borrowBookToMember(int id, Model model){
        MemberBorrowerDTO memberBorrowerDTO = new MemberBorrowerDTO();
        Optional<Member> member = memberRepository.findById(id);
        memberBorrowerDTO.setMemberId(member.get().getId());
        memberBorrowerDTO.setMemberName(member.get().getName());
        memberBorrowerDTO.setBookList(bookRepository.findAll());
        model.addAttribute("memberBorrowerDTO", memberBorrowerDTO);
        return "member/borrower";
    }

    @PostMapping("member/borrower")
    public String borrowBook(MemberBorrowerDTO memberBorrowerDTO){
        if(memberBorrowerDTO == null) {
            System.out.println("Member Borrower DTO is null");
            return "redirect:/member";
        }
        Borrower borrower = new Borrower();
        borrower.setMember(memberRepository.findById(memberBorrowerDTO.getMemberId()).get());
        borrower.setBook(bookRepository.findById(memberBorrowerDTO.getBookId()).get());
        borrower.setCheckout_date(memberBorrowerDTO.getCheckout_Date());
        borrower.setReturn_date(memberBorrowerDTO.getReturn_Date());
        borrower.set_deleted(false);
        borrower.set_returned(false);

        borrowerRepository.save(borrower);
        return "redirect:/borrower";
    }
}
