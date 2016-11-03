## OOA Design
Based on Object-Oriented Analysis, OOA, this system should include the following main object.

The relationship of entity as below

`Conference -- 1 * n --> Period -- 1 * n --> Track -- 1 * n --> Event `


### Conference 
It presents Conference.
The conference has multiple tracks each of which has a morning and afternoon session.

### Period
It presents session period, include morning session period, lunch period, and afternoon session period, also have networking period.

### Track
Each track contains multiple events.

### Event
It presents every talk session, included event name, event duration

### The rules

- The conference has multiple tracks each of which has a morning and afternoon session.
Each session contains multiple talks.
- Morning sessions begin at 9am and must finish before 12 noon, for lunch.
- Afternoon sessions begin at 1pm and must finish in time for the networking event.
- The networking event can start no earlier than 4:00 and no later than 5:00.
- No talk title has numbers in it.
- All talk lengths are either in minutes (not hours) or lightning (5 minutes).
- Presenters will be very punctual; there needs to be no gap between sessions.