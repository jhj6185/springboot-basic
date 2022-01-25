package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
        //resources templetes 폴더에 있는 뷰네임.html을 찾아줌
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody
    public String helloSpring(@RequestParam("name") String name){
        return "hello "+name; //"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody //Responsebody라는 annotation이 있으면 데이터를
    // 컴퓨터는 그냥 데이터 그 자체로 넘겨야겠구나 라고 생각함(viewResolver 대신 HttpMessageConverter동작)
    //객체면 JsonConverter, 문자열이면 StringConverter가 동작함
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //ctrl+shift+enter 하면 자동완성해줌
        hello.setName(name);
        return hello; //json 방식으로 열린다.(객체를 만들어서 객체로 보내줘서 그런듯?)
        //객체로 보내면 무조건 json 으로 되고, 요즘은 json만 쓰기때문에 json을 쓰는게 맞음
    }
    //API는 객체를 반환해주는걸 API라고 함

    static class Hello{
        private String name;

        //property 접근방식 = getter setter
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
