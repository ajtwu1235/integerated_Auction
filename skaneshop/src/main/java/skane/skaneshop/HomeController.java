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
            //메인화면 스킨만 적용해둠
            return "skone_home";
        }
        model.addAttribute("member",loginMember);
        //로그인 완료된 페이지가 따로 하나 필요할듯한데 아직 없어서 로그인 완료도 메인화면페이지로해둠.
        return "skone_home";
    }
}