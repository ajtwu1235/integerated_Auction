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
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
//잠깐 사용
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="product_id")
  private Long productNumber;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="user_number")
  private User userNumber;

  @Column(name="product_name")
  private String name;

  private int price;

  private Category category;

  private boolean cart;

  @Enumerated(EnumType.STRING)
  private SaleOption saleOption;

  @Enumerated(EnumType.STRING)
  private Option option;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "image_number")
  private Image imageNumber;
}