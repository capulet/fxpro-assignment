package com.landscape.model;

public class Tuple<T1, T2> {

  public T1 _1;
  public T2 _2;

  public Tuple(T1 object1, T2 object2) {
    this._1 = object1;
    this._2 = object2;
  }

  @Override
  public boolean equals(Object object) {

    if (this == object)
      return true;

    if (object == null || getClass() != object.getClass())
      return false;

    Tuple<?, ?> tuple = (Tuple<?, ?>) object;

    if (!_1.equals(tuple._1))
      return false;

    return _2.equals(tuple._2);
  }

  @Override
  public int hashCode() {

    int result = _1.hashCode();
    result = 31 * result + _2.hashCode();

    return result;
  }
}
