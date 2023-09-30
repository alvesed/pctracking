package com.alvesed.apipctrackingspring;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class ApiPctrackingSpringApplication {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tablePctracking = "tb01_pctracking";

	public static void main(String[] args) {
		SpringApplication.run(ApiPctrackingSpringApplication.class, args);

		if (!isPctrackingTableCreated()) {
			createPctrackingTable();
		}

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
			attributeDefinitions.add(new AttributeDefinition().withAttributeName("id_pctracking").withAttributeType("N"));


			CreateTableRequest request = new CreateTableRequest().withTableName(tablePctracking)
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

}
