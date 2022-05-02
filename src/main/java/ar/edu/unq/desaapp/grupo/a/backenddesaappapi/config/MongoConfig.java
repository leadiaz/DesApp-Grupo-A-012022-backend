package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "criptop2p";
    }
    @Override
    public MongoClient mongoClient(){
        ConnectionString connectionString = new ConnectionString("mongodb+srv://ldiaz:wCRsweRl4Mt3XOqH@cluster0.f1ok6.mongodb.net/criptop2p?retryWrites=true&w=majority");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        return MongoClients.create(mongoClientSettings);
    }
}
