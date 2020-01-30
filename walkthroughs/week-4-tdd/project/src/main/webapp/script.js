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
 * Collects the information from the web form and sends the meeting request to
 * the server. Using the response, it lists the options reported by the server.
 */
function sendMeetingRequest() {
  const duration = document.getElementById('duration').value;

  // comma-separated list of names
  const attendeesNamesString = document.getElementById('attendees').value;
  // split it into an array of names
  const attendees = attendeesNamesString.split(/\s*,\s*/);

  // Create the request to send to the server using the data we collected from
  // the web form.
  const meetingRequest = new MeetingRequest(duration, attendees);

  queryServer(meetingRequest).then((timeRanges) => {
    updateResultsOnPage(timeRanges);
  });
}

/**
 * Updates the UI to show the results of a query.
 */
function updateResultsOnPage(timeRanges) {
  const resultsContainer = document.getElementById('results');

  // clear out any old results
  resultsContainer.innerHTML = '';

  // add results to the page
  for (const range of timeRanges) {
    resultsContainer.innerHTML += '<li>' + timeToString(range.getStartTime()) +
        ' - ' + timeToString(range.getEndTime()) + '</li>';
  }
}

/**
 * Sends the meeting request to the server and get back the time ranges.
 */
function queryServer(meetingRequest) {
  const json = JSON.stringify(meetingRequest);
  return fetch('/query', {method: 'POST', body: json})
      .then((response) => {
        return response.json();
      })
      .then((timeRanges) => {
        // Convert the range from a json representation to our TimeRange class.
        const out = [];
        timeRanges.forEach((range) => {
          out.push(new TimeRange(range.start, range.duration));
        });
        return out;
      });
}

/**
 * Converts the total number of minutes since midnight to a string displaying
 * hours and minutes in 24 hour format. For example: "11:32" or "22:14".
 */
function timeToString(totalMinutes) {
  const digitsToString = (digits) => {
    return (digits < 10) ? ('0' + digits) : (digits);
  };

  const hours = Math.floor(totalMinutes / 60);
  const minutes = totalMinutes % 60;

  return digitsToString(hours) + ':' + digitsToString(minutes);
}

/**
 * Request for possible meeting times.
 */
class MeetingRequest {
  constructor(duration, attendees) {
    this.duration = duration;
    this.attendees = attendees;
  }
}

/**
 * Represents a time range. It must mirror what the server has. Ranges has a
 * start time and a duration in minutes. For the start time, 12:00 PM would be
 * 720.
 */
class TimeRange {
  constructor(start, duration) {
    this.start = start;
    this.duration = duration;
  }

  getStartTime() {
    return this.start;
  }

  getEndTime() {
    return this.start + this.duration;
  }
}

/**
 * Get all the events that the server knows about. This will tell us who the
 * server knows about and when they are busy.
 */
function getAllEvents() {
  return fetch('/get-events', {method: 'GET'})
      .then((response) => {
        return response.json();
      })
      .then((events) => {
        return events.map((event) => {
          const time = new TimeRange(event.when.start, event.when.duration);
          return new Event(event.title, time, event.attendees);
        });
      });
}

/**
 * Converts "minutes since midnight" into a JavaScript Date object.
 */
function asDate(minutes) {
  const date = new Date();
  date.setHours(Math.floor(minutes / 60));
  date.setMinutes(minutes % 60);
  return date;
}

/**
 * An event from the server. An event provides the client with information about
 * who is busy at a specific point in time.
 */
class Event {
  /**
   * Create a new event. The title must be a string. The time must be a
   * TimeRange. The attendees must be a collection of strings.
   */
  constructor(title, time, attendees) {
    this.title = title;
    this.time = time;
    this.attendees = new Set(attendees);
  }
}

/**
 * Gets the set of all attendees across all events.
 */
function getUniquePeople(events) {
  // Use a set so that we only include each person once.
  const people = new Set();

  for (const e of events) {
    for (const person of e.attendees) {
      people.add(person);
    }
  }

  return people;
}

/**
 * Initializes and renders the chart at the top of the page, showing who is busy
 * and when.
 */
function initializeChart() {
  const container = document.getElementById('timeline');
  getAllEvents().then((events) => {
    initializeChartWithEvents(container, events);
  });
}

/**
 * Initializes and renders a collection of events as a calendar in the given
 * container.
 */
function initializeChartWithEvents(container, events) {
  const dataTable = new google.visualization.DataTable();
  dataTable.addColumn({type: 'string', id: 'Person'});
  dataTable.addColumn({type: 'string', id: 'Title'});
  dataTable.addColumn({type: 'date', id: 'Start'});
  dataTable.addColumn({type: 'date', id: 'End'});

  // Use an array so that we can have people in sorted order. It will make the
  // display much nicer to look at.
  const people = Array.from(this.getUniquePeople(events));
  people.sort();

  for (const person of people) {
    for (const e of events) {
      if (e.attendees.has(person)) {
        dataTable.addRow([
          person, e.title, asDate(e.time.getStartTime()),
          asDate(e.time.getEndTime())
        ]);
      }
    }
  }

  const chart = new google.visualization.Timeline(container);
  chart.draw(dataTable);
}


// Load the chart when the doc is ready.
google.charts.load('current', {'packages': ['timeline']});
google.charts.setOnLoadCallback(initializeChart);
