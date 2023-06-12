package com.sau.withsecurity.Repositories;

import com.sau.withsecurity.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
