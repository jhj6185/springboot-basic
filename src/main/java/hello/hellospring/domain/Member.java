package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //identity 전략이라고 db가 알아서 insert될때마다 id값 주는거를 말함
    private Long id; //시스템에 저장할 id
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
