package ragna.cqrs.query.model;

import lombok.Data;

@Data
public class Item {
  private String label;
  private double price;
  private int quantity;
}
