package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    // 모든 메소드는 테스트 순서와 상관 없이 따로 동작하게 만들어야 한다 -> 클래스 테스트 시 메소드 호출 순서는 보장되지 않는다
    // -> afterEach 메소드를 통해 테스트 종료 시마다 저장소를 클리어해 문제가 발생하지 않도록 한다
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    // 메소드가 끝날 때마다 호출되는 콜백메소드
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 결과 확인
//      Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); // import org.assertj.core.api.Assertions; -> static
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + F6 일괄변경
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}

//TDD 테스트주도개발 : 테스트를 먼저 만들고 개발하는 방식