package io.pivotal.pal.tracker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntryStore = new ConcurrentHashMap<>();
    private AtomicLong counterId = new AtomicLong(0);

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(counterId.incrementAndGet(), timeEntry);
        timeEntryStore.put(newTimeEntry.getId(), newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntryStore.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryStore.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if(timeEntryStore.get(id) == null) return null;

        TimeEntry updateTimeEntry = new TimeEntry(id, timeEntry);
        timeEntryStore.put(id, updateTimeEntry);
        return timeEntryStore.get(id);
    }

    @Override
    public void delete(Long id) {
        timeEntryStore.remove(id);
    }
}
