package skane.skaneshop.Auction.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import skane.skaneshop.domain.Auction;


/**
 *  입찰정보를 담는 RequestDto
 */
@Data
public class AuctionRequestDto {
    private Long auctionId;
    private String userName;
    private int bid_price;

    @JsonCreator
    public AuctionRequestDto(@JsonProperty("auctionId") Long auctionId ,
                             @JsonProperty("userName") String userName ,
                             @JsonProperty("bid_price") int bid_price) {
        this.auctionId = auctionId;
        this.userName =userName;
        this.bid_price = bid_price;
    }
}
