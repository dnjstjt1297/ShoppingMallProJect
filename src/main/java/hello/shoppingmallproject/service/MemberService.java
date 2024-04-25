package hello.shoppingmallproject.service;



import hello.shoppingmallproject.domian.Member;
import hello.shoppingmallproject.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     */
    public Long join(Member member){

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getIdx();
    }

    private void validateDuplicateMember(Member member) {

        if(member.getId().equals("")){
            throw new IllegalStateException("아이디는 필수 항목입니다.");
        }
        if(member.getPw().equals("")){
            throw new IllegalStateException("비밀번호는 필수 항목입니다.");
        }
        if(member.getName().equals("")){
            throw new IllegalStateException("이름은 필수 항목입니다.");
        }
        if(member.getNickname().equals("")){
            throw new IllegalStateException("닉네임은 필수 항목입니다.");
        }
        if(member.getPhone().equals("")){
            throw new IllegalStateException("핸드폰 번호는 필수 항목입니다.");
        }
        if(member.getEmail().equals("")){
            throw new IllegalStateException("이메일은 필수 항목입니다.");
        }
        if(member.getAddr1().equals("")){
            throw new IllegalStateException("주소는 필수 항목입니다.");
        }



        for(int i=0; i<member.getId().length();i++){
            if(!((member.getId().charAt(i)>='a'&&member.getId().charAt(i)<='z')||(member.getId().charAt(i)>='0'&& member.getId().charAt(i)<='9'))){
                throw new IllegalStateException("아이디는 영어소문자와 숫자의 조합으로 입력하세요.");
            }
        }

        boolean is_P1 = false;
        boolean is_P2 = false;
        boolean is_P3 = false;

        for(int i=0; i<member.getPw().length();i++){
            if(!(member.getPw().charAt(i)>=33&&member.getPw().charAt(i)<=126)){
                throw new IllegalStateException("잘못된 비밀번호 형식입니다.");
            }
            if(member.getPw().charAt(i)>='0'&&member.getPw().charAt(i)<='9'){
                is_P1 = true;
            }
            else if(member.getPw().charAt(i)>='a'&&member.getPw().charAt(i)<='z'){
                is_P2 = true;
            }
            else{
                is_P3 = true;
            }
        }
        if(!is_P1||!is_P2||!is_P3){
            throw new IllegalStateException("비밀번호는 숫자,영어,특수문자를 모두 사용해야합니다.");
        }


        for(int i = 0; i<member.getPhone().length(); i++){
            if(!(member.getPhone().charAt(i)>='0'&&member.getPhone().charAt(i)<='9')){
                throw new IllegalStateException("잘못된 핸드폰 번호입니다. -를 제외하고 입력하세요.");
            }
        }

        boolean is_E1 = false;
        boolean is_E2 = false;
        for(int i=0; i<member.getEmail().length();i++){
            if(member.getEmail().charAt(i)=='@'){
                is_E1=true;
            }
            if(is_E1){
                if(member.getEmail().charAt(i)=='.'){
                    is_E2=true;
                }
            }
        }
        if(!is_E1||!is_E2){
            throw new IllegalStateException("잘못된 이메일 형식입니다.");
        }



        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
        memberRepository.findByNickName(member.getNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 닉네임입니다.");
                });
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });



    }
    public void certificationMember(Member member){
        //이메일 인증
        //휴대폰 인증
    }



    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberIdx){
        return memberRepository.findByIdx(memberIdx);
    }

}
