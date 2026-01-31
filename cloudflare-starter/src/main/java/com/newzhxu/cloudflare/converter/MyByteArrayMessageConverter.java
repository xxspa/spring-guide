package com.newzhxu.cloudflare.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyByteArrayMessageConverter extends ByteArrayHttpMessageConverter {
    @Override
    public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
        ArrayList<MediaType> mediaTypes = new ArrayList<>(supportedMediaTypes);
        super.setSupportedMediaTypes(Collections.unmodifiableList(mediaTypes));
    }

    @Override
    protected boolean canRead(@Nullable MediaType mediaType) {
        return super.canRead(mediaType);
    }

    @Override
    public boolean canRead(Class<?> clazz, @Nullable MediaType mediaType) {
        return super.canRead(clazz, mediaType);
    }

    @Override
    public byte[] readInternal(Class<? extends byte[]> clazz, HttpInputMessage message) throws IOException {
        return super.readInternal(clazz, message);
    }
}
