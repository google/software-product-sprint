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
 * Adds a slideshow to the page.
 */
//slideIndex is the variable to hold the index of the slide currently on display
let slideIndex = 0;
//if ShaowLargeImages is true then the images in the Gallery will have their width set to 75vw
//if it is false then the gallery images will have a width of 60vw
let showLargeImages = false;

// Next/previous controls
function plusSlides(n) {
  const slides = document.getElementsByClassName("mySlides");
  //wrap slides back around to max if it would be decremented to -1
  if(slideIndex <= 0 && n == -1)
    slideIndex = slides.length - 1;
  else
    slideIndex += n;
  showSlides();
}

// Thumbnail image controls
function currentSlide(n) {
    slideIndex = n
    showSlides();
}
//function that hides any slides that aren't at the slideIndex in the list of mySlides,
//and shows the selected slide with the correct size according to the showLargeImages variable
function showSlides() {
  const slides = document.getElementsByClassName("mySlides");
  const dots = document.getElementsByClassName("dot");

  //hide any unselected slides
  for(slide of slides)
    slide.style.display = 'none';

  //ensure slide index is not greater than the max index
  slideIndex = slideIndex % slides.length;

  //resize photo according to the showLargeImages global variable
  resizePhotoAuto(slides[slideIndex].getElementsByTagName('img')[0]);

  //de-highlight all dots
  for(dot of dots)
    dot.className = dot.className.replace(" active","");

  //Make current slide show with block formatting
  slides[slideIndex].style.display = "block";
  
  //highlight dot that represents selected image on screen
  dots[slideIndex].className += " active";
}
//function to be run in setTimeout to make slide show auto increment
function showSlidesAuto()
{
    slideIndex++;
    showSlides();
}
//change width css attribute
function resizePhoto(photo,size)
{
    photo.style.width = size;
}
//Change witdh attribute of an object according to showLargeImages variable
function resizePhotoAuto(photo)
{
  if(showLargeImages)
    resizePhoto(photo,"75vw");
  else
    resizePhoto(photo,"60vw");
}
//changes state of showLargeImages variable then 
//resize currently displayed image according to new state
function changeSizeState(photo){
  showLargeImages = !showLargeImages; 
  resizePhotoAuto(photo);
}
async function getDataText(path) {
  // The fetch() function returns response object, and is asynchronous.
  const response = await fetch(path);
  //the text() function gets the text from the response asynchronously
  const data = await response.text();
  // When the request is complete return text from response
  return data;
}
async function getDataJson(path) {
  // The fetch() function returns response object, and is asynchronous.
  const response = await fetch(path);
  //the text() function gets the text from the response asynchronously
  const data = await response.json();
  // When the request is complete return text from response
  return data;
}
async function include(path,extension){
    const element = document.getElementById(`include-${path}`);
    if(element)
    {
        const header = await getDataText(`/${path}${extension}`);
        element.innerHTML = header+'\n'+element.innerHTML;
    }
}
async function includeHTML(fileName){
    include(fileName,".html");
}
async function includeDynamicHTML(fileName){
    include(fileName,"");
}
function jsListToHtml(list){
    let html = "<ul>";
    for(element of list)
    {
      html += `<li>${element.message}</li>`;
    }
    html += "</ul>";
    return html
}
async function updateDataInclude(){
    const path = 'data';
    const element = document.getElementById(`include-${path}`);
    if(element)
    {
        const data = await getDataJson(`/${path}`);
        element.innerHTML = jsListToHtml(data)+'\n'+element.innerHTML;
    }
}
document.addEventListener('DOMContentLoaded',() => {
    includeHTML('partials/navigation');
    includeHTML('partials/contact');
})