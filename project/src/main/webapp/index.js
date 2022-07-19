//So it loads the majors from Datastore when the page loads
//TODO: Look if there's a better event to listen than DOMContentLoaded
//TODO: Maybe this shouldn't be an async function
document.addEventListener('DOMContentLoaded', loadMajors, false);

//TODO: Maybe this shouldn't be an async function?
async function loadMajors(){
    const responseFromServer = await fetch('/get-majors')
    const textFromResponse = await responseFromServer.text()
    const container = document.getElementById("major")
    container.innerHTML = textFromResponse
}