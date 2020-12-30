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

/** Fetches stats from the server and adds them to the page. */
async function getServerStats() {
  const responseFromServer = await fetch('/server-stats');
  // The json() function returns an object that contains fields that we can
  // reference to create HTML.
  const stats = await responseFromServer.json();

  const statsListElement = document.getElementById('server-stats-container');
  statsListElement.innerHTML = '';

  statsListElement.appendChild(
      createListElement('Start time: ' + stats.startTime));
  statsListElement.appendChild(
      createListElement('Current time: ' + stats.currentTime));
  statsListElement.appendChild(
      createListElement('Max memory: ' + stats.maxMemory));
  statsListElement.appendChild(
      createListElement('Used memory: ' + stats.usedMemory));
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
