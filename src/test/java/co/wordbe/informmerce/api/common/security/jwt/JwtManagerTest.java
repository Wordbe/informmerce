package co.wordbe.informmerce.api.common.security.jwt;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {JwtManager.class, Gson.class})
class JwtManagerTest {
    @Autowired
    JwtManager jwtManager;

    @Test
    void name() {

    }
}