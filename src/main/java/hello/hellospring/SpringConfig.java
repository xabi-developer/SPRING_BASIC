package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 2. 자바 코드로 스프링 빈 구현 방식
// 장점 : 상황에 따라 구현 클래스를 변경해야하면 설정을 통해 스프링 빈에 등록하는 객체를 변경하면 된다.

@Configuration // 스프링이 뜰 때 컨피규레이션 어노테이션을 읽고 하위에 @Bean이 있으면 스프링 빈에 등록
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // 메소드 내의 로직을 호출하며 스프링 컨테이너에 스프링 빈으로 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//      return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource); // 메모리 -> DB로 방식을 변경할 때, Config 코드만 수정할 수 있는 스프링의 장점!
    }
}