package skane.skaneshop.domain;

import lombok.Getter;

@Getter
public enum Category {
  CLOTHES("의상"), ELECTRONIC_PRODUCTS("전자제품"), SPORT("스포츠"), BOOKS("책");

  private  final String description;

  Category(String description) {
    this.description = description;
  }
}
