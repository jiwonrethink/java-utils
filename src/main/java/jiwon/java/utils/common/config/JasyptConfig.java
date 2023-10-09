package jiwon.java.utils.common.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}")
    private String encryptKey;

    @Bean("jasyptStringEncryptor")
    public StandardPBEStringEncryptor jasyptStringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptKey);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations(1000);
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);

        return encryptor;
    }
}
