package com.gstore;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App {
    public static void main(String[] args) {

        String connectionString = "mongodb+srv://pateldhruti803:Dhruti832@g-store.nhza3e2.mongodb.net/?appName=G-store";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {

            MongoDatabase database = mongoClient.getDatabase("G-Store");
            MongoCollection<Document> collection = database.getCollection("Invoice");

            System.out.println("=== Connected to G-Store ===");

            // CREATE
            Document invoice = new Document("item", "Laptop")
                    .append("quantity", 2)
                    .append("price", 1200);
            collection.insertOne(invoice);
            System.out.println("CREATE: Invoice inserted - " + invoice.toJson());

            // READ
            Document found = collection.find(new Document("item", "Laptop")).first();
            System.out.println("READ: Found document - " + found.toJson());

            // UPDATE
            collection.updateOne(
                new Document("item", "Laptop"),
                new Document("$set", new Document("price", 999))
            );
            Document updated = collection.find(new Document("item", "Laptop")).first();
            System.out.println("UPDATE: Updated document - " + updated.toJson());

            // DELETE
            collection.deleteOne(new Document("item", "Laptop"));
            System.out.println("DELETE: Document deleted successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
