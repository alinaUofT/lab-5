package data_access;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * The constructor to access or mongo DB, creates an endpoint to our "Movies4U" database.
 */
public class DataBaseConstructor {
    private static final String URI = "mongodb+srv://andersonwyatt199:oSJAGafbowj9jeFl@cluster0.j9lgn.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    // Reuse a single MongoClient instance
    private static final MongoClient mongoClient = MongoClients.create(URI);

    // Constructor
    public DataBaseConstructor() {
    }

    /**
     * Method to create or get a collection.
     * @param collectionName a key String.
     * @return a collection from our DB.
     */
    public static MongoCollection<Document> getCollection(String collectionName) {
        try {
            // Access the specific database
            final MongoDatabase database = mongoClient.getDatabase("Movies4U");
            // Return the requested collection
            return database.getCollection(collectionName);
        }
        catch (Exception e) {
            System.out.println("Error accessing the collection: " + e.getMessage());
            throw e;
        }
    }
}
