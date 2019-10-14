package controller;

import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import model.Model;

// http://localhost:7000/plus?n1=4&n2=1


public class Controller {

  private Javalin app = Javalin.create().start(7000);
  private Model model = new Model();
  private int firstNumber;
  private int secondNumber;


  public static void main(String[] args) {
    Controller controller = new Controller();
    controller.get("plus");
    controller.get("minus");
    controller.get("times");
    controller.get("divide");
  }

  public void get(String operator) {
    app.get("/" + operator, ctx -> {
      getParams(ctx);
      String h1 = "";
      int result = model.calculate(firstNumber, secondNumber, operator);
      switch (operator) {
        case "plus":
          h1 = "<h1>The result of your plus operation was: ";
          break;
        case "minus":
          h1 = "<h1>The result of your minus operation was: ";
          break;
        case "times":
          h1 = "<h1>The result of your multiply operation was: ";
          break;
        case "divide":
          h1 = "<h1>The result of your divide operation was: ";
          break;
      }
      String style = "<style>h1 { font-size: 72px; color: green; background: -webkit-linear-gradient(#eee, #333); }</style>";
      ctx.html(style + h1 + result + "</h1><p>Powered by AI &trade;</p>");
    });
  }

  private void getParams(Context ctx) {
    try {
      String rawFirstNumber = ctx.queryParam("n1");
      if (rawFirstNumber == null) throw new BadRequestResponse("n1 cannot be null");
      firstNumber = Integer.parseInt(rawFirstNumber);
      String rawSecondNumber = ctx.queryParam("n2");
      if (rawSecondNumber == null) throw new BadRequestResponse("n1 cannot be null");
      secondNumber = Integer.parseInt(rawSecondNumber);
    } catch (NumberFormatException e) {
      throw new BadRequestResponse("Invalid number parameters");
    }
  }
}

