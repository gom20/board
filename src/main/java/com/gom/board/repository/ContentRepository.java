package com.gom.board.repository;

import com.gom.board.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    Optional<Content> findById(Long id);

    Optional<Content> findByIdAndDeleteYN(Long id, String deleteYN);

    List<Content> findContentsByDeleteYNEquals(String deleteYN);
}
