package com.crepo.taskmaster.wk6.taskMaster;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TheTaskMasterController {

    @Autowired
    TheTaskMasterRepository theTaskMasterRepository;

    @GetMapping("/")
    public String getHomePg(){

        return "Working";

    }

    @GetMapping("/tasks")
    public void createTasks(@RequestParam String title, @RequestParam String description, @RequestParam String status){

        status = status.substring(0, 1).toUpperCase() + status.substring(1);
        TheTaskMaster task = new TheTaskMaster(title, description, status);
        theTaskMasterRepository.save(task);

    }

    @PutMapping("/tasks/{id}/state")
    public void taskStateUpdate(@PathVariable UUID id){

        TheTaskMaster task = theTaskMasterRepository.findById(id).get();
        String status = task.getStatus();

        if (status.equals("Available")){
            task.setStatus("Assigned");
        }

        if (status.equals("Assigned")){
            task.setStatus("Accepted");
        }

        if (status.equals("Accepted")){
            task.setStatus("Finished");
        }
        theTaskMasterRepository.save(task);
    }
}
