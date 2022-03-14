package ragna.cqrs.command.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Price {
  CHEAP("cheap"),
  AFFORDABLE("affordable"),
  EXPENSIVE("expensive");

  @JsonValue private final String label;

  Price(String label) {
    this.label = label;
  }
}
