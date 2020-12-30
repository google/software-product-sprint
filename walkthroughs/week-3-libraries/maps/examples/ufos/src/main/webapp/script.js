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

/** Fetches UFO sightings data from the server and displays it in a map. */
function createUfoSightingsMap() {
  fetch('/ufo-data').then(response => response.json()).then((ufoSightings) => {
    const map = new google.maps.Map(
        document.getElementById('map'),
        {center: {lat: 35.78613674, lng: -119.4491591}, zoom: 7});

    ufoSightings.forEach((ufoSighting) => {
      new google.maps.Marker(
          {position: {lat: ufoSighting.lat, lng: ufoSighting.lng}, map: map});
    });
  });
}
