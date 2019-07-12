package com.crepo.taskmaster.wk6.taskMaster;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class TheTaskMasterController {

    @Autowired
    private S3Client s3Client;

    @Autowired
    TheTaskMasterRepository theTaskMasterRepository;


    // Get Mapping request methods

    @GetMapping("/")
    public String getHomePg(){

        return "Working";

    }

    @GetMapping("/tasks")
    public Iterable<TheTaskMaster> getTasks(){

        return theTaskMasterRepository.findAll();
    }

    @GetMapping("/users/{assignee}/tasks")
    public List<TheTaskMaster> getAssigneeTasks(@PathVariable String assignee){
        return theTaskMasterRepository.findByAssignee(assignee);
    }


    //Post mapping request methods

    @PostMapping("/tasks")
    public RedirectView createTasks(@RequestParam String title, @RequestParam String description, @RequestParam String assignee,
                                      @RequestPart(value = "file") MultipartFile file ){

        TheTaskMaster task = new TheTaskMaster();
        String picture = this.s3Client.uploadFile(file);
        task.setTitle(title);
        task.setDescription(description);
        task.setAssignee(assignee);
        task.setPicture(picture);
        String [] stuff = picture.split("/");
        String potato = stuff[stuff.length - 1];
        task.setPicReSizer("" + potato);
        theTaskMasterRepository.save(task);
        return new RedirectView(("http://taskmasterapp-app.s3-website-us-west-2.amazonaws.com/"));
    }

    @PostMapping("/tasks/{id}/images")
    public TheTaskMaster createImage(@PathVariable UUID id, @RequestPart(value = "file") MultipartFile file){

        TheTaskMaster task = theTaskMasterRepository.findById(id).get();
        String picture = this.s3Client.uploadFile(file);
        task.setPicture(picture);
        String [] stuff = picture.split("/");
        String potato = stuff[stuff.length - 1];
        task.setPicReSizer("" + potato);
        System.out.println(picture);
        theTaskMasterRepository.save(task);
        return task;
    }

    //Put mapping request methods

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

    @PutMapping("/tasks/{id}/assign/{assignee}")
    public void taskUpdate(@PathVariable UUID id, @PathVariable String assignee){

        TheTaskMaster task = theTaskMasterRepository.findById(id).get();

        task.setAssignee(assignee);
        task.setStatus("Assigned");
        theTaskMasterRepository.save(task);
    }

    // Delete mapping request method

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable UUID id){ theTaskMasterRepository.deleteById(id); }
}
