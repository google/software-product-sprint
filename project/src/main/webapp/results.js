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

 /*Changes maxHeight, padding, and border of the explanation to achieve collapsing effect*/
function collapsibleEffect(collapsible) {
    explanation = collapsible.nextElementSibling;
    if (explanation.style.maxHeight) {
        explanation.style.maxHeight = null;
        explanation.style.padding = null;
        explanation.style.border = null;
    }
    else {
        explanation.style.padding = "1rem";
        explanation.style.maxHeight = "calc(" + explanation.scrollHeight + "px + 2rem)";
        explanation.style.border = "1px solid black";
    }

}

/*adds collapsing event to each button*/
function addCollapsibleEffect() {

    let itemCollapsibles = document.getElementsByClassName("item-collapsible");

    for (let i = 0; i < itemCollapsibles.length; i++) {
        
        let collapsible = itemCollapsibles[i];
        collapsible.addEventListener("click", () => {collapsibleEffect(collapsible)});

        console.log(i);
    }
}

//TODO: Maybe this shouldn't be an async function?
async function loadItems(){
    //Get the JSON
    const responseFromServer = await fetch('/get-items')
    const jsonResponse = await responseFromServer.json()
    const generalTitle = document.getElementById("general-title")
    const majorTitle = document.getElementById("major-title")
    generalTitle.innerText = "General College Essentials"
    majorTitle.innerText = "Course-Specific Essentials: " + jsonResponse.major
    
    //Fill with the items:
    const itemTemplate = await fetch('/item_template.html')
    const templateText = await itemTemplate.text()
    //General items:
    const generalItemsHere = document.getElementById("general-item-list")
    for(let i = 0; i < jsonResponse.generalItems.length; i++){
        const item = jsonResponse.generalItems[i]
        generalItemsHere.innerHTML += replaceItemHTML(templateText, "general", item.name, item.explanation);
    }
    //Course-specific items:
    const courseItemsHere = document.getElementById("course-item-list")
    for(let i = 0; i < jsonResponse.majorItems.length; i++){
        const item = jsonResponse.majorItems[i]
        courseItemsHere.innerHTML += replaceItemHTML(templateText, "course", item.name, item.explanation);
    }

    /*Add collapsing effect event to every button as soon as it loads*/
    addCollapsibleEffect()
}

//Gets the HTML that must be put for an item to be displayed
function replaceItemHTML(templateText, itemType, itemName, itemExplanation){
    return templateText.replace("$ITEM_TYPE", itemType)
                       .replace("$ITEM_NAME", itemName)
                       .replace("$ITEM_NAME", itemName)
                       .replace("$ITEM_EXPLANATION", itemExplanation)
}

//So it loads the info from the pertinent major when the page loads
document.addEventListener('DOMContentLoaded', loadItems, false);
