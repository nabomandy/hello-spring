package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
//여기서 어떤일이 벌어지냐면
    // 스프링 컨테이너라는 통이 생긴다. 거기에 이 @Controller 어노테이션이 있으면
    // 이 MemberController 객체를 생성해서 스프링에 넣어두고 관리를 한다.
    // 스프링 컨테이너에서 빈이 관리된다고 표현
    // 스프링이 관리를 하게되면 다 스프링 컨테이너에 등록을 하고 거기서 받아서 쓰게 해야한다.
//    private final MemberService memberService = new MemberService();
    private final MemberService memberService;
    //필드 주입->@Autowired private final MemberService memberService;

//    @Autowired -> 세터 주입
//    public void setMemberService(MemberService memberService){
//        this.memberService=memberService;
//    }

    @Autowired // ->생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("member.getName() = " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
