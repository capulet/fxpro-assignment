package com.landscape.model.type;

public enum LandForms {

  HILLS("Hills"), PITS("Pits");

  private String value;

  LandForms(String val) {
    this.value = val;
  }

  public String value() {
    return value;
  }
}
