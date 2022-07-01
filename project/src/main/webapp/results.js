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

/**
 * Adds a random greeting to the page.
 */

function collapsibleEffect(collapsible) {
    explanation = collapsible.nextElementSibling;
    if (explanation.style.maxHeight) {
        explanation.style.maxHeight = null;
        explanation.style.padding = null;
        explanation.style.border = null;
    }
    else {
        explanation.style.maxHeight = explanation.scrollHeight + "px";
        explanation.style.padding = "1rem";
        explanation.style.border = "1px solid black";
    }

}
function addCollapsibleEffect() {

    let itemCollapsibles = document.getElementsByClassName("item-collapsible");

    for (let i = 0; i < itemCollapsibles.length; i++) {
        
        let collapsible = itemCollapsibles[i];
        collapsible.addEventListener("click", () => {collapsibleEffect(collapsible)});

        console.log(i);
    }
}

window.onload = addCollapsibleEffect;