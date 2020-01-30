// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.sps.data.SubtractionGame;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that encapsulates the subtraction game. */
@WebServlet("/subtraction-game")
public final class SubtractionServlet extends HttpServlet {

  private SubtractionGame game = new SubtractionGame();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    String json = new Gson().toJson(game);
    response.getWriter().println(json);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // If the user sends another POST request after the game is over, then start a new game.
    if (game.isGameOver()) {
      game = new SubtractionGame();
    }

    // Get the input from the form.
    int playerChoice = getPlayerChoice(request);
    if (playerChoice == -1) {
      response.setContentType("text/html");
      response.getWriter().println("Please enter an integer between 1 and 3.");
      return;
    }

    game.takePlayerTurn(playerChoice);

    // Redirect back to the HTML page.
    response.sendRedirect("/index.html");
  }

  /** Returns the choice entered by the player, or -1 if the choice was invalid. */
  private int getPlayerChoice(HttpServletRequest request) {
    // Get the input from the form.
    String playerChoiceString = request.getParameter("player-choice");

    // Convert the input to an int.
    int playerChoice;
    try {
      playerChoice = Integer.parseInt(playerChoiceString);
    } catch (NumberFormatException e) {
      System.err.println("Could not convert to int: " + playerChoiceString);
      return -1;
    }

    // Check that the input is between 1 and 3.
    if (playerChoice < 1 || playerChoice > 3) {
      System.err.println("Player choice is out of range: " + playerChoiceString);
      return -1;
    }

    return playerChoice;
  }
}
