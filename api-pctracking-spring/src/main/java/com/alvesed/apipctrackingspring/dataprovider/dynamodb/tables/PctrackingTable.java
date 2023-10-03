package com.alvesed.apipctrackingspring.dataprovider.dynamodb.tables;

import com.alvesed.apipctrackingspring.core.domain.Pctracking;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "tb01_pctracking")
public class PctrackingTable {

    @DynamoDBHashKey(attributeName = "id_pctracking")
    private String idPctracking;

    @DynamoDBAttribute(attributeName = "date_time_request_tracking")
    private String dateTimeRequestTracking;

    public Pctracking toPctracking() {
        return new Pctracking(this.idPctracking, LocalDateTime.parse(this.dateTimeRequestTracking));
    }

    public static PctrackingTable of(Pctracking pctracking) {
        return new PctrackingTable(pctracking.getIdPctracking(), pctracking.getDateTimeRequestTracking().toString());
    }

}
