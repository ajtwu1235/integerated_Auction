package skane.skaneshop.Auction.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skane.skaneshop.Auction.infra.AuctionRepository;
import skane.skaneshop.Auction.infra.BidInfoRepository;
import skane.skaneshop.domain.Auction;
import skane.skaneshop.domain.BidInfo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final BidInfoRepository bidInfoRepository;

    public void createAuction(Auction auction){

        auctionRepository.save(auction);
    }

    /**
     *
     * @param ,user
     * 1000원씩 고정 입찰
     */
    public void fixed_bid(String user ,Auction auction){

        BidInfo bidInfo = new BidInfo();

        bidInfo.setAuction(auction);
        bidInfo.setUserName(user);
        bidInfo.setBid_Price(get_First_Bid()+1000);

        bidInfoRepository.save(bidInfo);

    }

    public void fixed_bidV2(String user ,Auction auction){

        BidInfo bidInfo = new BidInfo();

        // BidInfo -> Auction 연결
        bidInfo.setAuction(auction);
        bidInfo.setUserName(user);
        bidInfo.setBid_Price(get_First_Bid()+1000);
        bidInfoRepository.save(bidInfo);

        // Auction -> BidInfo  연결   양방향 안쓸거면 이 코드 삭제 ㄱㄱ
        auction.addBidInfos(bidInfo);

        auctionRepository.save(auction);
    }

    /**
     * 1등 입찰 값을 불러오는 메소드, 2등 입찰 값을 불러오는 메소드
     */

    public int get_First_Bid(){
        List<BidInfo> all = bidInfoRepository.findAll();

        if(all.size()==0){
            return 0;
        }

        Collections.sort(all,(a,b)->b.getBid_Price()-a.getBid_Price());

        return all.get(0).getBid_Price();
    }

    public int get_Second_Bid(){
        List<BidInfo> all = bidInfoRepository.findAll();

        if(all.size()>2){
            return 0;
        }

        Collections.sort(all,(a,b)->b.getBid_Price()-a.getBid_Price());

        return all.get(1).getBid_Price();
    }

    /**
     *
     * @param time

     * 현재 시간을 받아서 + 7일 을 반환하는 메소드드
    */
    public LocalDateTime setLeftTime(LocalDateTime time){

        return time.plusDays(7L);
    }


//    //입찰 메소드 (자유입찰?)
//    public void bid(Long Auction_num, int price ){
//
//        Auction auction = auctionTestRepository.findById(Auction_num);
//
//
//    }
//
//    //1000원 입찰 고정
//    public void bid_price_fix(Long Auction_num) {
//        Auction auction = auctionTestRepository.findById(Auction_num);
//
//    }



}
