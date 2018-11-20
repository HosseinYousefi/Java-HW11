import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class ChatDatabase {
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection mongoCollection;

    static public final String DATABASE_NAME = "ChatDatabase";
    static public final String COLLECTION_NAME = "chats";
    static public final String USER_FIELD = "user";
    static public final String CHAT_FIELD = "chat";

    public ChatDatabase() {
        mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
        mongoCollection = mongoDatabase.getCollection(COLLECTION_NAME);
        mongoCollection.drop(); // for testing with a new db every time
    }

    public FindIterable<Document> getChats() {
        return mongoCollection.find();
    }

    public void addChat(String user, String chat) {
        Document toBeAdded = new Document();
        toBeAdded.put(USER_FIELD, user);
        toBeAdded.put(CHAT_FIELD, chat);
        mongoCollection.insertOne(toBeAdded);
    }

}
