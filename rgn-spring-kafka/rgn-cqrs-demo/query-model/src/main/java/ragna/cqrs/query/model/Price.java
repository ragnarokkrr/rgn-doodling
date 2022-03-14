package ragna.cqrs.query.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Price {
  CHEAP("cheap"),
  AFFORDABLE("affordable"),
  EXPENSIVE("expensive");

  @JsonValue public final String label;

  Price(String label) {
    this.label = label;
  }
}
