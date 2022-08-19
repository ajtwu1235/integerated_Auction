package skane.skaneshop.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import skane.skaneshop.Auction.application.AuctionService;
import skane.skaneshop.Auction.infra.AuctionRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class AuctionTest {

    @Autowired
    AuctionService auctionService;

    @Autowired
    AuctionRepository auctionRepository;

    @Test
    public void auction(){

        Auction auction = new Auction();

        auction.setAuctionNumber(1L);

        auctionService.createAuction(auction);

        Optional<Auction> byId = auctionRepository.findById(1L);

        org.assertj.core.api.Assertions.assertThat(auction.getAuctionNumber()).isEqualTo(byId.get().getAuctionNumber());

    }
}