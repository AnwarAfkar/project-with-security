package com.sau.withsecurity.Repositories;

import com.sau.withsecurity.Models.Borrower;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {
    @Query("SELECT b FROM Borrower b WHERE b.is_deleted = false")
    Iterable<Borrower> getAll();

    @Modifying
    @Transactional
    @Query("UPDATE Borrower b SET b.is_deleted = true WHERE b.id = :id")
    void setDeleted(@Param("id")int id);

    @Modifying
    @Transactional
    @Query("UPDATE Borrower b SET b.is_returned = true WHERE b.id = :id")
    void setReturned(@Param("id") int id);

}
