package pl.akmf.ksef.sdk;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

    public static String generateRandomNIP() {
        StringBuilder sb = new StringBuilder(10);
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(1,10)); // Generates digit 0-9
        }

        return sb.toString();
    }
}
