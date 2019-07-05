package com.crepo.taskmaster.wk6.taskMaster;


import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@EnableScan
public interface TheTaskMasterRepository extends CrudRepository<TheTaskMaster, UUID> {

    Optional<TheTaskMaster> findById(UUID id);
    List<TheTaskMaster> findByAssignee(String assignee);
}
