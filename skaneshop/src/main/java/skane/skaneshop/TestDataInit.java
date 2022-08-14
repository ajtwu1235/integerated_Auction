package skane.skaneshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import skane.skaneshop.Auction.infra.AuctionRepository;
import skane.skaneshop.Auction.infra.AuctionTestRepository;
import skane.skaneshop.board.infra.ItemRepository;
import skane.skaneshop.domain.*;
import skane.skaneshop.login.dto.request.Member;
import skane.skaneshop.login.infrastructure.MemberRepository;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final AuctionTestRepository auctionTestRepository;
    private final AuctionRepository auctionRepository;

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


    @PostConstruct
    public void Auction_init(){

        // 예시 상품 2개 생성
        Product product = new Product(1L,null,"컴퓨터",100000
                , Category.ELECTRONIC_PRODUCTS,true, SaleOption.AUCTION,Option.POST,null);
        Product product2 = new Product(2L,null,"스마트 자바 프로그래밍",15400
                , Category.BOOKS,true, SaleOption.AUCTION,Option.POST,null);

        //에시 게시물 2개 생성
        PostBoard postBoard = new PostBoard(1L,product,"컴퓨터 판매","",Status.RESERVED, Timestamp.valueOf(LocalDateTime.now()));
        PostBoard postBoard2 = new PostBoard(2L,product2,"자바 기본서 팝니다","",Status.RESERVED, Timestamp.valueOf(LocalDateTime.now()));

        //예시 옥션 2개생성
        Auction auction = new Auction();
        auction.setPostBordNumber(postBoard);
        auction.setCategory(Category.ELECTRONIC_PRODUCTS);
        auction.setOption(Option.AUCTION);

        Auction auction2 = new Auction();
        auction2.setPostBordNumber(postBoard2);
        auction2.setCategory(Category.BOOKS);
        auction2.setOption(Option.AUCTION);

        auctionTestRepository.save(auction);
        auctionTestRepository.save(auction2);

        System.out.println("옥션 데이터 추가");

    }

}