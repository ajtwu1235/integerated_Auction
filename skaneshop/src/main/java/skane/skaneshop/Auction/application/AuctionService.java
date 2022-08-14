package skane.skaneshop.Auction.application;

import org.springframework.stereotype.Service;
import skane.skaneshop.Auction.dto.AuctionDto;
import skane.skaneshop.Auction.infra.AuctionTestRepository;
import skane.skaneshop.domain.Auction;

@Service
public class AuctionService {

    AuctionTestRepository auctionTestRepository;

    //입찰 메소드 (자유입찰?)
    public void bid(Long Auction_num, int price ){

        Auction auction = auctionTestRepository.findById(Auction_num);

        auction.setBid_price(price);
    }

    //1000원 입찰 고정
    public void bid_price



}
