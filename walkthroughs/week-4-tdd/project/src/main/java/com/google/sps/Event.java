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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Event is the container class for when a specific group of people are meeting and are therefore
 * busy. Events are considered read-only.
 */
public final class Event {
  private final String title;
  private final TimeRange when;
  private final Set<String> attendees = new HashSet<>();

  /**
   * Creates a new event.
   *
   * @param title The human-readable name for the event. Must be non-null.
   * @param when The time when the event takes place. Must be non-null.
   * @param attendees The collection of people attending the event. Must be non-null.
   */
  public Event(String title, TimeRange when, Collection<String> attendees) {
    if (title == null) {
      throw new IllegalArgumentException("title cannot be null");
    }

    if (when == null) {
      throw new IllegalArgumentException("when cannot be null");
    }

    if (attendees == null) {
      throw new IllegalArgumentException("attendees cannot be null. Use empty array instead.");
    }

    this.title = title;
    this.when = when;
    this.attendees.addAll(attendees);
  }

  /**
   * Returns the human-readable name for this event.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the {@code TimeRange} for when this event occurs.
   */
  public TimeRange getWhen() {
    return when;
  }

  /**
   * Returns a read-only set of required attendees for this event.
   */
  public Set<String> getAttendees() {
    // Return the attendees as an unmodifiable set so that the caller can't change our
    // internal data.
    return Collections.unmodifiableSet(attendees);
  }

  @Override
  public int hashCode() {
    // For the hash code, just use the title. Most events "should" have different names and will
    // mainly be used as a way to skip the costly {@code equals()} call.
    return title.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof Event && equals(this, (Event) other);
  }

  private static boolean equals(Event a, Event b) {
    // {@code attendees} must be a set for equals to work as expected. According to the {@code Set}
    // interface documentation, equals will check for set-equality across all set implementations.
    return a.title.equals(b.title) && a.when.equals(b.when) && a.attendees.equals(b.attendees);
  }
}
