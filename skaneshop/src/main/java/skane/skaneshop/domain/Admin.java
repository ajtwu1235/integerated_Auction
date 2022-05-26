package skane.skaneshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.Generated;
import lombok.Getter;

@Entity
@Getter
public class Admin {
  
  @Id @Generated
  @Column(name = "admin_id")
  private Long adminNumber;

  private String id;

  private String password;

  @Enumerated(EnumType.STRING)
  private ManagementStatus managementStatus;
}
