package com.kpe.po;

import lombok.Data;

import javax.persistence.*;

/**
 * @description: 订单物品
 * @author: LCN
 * @date: 2019-10-31 17:28
 */

@Data
@Entity
@Table(name= "t_item")
public class ItemPO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private int itemId;

  @Column(name = "sku")
  private String sku;

  @Column(name = "description")
  private String description;

  @Column(name = "description_origin_language")
  private String descriptionOriginLanguage;

  @Column(name = "description_destination_language")
  private String descriptionDestinationLanguage;

  @Column(name = "category")
  private String category;

  @Column(name = "unit_price")
  private int unitPrice;

  @Column(name = "currency")
  private String currency;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "country_of_origin")
  private String countryOfOrigin;

  @Column(name = "hs_code")
  private String hsCode;

  @Column(name = "brand")
  private String brand;

  @Column(name = "remark")
  private String remark;

}
