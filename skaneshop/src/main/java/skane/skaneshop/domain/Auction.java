package skane.skaneshop.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
//잠깐 Setter 열어둠
@Setter
public class Auction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="auction_id")
  private Long auctionNumber;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="post_bord_id")
  private PostBoard postBordNumber;

  @Enumerated(EnumType.STRING)
  private Category category;

  @Enumerated(EnumType.STRING)
  private Option option;


  @OneToMany(mappedBy = "auction")
  private List<BidInfo> bidInfos = new ArrayList<>();

  private LocalDateTime left_time;

  //양방향 편의메소드  Auction-> BidInfo
  public void addBidInfos(BidInfo bidInfo){

    System.out.println("양방향 편의메소드  Auction-> BidInfo");
    this.bidInfos.add(bidInfo);
    if(bidInfo.getAuction()!=this){
      bidInfo.setAuction(this);
    }

  }

}
