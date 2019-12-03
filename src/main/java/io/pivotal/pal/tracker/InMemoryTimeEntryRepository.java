package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository
{
    private Map<Long,TimeEntry> map=new HashMap<>();
    private int idSequence = 0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        if (timeEntry.getId() == 0)
            timeEntry.setId(nextId());
        map.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    private long nextId() {
        return ++idSequence;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return map.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        //return new ArrayList<>(map.values());
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(!map.containsKey(id))
        {
            return null;
        }

        timeEntry.setId(id);
        map.put(id, timeEntry);
        return map.get(id);
    }

    @Override
    public void delete(long timeEntryId) {
        map.remove(timeEntryId);
    }
}
