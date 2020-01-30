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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** */
@RunWith(JUnit4.class)
public final class TimeRangeTest {
  @Test
  public void equality() {
    Assert.assertEquals(TimeRange.fromStartDuration(100, 50), TimeRange.fromStartDuration(100, 50));

    Assert.assertNotEquals(
        TimeRange.fromStartDuration(100, 50), TimeRange.fromStartDuration(100, 51));

    Assert.assertEquals(
        TimeRange.fromStartDuration(100, 50), TimeRange.fromStartEnd(100, 150, false));

    Assert.assertNotEquals(
        TimeRange.fromStartDuration(100, 50), TimeRange.fromStartEnd(100, 150, true));

    Assert.assertEquals(
        TimeRange.fromStartDuration(100, 51), TimeRange.fromStartEnd(100, 150, true));

    Assert.assertEquals(
        TimeRange.fromStartEnd(100, 151, false), TimeRange.fromStartEnd(100, 150, true));
  }

  @Test
  public void containsPoint() {
    // Range 100 (inclusive) to 150 (exclusive).
    TimeRange range = TimeRange.fromStartDuration(100, 50);
    Assert.assertFalse(range.contains(50));
    Assert.assertTrue(range.contains(100));
    Assert.assertFalse(range.contains(150));
    Assert.assertFalse(range.contains(200));
  }

  @Test
  public void containsRange() {
    TimeRange range = TimeRange.fromStartDuration(200, 50);

    // |---|   |--range--|
    Assert.assertFalse(range.contains(TimeRange.fromStartDuration(0, 20)));

    //     |--range--|
    // |---|
    Assert.assertFalse(range.contains(TimeRange.fromStartDuration(180, 20)));

    //   |--range--|
    // |---|
    Assert.assertFalse(range.contains(TimeRange.fromStartDuration(190, 20)));

    // |--range--|
    // |---|
    Assert.assertTrue(range.contains(TimeRange.fromStartDuration(200, 20)));

    // |--range--|
    //    |---|
    Assert.assertTrue(range.contains(TimeRange.fromStartDuration(210, 20)));

    // |--range--|
    //       |---|
    Assert.assertTrue(range.contains(TimeRange.fromStartDuration(230, 20)));

    // |--range--|
    //         |---|
    Assert.assertFalse(range.contains(TimeRange.fromStartDuration(240, 20)));

    // |--range--|
    //           |---|
    Assert.assertFalse(range.contains(TimeRange.fromStartDuration(250, 20)));

    // |--range--| |---|
    Assert.assertFalse(range.contains(TimeRange.fromStartDuration(260, 20)));
  }

  @Test
  public void overlaps() {
    TimeRange range = TimeRange.fromStartDuration(200, 50);

    // |---|   |--range--|
    Assert.assertFalse(range.overlaps(TimeRange.fromStartDuration(0, 20)));

    //     |--range--|
    // |---|
    Assert.assertFalse(range.overlaps(TimeRange.fromStartDuration(180, 20)));

    //   |--range--|
    // |---|
    Assert.assertTrue(range.overlaps(TimeRange.fromStartDuration(190, 20)));

    // |--range--|
    // |---|
    Assert.assertTrue(range.overlaps(TimeRange.fromStartDuration(200, 20)));

    // |--range--|
    //    |---|
    Assert.assertTrue(range.overlaps(TimeRange.fromStartDuration(210, 20)));

    // |--range--|
    //       |---|
    Assert.assertTrue(range.overlaps(TimeRange.fromStartDuration(230, 20)));

    // |--range--|
    //         |---|
    Assert.assertTrue(range.overlaps(TimeRange.fromStartDuration(240, 20)));

    // |--range--|
    //           |---|
    Assert.assertFalse(range.overlaps(TimeRange.fromStartDuration(250, 20)));

    // |--range--| |---|
    Assert.assertFalse(range.overlaps(TimeRange.fromStartDuration(260, 20)));
  }

  @Test
  public void rangeContainsSelf() {
    TimeRange range = TimeRange.fromStartDuration(100, 100);
    Assert.assertTrue(range.contains(range));
  }

  @Test
  public void rangeOverlapsSelf() {
    TimeRange range = TimeRange.fromStartDuration(100, 100);
    Assert.assertTrue(range.overlaps(range));
  }

  @Test
  public void emptyRangeContainsNothing() {
    TimeRange range = TimeRange.fromStartDuration(100, 0);

    Assert.assertFalse(range.contains(100));
    Assert.assertFalse(range.contains(range));
  }

  @Test
  public void canContainEmptyRange() {
    // Range from 100 (inclusive) to 200 (inclusive).
    TimeRange range = TimeRange.fromStartEnd(100, 200, true);

    TimeRange emptyStart = TimeRange.fromStartDuration(100, 0);
    TimeRange emptyMiddle = TimeRange.fromStartDuration(150, 0);
    TimeRange emptyEnd = TimeRange.fromStartDuration(200, 0);

    Assert.assertTrue(range.contains(emptyStart));
    Assert.assertTrue(range.contains(emptyMiddle));
    Assert.assertTrue(range.contains(emptyEnd));
  }

  @Test
  public void canOverlapEmptyRange() {
    // Range from 100 (inclusive) to 200 (inclusive).
    TimeRange range = TimeRange.fromStartEnd(100, 200, true);

    TimeRange emptyStart = TimeRange.fromStartDuration(100, 0);
    TimeRange emptyMiddle = TimeRange.fromStartDuration(150, 0);
    TimeRange emptyEnd = TimeRange.fromStartDuration(200, 0);

    Assert.assertTrue(range.overlaps(emptyStart));
    Assert.assertTrue(range.overlaps(emptyMiddle));
    Assert.assertTrue(range.overlaps(emptyEnd));

    // Overlaps is commutative, meaning that regardless of order, the result should be the same.
    // There for "a overlaps b" should be the same as "b overlaps a".
    Assert.assertTrue(emptyStart.overlaps(range));
    Assert.assertTrue(emptyMiddle.overlaps(range));
    Assert.assertTrue(emptyEnd.overlaps(range));
  }
}
