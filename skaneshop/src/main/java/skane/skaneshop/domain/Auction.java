package skane.skaneshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

  private int bid_price;

  private Timestamp left_time;

}
