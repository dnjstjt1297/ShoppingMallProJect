package hello.shoppingmallproject.service;

import hello.shoppingmallproject.domian.Member;
import hello.shoppingmallproject.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfaqw@qwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);


        //when
        Long saveIdx = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveIdx).get();
        assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void 빈IDJoin() {
        //given
        Member member = new Member();

        member.setPw("abcd1234!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfaqw@qwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        NullPointerException e = assertThrows(NullPointerException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("아이디는 필수 항목입니다.");
    }

    @Test
    void 빈PWJoin() {
        //given
        Member member = new Member();

        member.setId("abcdefghi");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfaqw@qwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        NullPointerException e = assertThrows(NullPointerException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("비밀번호는 필수 항목입니다.");
    }

    @Test
    void 빈이름Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setNickname("전우치");
        member.setEmail("asdfaqw@qwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        NullPointerException e = assertThrows(NullPointerException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("이름은 필수 항목입니다.");
    }
    @Test
    void 빈닉네임Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("전우치");
        member.setEmail("asdfaqw@qwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        NullPointerException e = assertThrows(NullPointerException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("닉네임은 필수 항목입니다.");
    }

    @Test
    void 빈이메일Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        NullPointerException e = assertThrows(NullPointerException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("이메일은 필수 항목입니다.");
    }

    @Test
    void 빈번호Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("전우치");
        member.setNickname("전우치");
        member.setEmail("asdfaqw@qwer.cs");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        NullPointerException e = assertThrows(NullPointerException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("핸드폰 번호는 필수 항목입니다.");
    }

    @Test
    void 빈주소Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("전우치");
        member.setNickname("전우치");
        member.setEmail("asdfaqw@qwer.cs");
        member.setPhone("01099999999");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        NullPointerException e = assertThrows(NullPointerException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("주소는 필수 항목입니다.");
    }

    @Test
    void 잘못된비밀번호1Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#김");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfa@qwqwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("잘못된 비밀번호 형식입니다.");
    }

    @Test
    void 잘못된비밀번호2Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfa@qwqwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("비밀번호는 숫자,영어,특수문자를 모두 사용해야합니다.");
    }

    @Test
    void 잘못된비밀번호3Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfa@qwqwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("비밀번호는 숫자,영어,특수문자를 모두 사용해야합니다.");
    }

    @Test
    void 잘못된비밀번호4Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("1234!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfa@qwqwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("비밀번호는 숫자,영어,특수문자를 모두 사용해야합니다.");
    }

    @Test
    void 잘못된이메일1Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfaqwqwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("잘못된 이메일 형식입니다.");
    }

    @Test
    void 잘못된핸드폰번호Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfaqwqwer.cs");
        member.setPhone("010-9999a9999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("잘못된 핸드폰 번호입니다. -를 제외하고 입력하세요.");
    }

    @Test
    void 잘못된이메일2Join() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfaq@wqwercs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member));

        //then
        assertThat(e.getMessage()).isEqualTo("잘못된 이메일 형식입니다.");
    }

    @Test
    void 중복IdJoin() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("홍길동");
        member.setNickname("고길동");
        member.setEmail("asdfaqw@qwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        Member member1 = new Member();
        member1.setId("abcdefghi");
        member1.setPw("abcd1234!@#");
        member1.setName("고길동");
        member1.setNickname("전우치");
        member1.setEmail("asd@qwer.cs");
        member1.setPhone("01099999999");
        member1.setAddr1("서울시 송파구 올림픽로300");
        member1.setAddr2("시그니엘 호텔 999호");
        member1.setPoint(0);
        memberService.join(member);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member1));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }

    @Test
    void 중복NicknameJoin() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asdfaqw@qwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        Member member1 = new Member();
        member1.setId("asdqwer");
        member1.setPw("abcd1234!@#");
        member1.setName("고길동");
        member1.setNickname("전우치");
        member1.setEmail("asd@qwer.cs");
        member1.setPhone("01099999999");
        member1.setAddr1("서울시 송파구 올림픽로300");
        member1.setAddr2("시그니엘 호텔 999호");
        member1.setPoint(0);
        memberService.join(member);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member1));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 닉네임입니다.");
    }

    @Test
    void 중복EmailJoin() {
        //given
        Member member = new Member();
        member.setId("abcdefghi");
        member.setPw("abcd1234!@#");
        member.setName("홍길동");
        member.setNickname("전우치");
        member.setEmail("asd@qwer.cs");
        member.setPhone("01099999999");
        member.setAddr1("서울시 송파구 올림픽로300");
        member.setAddr2("시그니엘 호텔 999호");
        member.setPoint(0);

        Member member1 = new Member();
        member1.setId("asdqwer");
        member1.setPw("abcd1234!@#");
        member1.setName("고길동");
        member1.setNickname("홍길동");
        member1.setPhone("01099999999");
        member1.setEmail("asd@qwer.cs");
        member1.setAddr1("서울시 송파구 올림픽로300");
        member1.setAddr2("시그니엘 호텔 999호");
        member1.setPoint(0);
        memberService.join(member);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member1));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일입니다.");
    }


}