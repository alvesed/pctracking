package com.alvesed.apipctrackingspring.dataprovider.dynamodb;

import com.alvesed.apipctrackingspring.core.domain.Pctracking;
import com.alvesed.apipctrackingspring.core.ports.in.ListPctrackingPort;
import com.alvesed.apipctrackingspring.dataprovider.dynamodb.tables.PctrackingTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PctrackingTableAdapter implements ListPctrackingPort {


    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public List<Pctracking> listPctrackingPort() {

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withIndexName("id_pctracking")
                .withConsistentRead(false);

        var requestsPctrackingTable = dynamoDBMapper.scan(PctrackingTable.class, scanExpression);

        return requestsPctrackingTable.stream()
                .map(PctrackingTable::toPctracking)
                .toList();

    }
}
