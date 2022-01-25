package me.jaeyeon.board.modules.member;

import me.jaeyeon.board.modules.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
