package io.javabrains.springbootstarter.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class loginController {
    
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
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
   
    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    } 
}