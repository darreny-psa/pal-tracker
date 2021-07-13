package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {
    TimeEntryRepository timeEntryRepo;
    List<TimeEntry> timeEntries;

    public TimeEntryController(TimeEntryRepository timeEntryRepo) {
        this.timeEntryRepo = timeEntryRepo;
    }
    @DeleteMapping(value = "time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable("timeEntryId") Long timeEntryID){
            timeEntryRepo.delete(timeEntryID);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable("timeEntryId") Long timeEntryId, @RequestBody TimeEntry timeEntryRecord) {
        TimeEntry newTimeEntry = timeEntryRepo.update(timeEntryId, timeEntryRecord);
        if (newTimeEntry == null) {
            return new ResponseEntity<TimeEntry>(newTimeEntry, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<TimeEntry>(newTimeEntry, HttpStatus.OK);
        }

    }
    @GetMapping(value = "time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepo.list(),HttpStatus.OK);
    }
    @GetMapping(value = "time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable("timeEntryId") Long timeEntryId){
        TimeEntry newTimeEntry = timeEntryRepo.find(timeEntryId);
        if(newTimeEntry==null){
            return new ResponseEntity<TimeEntry>(newTimeEntry,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<TimeEntry>(newTimeEntry, HttpStatus.OK);
        }
    }
    @PostMapping(value = "time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry newTimeEntry){
        return new ResponseEntity<TimeEntry>(timeEntryRepo.create(newTimeEntry),HttpStatus.CREATED);
    }
}
