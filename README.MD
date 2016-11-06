[![Build Status](https://travis-ci.org/lannerate/conference-track.svg?branch=master)](https://travis-ci.org/lannerate/conference-track)


##The Solution
Refer to the First-fit decreasing algorithm https://en.wikipedia.org/wiki/Bin_packing_problem

## Design
Based on Object-Oriented Analysis, OOA, which includes the following entities.

The relationship of entities as below
> `Conference -- 1 * n --> Track -- 1 * n --> Period -- 1 * n --> Event `

### Conference 
The conference can contains multiple tracks each one may inclu 

### Track
Each track can contains multiple event periods, that may include morning session period, lunch period, or afternoon session period, or networking period.

### Period
Each Period can contains multiple events(talk or networking). 

### Event
Each event means every talk session or networking, which has event description, and event duration.

## The processing of schedule conference.
The main method is in the `ConferenceApp`
> 1. Read input files, and parse files to events
> 2. Run the conference schedule `ConferenceApp.schedule()` to get the scheduled conference.
> 3. Print the context of conference

### The rules

- The conference has multiple tracks each of which has a morning and afternoon session.
Each session contains multiple talks.
- Morning sessions begin at 9am and must finish before 12 noon, for lunch.
- Afternoon sessions begin at 1pm and must finish in time for the networking event.
- The networking event can start no earlier than 4:00 and no later than 5:00.
- No talk title has numbers in it.
- All talk lengths are either in minutes (not hours) or lightning (5 minutes).
- Presenters will be very punctual; there needs to be no gap between sessions.