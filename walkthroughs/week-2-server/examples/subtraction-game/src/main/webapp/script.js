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

/**
 * Fetches the current state of the game and builds the UI.
 */
function getSubtractionGame() {
  fetch('/subtraction-game').then(response => response.json()).then((game) => {
    const totalEl = document.getElementById('total');
    if (game.gameOver) {
      // The current game is over, show the total for the next game.
      totalEl.innerText = 'Total: 21';
    } else {
      totalEl.innerText = 'Total: ' + game.currentTotal;
    }

    // Build the list of history entries.
    const historyEl = document.getElementById('history');
    game.history.forEach((line) => {
      historyEl.appendChild(createListElement(line));
    });
  });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
