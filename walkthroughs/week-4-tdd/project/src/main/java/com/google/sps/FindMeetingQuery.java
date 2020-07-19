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
import java.util.Set;
import java.util.Collection;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    Collection<String> attendees = request.getAttendees();
    long duration = request.getDuration();
    ArrayList<TimeRange> res = new ArrayList<>();

    // get all the unavailable timeRange
    ArrayList<TimeRange> naRanges = new ArrayList<>();
    
    //iterate over all the events
    for (Event event : events) {
        boolean attendeeInvolved = false;
        Set<String> involvedAttendees = event.getAttendees();
        for (String attendee : attendees) {
            if (involvedAttendees.contains(attendee)) {
                attendeeInvolved = true;
                break;
            }
        }
        if (attendeeInvolved) {
            naRanges.add(event.getWhen());
        }
    }

    naRanges.sort(TimeRange.ORDER_BY_START);
    int start_of_slot = TimeRange.START_OF_DAY;

    for (TimeRange range: naRanges) {
        if (range.end() <= start_of_slot) {
            continue;
        }else if (range.start() <= start_of_slot){
            start_of_slot = range.end();
            continue;
        }
        TimeRange availRange = TimeRange.fromStartEnd(start_of_slot, range.start(), false);
        start_of_slot = range.end();
        if (availRange .duration() >= duration) {
            res.add(availRange);
        }
    }
    if (TimeRange.END_OF_DAY - start_of_slot >= duration) {
        res.add(TimeRange.fromStartEnd(start_of_slot, TimeRange.END_OF_DAY, true));
    }

    return res;
  }
}
