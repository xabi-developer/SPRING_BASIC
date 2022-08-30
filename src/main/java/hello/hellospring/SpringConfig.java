package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 2. 자바 코드로 스프링 빈 구현 방식
// 장점 : 상황에 따라 구현 클래스를 변경해야하면 설정을 통해 스프링 빈에 등록하는 객체를 변경하면 된다.

@Configuration // 스프링이 뜰 때 컨피규레이션 어노테이션을 읽고 하위에 @Bean이 있으면 스프링 빈에 등록
public class SpringConfig {

    // 스프링 데이터 JPA 회원 리포지토리를 사용하도록 스프링 설정 변경
    // 스프링 데이터 JPA가 SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다.
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    private DataSource dataSource;
//    private final EntityManager em; // JPA
//
//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
//
//    @Bean // 메소드 내의 로직을 호출하며 스프링 컨테이너에 스프링 빈으로 등록
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
////      return new MemoryMemberRepository(); // 메모리
////      return new JdbcMemberRepository(dataSource); // JDBC
////      return new JdbcTemplateMemberRepository(dataSource); // JDBC Template
//        return new JpaMemberRepository(em); // JPA
//    } // 메모리 -> DB로 방식을 변경할 때, Config 코드만 수정할 수 있는 스프링의 장점!
}