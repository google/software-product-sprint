// Copyright 2019 Google LLC
//
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

package com.google.sps;

import java.util.Arrays;

public final class Events {
  private static final String PERSON_AMELIA = "Amelia";
  private static final String PERSON_AVA = "Ava";
  private static final String PERSON_EMMA = "Emma";
  private static final String PERSON_ISABELLA = "Isabella";
  private static final String PERSON_JAMES = "James";
  private static final String PERSON_LIAM = "Liam";
  private static final String PERSON_LOGAN = "Logan";
  private static final String PERSON_NOAH = "Noah";
  private static final String PERSON_OLIVER = "Oliver";
  private static final String PERSON_OLIVIA = "Olivia";

  private static final int TIME_0800 = TimeRange.getTimeInMinutes(8, 0);
  private static final int TIME_0830 = TimeRange.getTimeInMinutes(8, 30);
  private static final int TIME_0900 = TimeRange.getTimeInMinutes(9, 0);
  private static final int TIME_0930 = TimeRange.getTimeInMinutes(9, 30);
  private static final int TIME_1000 = TimeRange.getTimeInMinutes(10, 0);
  private static final int TIME_1030 = TimeRange.getTimeInMinutes(10, 30);
  private static final int TIME_1100 = TimeRange.getTimeInMinutes(11, 0);
  private static final int TIME_1130 = TimeRange.getTimeInMinutes(11, 30);
  private static final int TIME_1200 = TimeRange.getTimeInMinutes(12, 0);
  private static final int TIME_1230 = TimeRange.getTimeInMinutes(12, 30);
  private static final int TIME_1300 = TimeRange.getTimeInMinutes(13, 0);
  private static final int TIME_1330 = TimeRange.getTimeInMinutes(13, 30);
  private static final int TIME_1400 = TimeRange.getTimeInMinutes(14, 0);
  private static final int TIME_1430 = TimeRange.getTimeInMinutes(14, 30);
  private static final int TIME_1500 = TimeRange.getTimeInMinutes(15, 0);
  private static final int TIME_1530 = TimeRange.getTimeInMinutes(15, 30);
  private static final int TIME_1600 = TimeRange.getTimeInMinutes(16, 0);
  private static final int TIME_1630 = TimeRange.getTimeInMinutes(16, 30);
  private static final int TIME_1700 = TimeRange.getTimeInMinutes(17, 0);
  private static final int TIME_1730 = TimeRange.getTimeInMinutes(17, 30);
  private static final int TIME_1800 = TimeRange.getTimeInMinutes(18, 0);
  private static final int TIME_1830 = TimeRange.getTimeInMinutes(18, 30);
  private static final int TIME_1900 = TimeRange.getTimeInMinutes(19, 0);
  private static final int TIME_1930 = TimeRange.getTimeInMinutes(19, 30);
  private static final int TIME_2000 = TimeRange.getTimeInMinutes(20, 0);
  private static final int TIME_2030 = TimeRange.getTimeInMinutes(20, 30);
  private static final int TIME_2100 = TimeRange.getTimeInMinutes(21, 0);

  public static final Event[] events = {
      new Event("1-on-1 : Project Management", TimeRange.fromStartEnd(TIME_1500, TIME_1530, false),
          Arrays.asList(PERSON_LOGAN)),
      new Event("1-on-1 Career Advice", TimeRange.fromStartEnd(TIME_1000, TIME_1030, false),
          Arrays.asList(PERSON_ISABELLA)),
      new Event("1-on-1 Catch-up", TimeRange.fromStartEnd(TIME_1330, TIME_1400, false),
          Arrays.asList(PERSON_EMMA)),
      new Event("Career Development Talk", TimeRange.fromStartEnd(TIME_1000, TIME_1100, false),
          Arrays.asList(PERSON_AVA)),
      new Event("Company Year-end Review", TimeRange.fromStartEnd(TIME_1000, TIME_1100, false),
          Arrays.asList(PERSON_EMMA)),
      new Event("Head-down work", TimeRange.fromStartEnd(TIME_1430, TIME_1600, false),
          Arrays.asList(PERSON_ISABELLA)),
      new Event("Hiring Meeting", TimeRange.fromStartEnd(TIME_1100, TIME_1200, false),
          Arrays.asList(PERSON_JAMES, PERSON_OLIVIA)),
      new Event("Hiring Review", TimeRange.fromStartEnd(TIME_0900, TIME_1000, false),
          Arrays.asList(PERSON_JAMES)),
      new Event("Hiring Review", TimeRange.fromStartEnd(TIME_1030, TIME_1200, false),
          Arrays.asList(PERSON_ISABELLA)),
      new Event("Hiring Review", TimeRange.fromStartEnd(TIME_1400, TIME_1430, false),
          Arrays.asList(PERSON_ISABELLA)),
      new Event("Interview Recap", TimeRange.fromStartEnd(TIME_1100, TIME_1130, false),
          Arrays.asList(PERSON_AMELIA)),
      new Event("Team Lunch", TimeRange.fromStartEnd(TIME_1200, TIME_1300, false),
          Arrays.asList(PERSON_AMELIA, PERSON_AVA, PERSON_ISABELLA, PERSON_LOGAN)),
      new Event("Lunch", TimeRange.fromStartEnd(TIME_1200, TIME_1300, false),
          Arrays.asList(PERSON_NOAH, PERSON_OLIVER)),
      new Event("OOO - Appointment", TimeRange.fromStartEnd(TIME_0830, TIME_0900, false),
          Arrays.asList(PERSON_LOGAN)),
      new Event("OOO - Appointment", TimeRange.fromStartEnd(TIME_0900, TIME_1000, false),
          Arrays.asList(PERSON_AMELIA)),
      new Event("OOO - Appointment", TimeRange.fromStartEnd(TIME_0900, TIME_1000, false),
          Arrays.asList(PERSON_AVA)),
      new Event("OOO - Appointment", TimeRange.fromStartEnd(TIME_1200, TIME_1430, false),
          Arrays.asList(PERSON_LIAM)),
      new Event(
          "OOO", TimeRange.fromStartEnd(TIME_1700, TIME_2000, false), Arrays.asList(PERSON_EMMA)),
      new Event("Partners Dinner", TimeRange.fromStartEnd(TIME_1830, TIME_2100, false),
          Arrays.asList(PERSON_AMELIA)),
      new Event("Partners Meeting", TimeRange.fromStartEnd(TIME_1430, TIME_1700, false),
          Arrays.asList(PERSON_AMELIA)),
      new Event("Phone Interview", TimeRange.fromStartEnd(TIME_1400, TIME_1500, false),
          Arrays.asList(PERSON_AMELIA)),
      new Event("Product Leadership AMA", TimeRange.fromStartEnd(TIME_0930, TIME_1030, false),
          Arrays.asList(PERSON_OLIVER)),
      new Event("Release Planning", TimeRange.fromStartEnd(TIME_1030, TIME_1100, false),
          Arrays.asList(PERSON_LOGAN)),
      new Event("Self-study", TimeRange.fromStartEnd(TIME_1400, TIME_1430, false),
          Arrays.asList(PERSON_OLIVIA)),
      new Event("Team Outing - Pick-up Tickets",
          TimeRange.fromStartEnd(TIME_1130, TIME_1330, false), Arrays.asList(PERSON_OLIVER)),
      new Event("Team Outing", TimeRange.fromStartEnd(TIME_1130, TIME_1400, false),
          Arrays.asList(PERSON_JAMES, PERSON_OLIVIA)),
      new Event("Team Sync", TimeRange.fromStartEnd(TIME_1030, TIME_1100, false),
          Arrays.asList(PERSON_OLIVIA, PERSON_EMMA)),
      new Event("Team Sync", TimeRange.fromStartEnd(TIME_1100, TIME_1130, false),
          Arrays.asList(PERSON_LOGAN)),
      new Event("Team Sync", TimeRange.fromStartEnd(TIME_1300, TIME_1400, false),
          Arrays.asList(PERSON_LOGAN)),
      new Event("Team Vision Planning", TimeRange.fromStartEnd(TIME_1430, TIME_1530, false),
          Arrays.asList(PERSON_LIAM)),
      new Event("Team planning", TimeRange.fromStartEnd(TIME_1300, TIME_1330, false),
          Arrays.asList(PERSON_ISABELLA)),
      new Event("Team stand-up", TimeRange.fromStartEnd(TIME_1100, TIME_1130, false),
          Arrays.asList(PERSON_AVA, PERSON_ISABELLA, PERSON_OLIVER)),
      new Event("Team sync", TimeRange.fromStartEnd(TIME_1030, TIME_1100, false),
          Arrays.asList(PERSON_JAMES)),
      new Event("Team triage", TimeRange.fromStartEnd(TIME_1330, TIME_1400, false),
          Arrays.asList(PERSON_ISABELLA)),
      new Event("Vendor Sync", TimeRange.fromStartEnd(TIME_1000, TIME_1030, false),
          Arrays.asList(PERSON_EMMA)),
      new Event("Work Trip Planning", TimeRange.fromStartEnd(TIME_1000, TIME_1030, false),
          Arrays.asList(PERSON_LIAM)),
  };

  private Events() {
    // Disallow instances.
  }
}
