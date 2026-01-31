package com.newzhxu.cloudflare;

import jakarta.annotation.Nonnull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CloudflareMessageConverter extends MappingJackson2HttpMessageConverter {
    @Override
    public boolean canRead(Class<?> clazz, @Nullable MediaType mediaType) {
        return super.canRead(clazz, mediaType);
    }

    @Override
    protected boolean canRead(@Nullable MediaType mediaType) {
        return super.canRead(mediaType);
    }

    @Override
    public boolean canRead(Type type, @Nullable Class<?> contextClass, @Nullable MediaType mediaType) {
        return super.canRead(type, contextClass, mediaType);
    }

    @Override
    public void setSupportedMediaTypes(@NonNull List<MediaType> supportedMediaTypes) {
        ArrayList<MediaType> mediaTypes = new ArrayList<>(supportedMediaTypes);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);

        super.setSupportedMediaTypes(Collections.unmodifiableList(mediaTypes));
    }

    @Override
    @NonNull
    public Object read(@NonNull Type type, Class<?> contextClass, @NonNull HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String s = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
        log.info("响应内容: {}", s);
        HttpInputMessage warpper = new HttpInputMessage() {
            @Override
            @Nonnull
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }

            @Override
            @Nonnull
            public InputStream getBody() {
                return new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
            }
        };
        return super.read(type, contextClass, warpper);
    }
}
