package me.jaeyeon.board.modules.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.jaeyeon.board.constant.Role;
import me.jaeyeon.board.dto.MemberFormDto;
import me.jaeyeon.board.modules.BaseTimeEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
@ToString
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFromDto, PasswordEncoder passwordEncoder) {
        final Member member = new Member();
        member.setName(memberFromDto.getName());
        member.setEmail(memberFromDto.getEmail());
        final String password = passwordEncoder.encode(memberFromDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }

}
