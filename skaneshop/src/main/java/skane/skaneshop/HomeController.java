package skane.skaneshop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import skane.skaneshop.board.dto.request.Item;
import skane.skaneshop.board.infra.ItemRepository;
import skane.skaneshop.login.application.ArgumentResolver.Login;
import skane.skaneshop.login.dto.request.Member;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemRepository itemRepository;


    @GetMapping("/")
    public String homeArgumentResolver(@Login Member loginMember, Model model) {

        if (loginMember==null){
            //메인화면 스킨만 적용해둠
            return "skone_home";
        }

        List<Item> items = itemRepository.findAll();

        for (Item item : items) {
            log.info(item.getItemName());
        }


        model.addAttribute("items",items);
        model.addAttribute("member",loginMember);
        //로그인 완료된 페이지가 따로 하나 필요할듯한데 아직 없어서 로그인 완료도 메인화면페이지로해둠.
        return "skone_home";
    }
}