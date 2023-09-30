package com.alvesed.apipctrackingspring.dataprovider.dynamodb.tables;

import com.alvesed.apipctrackingspring.core.domain.Pctracking;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@DynamoDBTable(tableName = "tb01_pctracking")
public class PctrackingTable {

    @DynamoDBAttribute(attributeName = "id_pctracking")
    private Long idPctracking;

    @DynamoDBAttribute(attributeName = "date_time_request_tracking")
    private LocalDateTime dateTimeRequestTracking;

    public Pctracking toPctracking() {
        return new Pctracking(this.idPctracking, this.dateTimeRequestTracking);
    }
}
