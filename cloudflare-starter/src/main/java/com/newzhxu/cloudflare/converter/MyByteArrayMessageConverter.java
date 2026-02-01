package com.newzhxu.cloudflare.converter;

import lombok.NonNull;
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
    public void setSupportedMediaTypes(@NonNull List<MediaType> supportedMediaTypes) {
        ArrayList<MediaType> mediaTypes = new ArrayList<>(supportedMediaTypes);
        super.setSupportedMediaTypes(Collections.unmodifiableList(mediaTypes));
    }

    @Override
    protected boolean canRead(@Nullable MediaType mediaType) {
        return super.canRead(mediaType);
    }

    @Override
    public boolean canRead(@NonNull Class<?> clazz, @Nullable MediaType mediaType) {
        return super.canRead(clazz, mediaType);
    }

    @Override
    public byte @NonNull [] readInternal(@NonNull Class<? extends byte[]> clazz, @NonNull HttpInputMessage message) throws IOException {
        return super.readInternal(clazz, message);
    }
}
