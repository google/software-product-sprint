// Copyright 2020 Google LLC
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

/*** Landing Page ***/
/* Enter workout into the selected workout line by pressing on button */
function clickWorkout(x) {
    document.getElementById("workout-select").value = x;
}

/*** Playlist Page ***/


/*** Side Navigation Menu ***/
/* Set the width of the side navigation to 250px and the left margin of the page content to 250px */

function openMenu() {
    document.getElementById("side-menu").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0 */

function closeMenu() {
    document.getElementById("side-menu").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
}

/* Activate the dark-mode class in the style sheet on the body */
function darkMode() {
    var x = document.body;
    x.classList.toggle("dark-mode");
}

const _getToken = async () => {

    const result = await fetch('https://accounts.spotify.com/api/token', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Basic ' + btoa(clientId + ':' + clientSecret)

        },
        body: 'grant_type=client_credentials'
    });

    const data = await result.json();
    return data.access_token;
}
function fetchId() {
var bpmToPlaylistId = { '90': '5fQuCLAEcNtHbFTdyiEJDd', '120' : '0StaxvjifcoNPeIM8stp4p', '128': '28p4zE67Svf2S1OIZlp0rh', '135' : '26ZtghULwqbNzZK3yi3Txx', '150': '37i9dQZF1DX0hWmn8d5pRe' };
var workoutNameToBpm = { 'Pilates': '90', 'Jogging': '120','Sit-Ups': '128', 'Aerobics' : '135', 'Treadmill': '150',  };
const workoutElement = document.getElementById('workout-select');
const workoutName = workoutElement.value;
const bpm = workoutNameToBpm[workoutName];
const playlistId = bpmToPlaylistId[bpm];
//const playlistElement = document.getElementById('playlist');
//playlistElement.innerText = response.name + response.url;

const accessToken = 'BQDDPQbWkQ16NvNhG4pOvjQvsWAQL_Y6PSsj2E1KviXRQqStPW8Lh37fbL7qLelKCO6Tg5eeIRQGcavZVSfNz-oYYzLob4sULCfpoXYt38HHuWruOfgEcZTgdx__2_6yoM02rNeZhimI1c-fjNN3zg6q';


    fetch('https://api.spotify.com/v1/playlists/' + playlistId, {
        method: 'GET', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    })

    .then((response) => {
        console.log(response.json().then(
            (data) => { console.log(data) }
        ));
    });
}