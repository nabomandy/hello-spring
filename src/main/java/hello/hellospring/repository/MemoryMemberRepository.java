package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//구현체
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    //sequence는 0,1,2키값을 생성해주는 애


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
//        return store.get(id);
        return Optional.ofNullable(store.get(id));
        //null이 반환될 가능성이 있으면 optional 로 감싼다. 결과가 없으면 null이 반환되기 떄문에
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //findAny는 찾는거
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}

