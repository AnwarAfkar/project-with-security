package com.sau.withsecurity.Repositories;

import com.sau.withsecurity.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepository extends JpaRepository<Member,Integer> {
}
