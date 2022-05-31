package skane.skaneshop.login.application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skane.skaneshop.login.dto.request.Member;
import skane.skaneshop.login.infrastructure.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password){
//        Optional<Member> findMember =memberRepository.findByLoginId(loginId);
//        Member member = findMember.get();
//        if()

        return memberRepository.findByLoginId(loginId)
                .filter(m->m.getPassword().equals(password))
                .orElse(null);
    }
}