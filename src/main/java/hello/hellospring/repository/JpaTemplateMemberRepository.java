package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaTemplateMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaTemplateMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> reult = em.createQuery("select m from member m", Member.class).getResultList();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
