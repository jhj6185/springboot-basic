package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    
    //Autowired는 bean이 관리되고 있을 때에만 동작함= @Service를 붙이던지, 아니면 config파일에서 bean을 생성해주던지..
    @Autowired //생성자를 통해서 Autowired로 주입을 하게되는걸 요즘 제일 많이쓰고 좋음
    public MemberService(MemberRepository memberRepository) {
        //왜냐하면 set으로 주입하면 set하는게 public으로 열려있기때문에 안좋음
        // DI 주입 방법엔 필드, setter, 생성자 주입 이렇게 세가지가 있는데 이 중에 생성자 주입이 젤 좋음
        this.memberRepository = memberRepository; //new로 계속 새로운 객체를 만들어주다보면
        //데이터값이 일치하지 않을 수 있기 때문에 밖에서 안으로 데이터를 집어넣어줄 수 있도록 설계
    }

    //회원가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원은 가입 안된다!
        //어차피 결과값은 optional로 나오니까 바로 .ifPresent붙임
        validateDuplicateMember(member); //중복회원 검증
        //null일 가능성이 있으면 optional로 요즘은 감싸는 추세

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // ifPresent = null이 아니라 어떤 값이 있으면?
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체회원 조회
    public List<Member> findMembers(){
        //보통 서비스 클래스는 비즈니스에 의존적으로 설계하기때문에 비즈니스적인 네임을 써야함
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
