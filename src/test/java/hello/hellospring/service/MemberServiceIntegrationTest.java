package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
//스프링 기반으로 테스트 하는 법
@SpringBootTest
@Transactional
//@Transactional을 걸어서 테스트를 시작하고-> 테스트가 끝나면 롤백을 해준다.
class MemberServiceIntegrationTest {
//Test는 과감하게 한글로 바꿔도 된다.

    //기존코드는 인젝션이 좋은데, 테스트는 필드기반으로 이렇게 해도 편하다.
    @Autowired MemberService memberService;
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입join() {
        //give 이런 상황이 주어져서
        Member member = new Member();
        member.setName("hello");

        //when 이거를 실행했을 때 / 뭘 검증 할거냐
        Long saveId = memberService.join(member);

        //then 결과가 이게 나와야 되
        Member findMember = memberService.findOne(saveId).get();

        //Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
       // assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //람다식

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //then
    }

}