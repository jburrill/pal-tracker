package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity<>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        HttpStatus status = timeEntry == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return  new ResponseEntity<>(timeEntry, status);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryInput) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, timeEntryInput);
        HttpStatus status = timeEntry == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return  new ResponseEntity<>(timeEntry, status);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
