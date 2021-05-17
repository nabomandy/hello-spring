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

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    /*-> http에서 헤더부와 바디부가 있는데, 그 바디부에 이 데이터를 직접 넣어주겠다는 의미*/
    public String helloString(@RequestParam("name") String name){
        return "hello " +name; //"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    // JSON 방식 키와 벨류라는 단순한 방식
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); //객체 생성
        hello.setName(name);
        return hello; //객체를 넘김
        //객체가 오면 json방식으로 넘긴다는게 기본
        //객체를 반환하고 @ResponseBody 해놓으면 json이 기본 
        
    }

    static class Hello{
        private String name;
        //게터세터는 자바빈 규약, 자바빈 표준방식, 프로퍼티 접근방식
        //private String name;이라서 외부에서 못꺼낸다 그래서
        //라이브러리나 내가 쓸 때 저런 메소드(gettersetter)를 이용해서 접근한다
        public String getName() {
            //꺼낼 때는 getName
            return name;
        }

        public void setName(String name) {
            //넣을 때는 setName
            this.name = name;
        }
    }


}
