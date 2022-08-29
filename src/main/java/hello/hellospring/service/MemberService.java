package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service // 스프링이 뜰 때 스프링 컨테이너에 멤버서비스 등록 @Component => 1. 컴포넌트 스캔 방식
@Transactional // 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋한다. 만약 런타임 예외가 발생하면 롤백한다.
// # JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
public class MemberService {

    // ctrl+shift+t -> 테스트 신규

    private final MemberRepository memberRepository;

    // @Autowired // 멤버서비스 생성할 때 컨테이너에 등록하고 생성자를 호출하는데 이 때 입력파라미터로 있는 멤버리포지토리를 컨테이너에서 가져와 넣어줌 => 1. 컴포넌트 스캔 방식
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) { // ctrl+alt+v : 리턴값 생성
        validateDupMember(member); // 중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // ifPresent : Optional 안의 멤버객체 메소드 사용
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단건 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
