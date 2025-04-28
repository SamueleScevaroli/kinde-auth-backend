package com.tutorial.general.auth.kinde;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {
    static {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        System.setProperty("KINDE_CLIENT_ID", dotenv.get("KINDE_CLIENT_ID"));
        System.setProperty("KINDE_CLIENT_SECRET", dotenv.get("KINDE_CLIENT_SECRET"));
        System.setProperty("KINDE_API_URL", dotenv.get("KINDE_API_URL"));
    }
}