package skane.skaneshop.Auction.dto;

import lombok.Data;
import skane.skaneshop.Auction.application.AuctionService;
import skane.skaneshop.domain.Auction;
import skane.skaneshop.domain.BidInfo;
import skane.skaneshop.domain.Category;
import skane.skaneshop.domain.Option;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AuctionDto {

    private Long auctionId;
    private Category category;
    private Option option;
    private String ItemName;
    private String PostName;
    private List<AuctionRequestDto> bidInfo;
    private LocalDateTime left_time;


    public AuctionDto(Auction auction){
        this.auctionId =auction.getAuctionNumber();
        this.category = auction.getCategory();
        this.option = auction.getOption();
        this.ItemName = auction.getPostBordNumber().getProductNumber().getName();
        this.PostName = auction.getPostBordNumber().getName();
        this.bidInfo = auction.getBidInfos().stream()
                .map(o->new AuctionRequestDto(o.getAuction().getAuctionNumber(),o.getUserName(),o.getBid_Price()))
                .collect(Collectors.toList());
        this.left_time=auction.getLeft_time();
    }

}
