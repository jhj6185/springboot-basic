package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig { //직접 빈 등록해주는 방법
    //불편해보이지만 return 값만 띡 바꾸면되서 편리함이 있음
    private final MemberRepository memberRepository;

    //@Autowired
    public SpringConfig(MemberRepository memberRepository ){
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    /*@Bean
    public TimeTraceAop timeTraceAop(){ //aop는 bean을 만들어주면 특별해진 느낌이라
        //사람들이 config 파일을 보고 아 이렇게 쓰이고 있구나를 바로 알 수 있어서 config에 만드는걸
        //좀 더 선호함 여기다 안쓰려면 TimeTraceAop.java 가서 @Component 라고 써주면됨.
        return new TimeTraceAop();
    }*/
    
    /*@Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }*/
}
