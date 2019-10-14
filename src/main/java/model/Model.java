package model;

public class Model {

  public int calculate(int number1, int number2, String operator) {
    switch (operator) {
      case "plus":
        return number1 + number2;
      case "minus":
        return number1 - number2;
      case "times":
        return number1 * number2;
      case "divide":
        return number1 / number2;
      default:
        return 0;
    }
  }
}
