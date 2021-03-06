package com.excelsiormc.excelsiorcore.services.database;

import com.excelsiormc.excelsiorcore.services.ServiceLocationUtils;
import com.excelsiormc.excelsiorcore.services.UUIDUtils;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

/**
 * Connects to the given MongoDB database and provides a connected MongoClient for custom writing and reading
 */
public class ServiceMongoDB {

    private MongoClient client;
    private MongoDatabase database;
    private String username, password, ip, databaseName;

    public ServiceMongoDB(String username, String password, String ip, String databaseName) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.databaseName = databaseName;
        openConnection();
    }

    public void openConnection(){
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://" + username + ":" + password + ip);
            client = new MongoClient(uri);
            if (database == null) {
                database = client.getDatabase(databaseName);
            }
        } catch( Exception e){
            System.out.println(e.toString());
        }
    }

    public boolean isConnected(){
        return client != null;
    }

    public MongoDatabase getDatabase(){
        return database;
    }

    public void close() {
        client.close();
        client = null;
        database = null;
    }

    public Document fetchEmbeddedDocument(Document document, String field) { //for reading a single, non-embedded integer
        return (Document)document.get(field);
    }

    public MongoClient getClient() {
        return client;
    }
}
