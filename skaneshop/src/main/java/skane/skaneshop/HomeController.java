package skane.skaneshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import skane.skaneshop.login.application.ArgumentResolver.Login;
import skane.skaneshop.login.dto.request.Member;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String homeArgumentResolver(@Login Member loginMember,
                                         HttpServletRequest request, Model model) {

        if (loginMember==null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "members/loginHome";
    }
}