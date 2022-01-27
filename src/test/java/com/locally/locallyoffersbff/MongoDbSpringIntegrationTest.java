package com.locally.locallyoffersbff;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class MongoDbSpringIntegrationTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";
    private MongodExecutable mongodExecutable;
    private MongoClient mongoClient;

    private final String DATABASE_NAME = "testowabaza";
    private final String COLLECTION_NAME = "testcollection";

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        int port = 27017;
        String ip = "localhost";
        MongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoClient = MongoClients.create(String.format(CONNECTION_STRING, ip, port));
    }


    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")
    @Test
    public void test() {
        // given
        MongoCollection<Document> testCollectionConnection = getCollection(DATABASE_NAME, COLLECTION_NAME);

        Document objectToSave = new Document()
                .append("_id", "some-id")
                .append("mojklucz", "wartosc");

        // when
        testCollectionConnection.insertOne(objectToSave);

        // then
        Document filter = new Document()
                .append("_id", "some-id");

        FindIterable<Document> actual = testCollectionConnection.find(filter);
        System.out.println("dane z bazy " + actual);
        assertThat(actual)
                .extracting("mojklucz")
                .containsOnly("wartosc");
    }

    private MongoCollection<Document> getCollection(String dbName, String collectionName) {
        return mongoClient.getDatabase(dbName).getCollection(collectionName);
    }
}