package com.crepo.taskmaster.wk6.taskMaster;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TaskMasterApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
public class TheTaskMasterTests {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    TheTaskMasterRepository theTaskMasterRepository;

    private static final String EXPECTED_TITLE = "Test Title";
    private static final String EXPECTED_DESCRIPTION = "Test Description";
    private static final String EXPECTED_STATUS = "Test Status";

    @Before
    public void setup() throws Exception {

        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(TheTaskMaster.class);

        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

    }

    @Test
    public void testReadWrite() {

        TheTaskMaster task = new TheTaskMaster(EXPECTED_TITLE, EXPECTED_DESCRIPTION, EXPECTED_STATUS);
        theTaskMasterRepository.save(task);

        List<TheTaskMaster> result = (List<TheTaskMaster>) theTaskMasterRepository.findAll();

        assertTrue("Not empty", result.size() > 0);
        assertTrue("Contains a task with the expected title", result.get(0).getTitle().equals(EXPECTED_TITLE));

    }
}
