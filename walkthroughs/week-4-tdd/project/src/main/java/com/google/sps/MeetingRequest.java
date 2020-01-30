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

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public final class MeetingRequest {
  // All the people that should be attending this new meeting. Use a set to avoid duplicates.
  private final Collection<String> attendees = new HashSet<>();

  // The duration of the meeting in minutes.
  private final long duration;

  public MeetingRequest(Collection<String> attendees, long duration) {
    this.duration = duration;
    this.attendees.addAll(attendees);
  }

  /**
   * Returns a read-only copy of the people who are required to attend this meeting.
   */
  public Collection<String> getAttendees() {
    return Collections.unmodifiableCollection(attendees);
  }

  /**
   * Returns the duration of the meeting in minutes.
   */
  public long getDuration() {
    return duration;
  }
}
