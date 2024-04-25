package hello.shoppingmallproject.repository;


import hello.shoppingmallproject.domian.Member;
import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByIdx(Long idx);
    Optional<Member> findById(String id);
    Optional<Member> findByNickName(String name);
    Optional<Member> findByEmail(String email);

    List<Member> findAll();
}
