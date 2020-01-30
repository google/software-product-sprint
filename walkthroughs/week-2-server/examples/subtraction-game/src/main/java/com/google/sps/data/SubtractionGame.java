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

package com.google.sps.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the subtraction game, where players take turns subtracting from 21 to reach 0.
 *
 * <p>Note: The private variables in this class are converted into JSON.
 */
public class SubtractionGame {

  /** List of descriptions of turns, e.g. "Player 1 took 3. New total: 18" */
  private final List<String> history = new ArrayList<>();

  /** The total of the current turn. */
  private int currentTotal = 21;

  /** Whether this game has ended, i.e. one of the players reached 0. */
  private boolean gameOver = false;

  /** Returns whether this game has ended. */
  public boolean isGameOver() {
    return gameOver;
  }

  /** Takes the player's turn, subtracting `playerChoice` from the total. */
  public void takePlayerTurn(int playerChoice) {
    currentTotal -= playerChoice;
    if (currentTotal < 0) {
      currentTotal = 0;
    }
    logMove("Player 1", playerChoice, currentTotal);

    if (currentTotal == 0) {
      // Player 1 won
      history.add("Player 1 won!");
      history.add("I want a rematch!");
      gameOver = true;
    } else {
      takeComputerTurn();
    }
  }

  private void takeComputerTurn() {
    int computerChoice;
    if (currentTotal <= 3) {
      // If the current total is 1, 2, or 3, then the computer can win this turn
      // by subtracting the current total.
      computerChoice = currentTotal;
    } else {
      // The computer can't win this turn,
      // so generate a random number between 1 and 3 inclusive.
      computerChoice = 1 + (int) (Math.random() * 3);
    }

    currentTotal -= computerChoice;
    logMove("Computer", computerChoice, currentTotal);

    if (currentTotal == 0) {
      history.add("Computer won!");
      history.add("Want a rematch?");
      gameOver = true;
    }
  }

  private void logMove(String player, int choice, int newTotal) {
    history.add(player + " subtracted " + choice);
    history.add("New total: " + newTotal);
  }
}
