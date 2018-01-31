package io.javabrains.springbootstarter.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class loginController {
   
    @RequestMapping(value={"/","home"})
    public String home(){
    	System.out.println("called home");
        return "home";
    }
    
    @RequestMapping(value={"/signup"})
    public String signup(){
    	System.out.println("called signup");
        return "signup";
    }
    
    @RequestMapping(value={"/signout"})
    public String signout(){
    	System.out.println("called signout");
        return "home";
    }
    
    @RequestMapping(value={"/signin"})
    public String signin(){
    	System.out.println("called signin");
        return "home";
    }
   
    @RequestMapping(value={"/test"})
    public String test(){
    	System.out.println("called test");
        return "Pois";
    }
    
    @RequestMapping(value={"/index"})
    public String index(){
    	System.out.println("called index");
        return "index";
    }
    
    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    } 
}