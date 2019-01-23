package ru.basejava.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.basejava.model.AbstractSection;

import java.io.Reader;
import java.io.Writer;

public class JsonBinder {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(AbstractSection.class, new SectionAdapterForJson())
            .create();

    public static <S> S read(Reader reader, Class<S> clazz) {
        return gson.fromJson(reader, clazz);
    }

    public static <S> void write(Writer writer, S object) {
        gson.toJson(object, writer);
    }
}