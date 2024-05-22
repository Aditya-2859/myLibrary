package com.library.library.repository;

import com.library.library.entity.AllocateBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocatedBookRepository extends JpaRepository<AllocateBook, Integer> {
}
