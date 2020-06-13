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

let slideIndex = 0;

let sizeStateBig = false;

// Next/previous controls
function plusSlides(n) {
  const slides = document.getElementsByClassName("mySlides");

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

function showSlides() {
  const slides = document.getElementsByClassName("mySlides");
  const dots = document.getElementsByClassName("dot");

  for(slide of slides)
    slide.style.display = 'none';

  slideIndex = slideIndex % slides.length; 
  
  for(dot of dots)
    dot.className = dot.className.replace(" active","");
  
  slides[slideIndex].style.display = "block";  
  dots[slideIndex].className += " active";
}

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
function changeSizeState(photo)
{
    sizeStateBig = !sizeStateBig;
    if(sizeStateBig)
        resizePhoto(photo,"75vw");
    else
        resizePhoto(photo,"60vw");
}
async function getData(path) {
  // The fetch() function returns response object, and is asynchronous.
  const response = await fetch(path);
  //the text() function gets the text from the response asynchronously
  const data = await response.text();
  // When the request is complete return text from response
  return data;
}
async function include(path,extension)
{
    const element = document.getElementById(`include-${path}`);
    if(element)
    {
        const header = await getData(`/${path}${extension}`);
        element.innerHTML = header+'\n'+element.innerHTML;
    }
}
async function includeHTML(fileName)
{
    include(fileName,".html");
}
async function includeDynamicHTML(fileName)
{
    include(fileName,"");
}

document.addEventListener('DOMContentLoaded',() => {
    includeHTML('navigation');
    includeHTML('contact');
    includeDynamicHTML('data');
    getData('/data').then((data) => {
        console.log(data);
    });
})