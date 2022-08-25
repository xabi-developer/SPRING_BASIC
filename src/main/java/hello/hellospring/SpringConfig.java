package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링이 뜰 때 컨피규레이션 어노테이션을 읽고 하위에 @Bean이 있으면 스프링 빈에 등록
public class SpringConfig {

    @Bean // 메소드 내의 로직을 호출하며 스프링 컨테이너에 스프링 빈으로 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
// 2. 자바 코드로 스프링 빈 구현
// 장점 : 상황에 따라 구현 클래스를 변경해야하면 설정을 통해 스프링 빈에 등록하는 객체를 변경하면 된다.