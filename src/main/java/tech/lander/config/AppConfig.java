package tech.lander.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import tech.lander.Util.DCUtil;


@Configuration
public class AppConfig {

    @Value("${mongodburi}")
    private String mongoURL;

    @Value("${publicKeyLoc}")
    private String publicKeyLoc;

    @Bean
    public MongoDatabaseFactory mongoDbFactory() throws Exception {
        return new SimpleMongoClientDatabaseFactory(getDecryptedMongoURL(mongoURL));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    private String getDecryptedMongoURL(String cipherText) {
        String mongoDBUri = "";
        try {
            DCUtil dcUtil = new DCUtil();
            mongoDBUri = dcUtil.decryptText(cipherText, publicKeyLoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mongoDBUri;
    }
}