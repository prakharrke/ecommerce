package prakhar.system;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Keys {

    private static String JWT = pathTo("jwt.key");


    public static SecretKey loadJwtKey() throws IOException {
        String encodedKey = Files.readFirstLine(new File(JWT), Charsets.UTF_8).trim();
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, SignatureAlgorithm.HS512.getJcaName());
    }

    private static String pathTo(String value) {
        return "Config/Keys/" + value;
    }
}
