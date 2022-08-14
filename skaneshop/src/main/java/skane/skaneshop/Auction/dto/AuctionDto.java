package skane.skaneshop.Auction.dto;

import lombok.Data;
import skane.skaneshop.domain.Auction;
import skane.skaneshop.domain.Category;
import skane.skaneshop.domain.Option;

@Data
public class AuctionDto {

    private Long auctionId;
    private Category category;
    private Option option;
    private String ItemName;
    private String PostName;

    public AuctionDto(Auction auction){
        this.auctionId =auction.getAuctionNumber();
        this.category = auction.getCategory();
        this.option = auction.getOption();
        this.ItemName = auction.getPostBordNumber().getProductNumber().getName();
        this.PostName = auction.getPostBordNumber().getName();
    }

}
