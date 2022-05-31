package skane.skaneshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import skane.skaneshop.board.infra.ItemRepository;
import skane.skaneshop.login.dto.request.Member;
import skane.skaneshop.login.infrastructure.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {


        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        memberRepository.save(member);
    }

}