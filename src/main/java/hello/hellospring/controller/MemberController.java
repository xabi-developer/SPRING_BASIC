package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 스프링이 시작될 때 스프링컨테이너라는 스프링통이 생기는데
// 컨트롤러 어노테이션을 붙인 MemberController 객체를 생성하여 
// 스프링컨테이너에 넣어두고 스프링에서 관리함
// = 스프링 컨테이너에서 스프링 빈이 관리된다 라고 표현
// 하나의 인스턴스를 공유하기 위해 스프링 컨테이너에 빈을 등록해놓고 공유
@Controller // @Component -> 컴포넌트스캔 방식 의존관계
public class MemberController {

    private final MemberService memberService;

    @Autowired // 생성자의 오토와이어드 -> 스프링 컨테이너에 등록된 멤버서비스 객체를 가져와 연결시켜줌 : Dependency Injection 의존관계
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    } // <-생성자주입 방식이 가장 좋음 : setter주입 방식은 public으로 메소드가 노출되므로 위험

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm"; // members 폴더의 createMemberForm.html
    }

    // 데이터 등록 시 post 사용 / 조회는 get 사용
    // 현재는 메모리 안에 저장했기 때문에 자바를 내려버리면 데이터가 날아감 -> 파일 or DB에 저장해야 함
    @PostMapping("/members/new") // <form action="/members/new" method="post">
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // -> Model에 담아둔 값은 html에서 $변수로 구현됨
        return "members/memberList";
    }

}
