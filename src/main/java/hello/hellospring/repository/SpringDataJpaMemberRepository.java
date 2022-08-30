package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// 스프링 데이터 JPA 회원 리포지토리
// JpaRepository 인터페이스(스프링 데이터 JPA)와 MemberRepository 인터페이스를 상속한 인터페이스 생성
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    Optional<Member> findByName(String name);
}