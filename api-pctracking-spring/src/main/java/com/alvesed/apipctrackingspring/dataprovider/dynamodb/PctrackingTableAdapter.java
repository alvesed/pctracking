package com.alvesed.apipctrackingspring.dataprovider.dynamodb;

import com.alvesed.apipctrackingspring.core.domain.Pctracking;
import com.alvesed.apipctrackingspring.core.ports.in.ListPctrackingPort;
import com.alvesed.apipctrackingspring.core.ports.out.CreatePctrackingPort;
import com.alvesed.apipctrackingspring.dataprovider.dynamodb.tables.PctrackingTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PctrackingTableAdapter implements CreatePctrackingPort, ListPctrackingPort {


    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public List<Pctracking> listPctrackingPort() {

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withConsistentRead(false);

        var requestsPctrackingTable = dynamoDBMapper.scan(PctrackingTable.class, scanExpression);

        return requestsPctrackingTable.stream()
                .map(PctrackingTable::toPctracking)
                .toList();

    }

    @Override
    public Pctracking createPctrackingPort(Pctracking pctracking) {

        HashMap<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("id_pctracking", AttributeValue.builder().s(pctracking.getIdPctracking()).build());
        itemValues.put("date_time_request_tracking", AttributeValue.builder().s(pctracking.getDateTimeRequestTracking().toString()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName("tb01_pctracking")
                .item(itemValues)
                .build();

        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .build();
        try {
            PutItemResponse response = ddb.putItem(request);
            System.out.println("tb01_pctracking was successfully created. The request id is "+response.responseMetadata().requestId());

        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The Amazon DynamoDB table tb01_pctracking can't be found.\n");
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }

        return pctracking;
    }
}
