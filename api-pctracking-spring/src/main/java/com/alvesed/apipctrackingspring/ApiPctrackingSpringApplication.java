package com.alvesed.apipctrackingspring;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class ApiPctrackingSpringApplication {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tablePctracking = "tb01_pctracking";

	public static void main(String[] args) {
		SpringApplication.run(ApiPctrackingSpringApplication.class, args);
		System.out.println("0.0.2-SNAPSHOT 10/10");
		//deleteTable();

		if (!isPctrackingTableCreated()) {
			createPctrackingTable();
		}

		//putItemInTable();

	}

	static boolean isPctrackingTableCreated() {

		TableCollection<ListTablesResult> tables = dynamoDB.listTables();
		Iterator<Table> iterator = tables.iterator();

		System.out.println("Listing table names");
		var isPctrackingTableCreated = false;
		while (iterator.hasNext()) {
			Table table = iterator.next();
			System.out.println(table.getTableName());
			if (table.getTableName().equalsIgnoreCase(tablePctracking)) {
				isPctrackingTableCreated = true;
			}
		}

		return isPctrackingTableCreated;
	}
	static void createPctrackingTable() {

		try {

			List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
			attributeDefinitions.add(new AttributeDefinition().withAttributeName("id_pctracking").withAttributeType("S"));
			attributeDefinitions.add(new AttributeDefinition().withAttributeName("date_time_request_tracking").withAttributeType("S"));

			ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			keySchema.add(new KeySchemaElement()
					.withAttributeName("id_pctracking")
					.withKeyType(KeyType.HASH));
			keySchema.add(new KeySchemaElement()
					.withAttributeName("date_time_request_tracking")
					.withKeyType(KeyType.RANGE));

			CreateTableRequest request = new CreateTableRequest().withTableName(tablePctracking)
					.withKeySchema(keySchema)
					.withAttributeDefinitions(attributeDefinitions).withProvisionedThroughput(
							new ProvisionedThroughput().withReadCapacityUnits(5L).withWriteCapacityUnits(5L));

			System.out.println("Issuing CreateTable request for " + tablePctracking);
			Table table = dynamoDB.createTable(request);

			System.out.println("Waiting for tb01_pctracking to be created...this may take a while...");
			table.waitForActive();

			getTableInformation();

		}
		catch (Exception e) {
			System.err.println("CreateTable request failed for " + tablePctracking);
			System.err.println(e.getMessage());
		}
	}

	static void getTableInformation() {

		System.out.println("Describing " + tablePctracking);

		TableDescription tableDescription = dynamoDB.getTable(tablePctracking).describe();
		System.out.format(
				"Name: %s:\n" + "Status: %s \n" + "Provisioned Throughput (read capacity units/sec): %d \n"
						+ "Provisioned Throughput (write capacity units/sec): %d \n",
				tableDescription.getTableName(), tableDescription.getTableStatus(),
				tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
				tableDescription.getProvisionedThroughput().getWriteCapacityUnits());
	}

	static void deleteTable() {

		Table table = dynamoDB.getTable(tablePctracking);
		try {
			System.out.println("Issuing DeleteTable request for " + tablePctracking);
			table.delete();

			System.out.println("Waiting for " + tablePctracking + " to be deleted...this may take a while...");

			table.waitForDelete();
		}
		catch (Exception e) {
			System.err.println("DeleteTable request failed for " + tablePctracking);
			System.err.println(e.getMessage());
		}
	}

	static void putItemInTable(){

		HashMap<String, AttributeValue> itemValues = new HashMap<>();
		itemValues.put("id_pctracking", AttributeValue.builder().s("2").build());
		itemValues.put("date_time_request_tracking", AttributeValue.builder().s(LocalDateTime.now().toString()).build());

		PutItemRequest request = PutItemRequest.builder()
				.tableName(tablePctracking)
				.item(itemValues)
				.build();

		DynamoDbClient ddb = DynamoDbClient.builder()
				.region(Region.US_EAST_1)
				.build();
		try {
			PutItemResponse response = ddb.putItem(request);
			System.out.println(tablePctracking +" was successfully updated. The request id is "+response.responseMetadata().requestId());

		} catch (ResourceNotFoundException e) {
			System.err.format("Error: The Amazon DynamoDB table \"%s\" can't be found.\n", tablePctracking);
			System.err.println("Be sure that it exists and that you've typed its name correctly!");
			System.exit(1);
		} catch (DynamoDbException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
