package br.paulocalderan.projetocrud.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceUtils {
    public static String getContentFromResource(String resourceName) {
        try {
            InputStream stream = ResourceUtils.class.getResourceAsStream(resourceName);
            return StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
