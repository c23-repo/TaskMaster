# TaskMaker

## Description
- Application that manages tasks.
- Tasks can be created, assigned, and status can be updated.
- Tasks for assignees can be displayed.

## API
- ```@GetMapping("/tasks")```
  - Returns json data from the dynamodb.
- ```@PostMapping("/tasks)```
  - Post tasks to the dynamodb.
- ```@PutMapping("/tasks/{id}/state")```
  - Update task status.
- ```@GetMapping("/users/{assignee}/tasks) ```
  - Show the tasks that are assigned to that assignee.
- ```@PutMapping("/tasks/{id}/assign/{assignee}) ```
  - Assign tasks to an assignee.
- ```@DeleteMapping("/tasks/{id}")```
  - Delete a task.
  
## Deployed
- http://taskmaster123cr.us-west-2.elasticbeanstalk.com/
  - to test routes replace localhost:5000 with the above link