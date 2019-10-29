package com.kpe.po;

public class OrderPO {

  private Integer id;

  private String title;

  private String url;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "OrderPO{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", url='" + url + '\'' +
      '}';
  }

  public OrderPO() {
  }

  public OrderPO(Integer id, String title, String url) {
    this.id = id;
    this.title = title;
    this.url = url;
  }
}
