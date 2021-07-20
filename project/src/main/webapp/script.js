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

/*** Side Navigation Menu ***/
/* Set the width of the side navigation to 250px and the left margin of the page content to 250px */
function openNav() {
    document.getElementById("side-menu").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0 */
function closeNav() {
    document.getElementById("side-menu").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
}

/*** Dark Mode Toggle ***/
function darkMode() {
    var element = document.body;
    element.classList.toggle("dark-mode");
}

const _getToken = async () => {
    //OLD ID's
    const clientId = '65b8fa1fad84447391094d21e2907ffa';
    const clientSecret = '68540d65fa6a48ea8fc69d6096284459';
    
    
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

var bpmToPlaylistId ={'90': '5fQuCLAEcNtHbFTdyiEJDd', '128': '28p4zE67Svf2S1OIZlp0rh', '150': '37i9dQZF1DX0hWmn8d5pRe'};
var wortoutNameToBpm = {'90': 'yoga', '128' : 'abs', '150':'running'};
//workout based on user input?
// var workoutType = window.prompt("Enter your workout Type: ");
// alert("workout: " + workoutType);
const accessToken = 'BQBE8LgaXhAp-aTLdd4cpLuTV85YBhqXVtXqFfg5HB69mIBfSXSBNEqdwjmLnhuiQHc07KbjbhV8tzQXaOEAEJGwdjVRoVyLUu-m8qbzX3d3iuyW-wE0dcoCIRBOqBFghev4cDFqjThqwv4R6A-rMNJ3';
fetch('https://api.spotify.com/v1/playlists/37i9dQZF1DX0hWmn8d5pRe', {
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