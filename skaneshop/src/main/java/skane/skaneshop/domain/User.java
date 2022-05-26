package skane.skaneshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_number")
  private Long userNumber;

  private String id;

  private String password;

  private String name;

  private int age;

  @Enumerated(EnumType.STRING)
  private ManagementStatus managementStatus;

  @Enumerated(EnumType.STRING)
  private Gender gender; // (man,woman)

}
