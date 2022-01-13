package me.jaeyeon.board.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsPagingRepository extends JpaRepository<Posts, Long> {

    @Query(value = "SELECT p FROM Posts p ORDER BY p.id DESC")
    Page<Posts> findAllDesc(Pageable pageable);
}
