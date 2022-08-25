package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service // 스프링이 뜰 때 스프링 컨테이너에 멤버서비스 등록 @Component => 1. 컴포넌트 스캔 방식
public class MemberService {

    // ctrl+shift+t -> 테스트 신규

    private final MemberRepository memberRepository;

    // @Autowired // 멤버서비스 생성할 때 컨테이너에 등록하고 생성하를 호출하는데 이 때 입력파라미터로 있는 멤버리포지토리를 컨테이너에서 가져와 넣어줌 => 1. 컴포넌트 스캔 방식
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
