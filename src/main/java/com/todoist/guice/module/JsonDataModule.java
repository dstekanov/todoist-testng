package com.todoist.guice.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JsonDataModule<T> extends AbstractModule {

    private final Logger logger = LogManager.getLogger(getClass());
    private ObjectMapper objectMapper;
    private Path rootFolder;

    private List<ObjectResult> objects;

    public JsonDataModule(ObjectMapper objectMapper, Path rootFolder) {
        this.objectMapper = objectMapper;
        this.rootFolder = rootFolder;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void configure() {
        try {
            objects = load();
        } catch (IOException | ClassNotFoundException e) {
            binder().addError(e);
        }

        // Suppress unchecked warning... toInstance receive correct object
        // @Inject @Named("fileName") Object variable; 1
        objects.forEach((o) ->
                bind(o.getClazz())
                        .annotatedWith(Names.named(o.getFileName()))
                        .toInstance(o.getTargetObject()));

    }


    private List<ObjectResult> load() throws IOException, ClassNotFoundException {
        Map<String, File> files = new HashMap<>();

        File folder = rootFolder.toFile();
        if (!folder.exists())
            throw new RuntimeException(String.format("Folder [%s] does not exist.", rootFolder));

        for (File file : Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".json")))) {
            String fileName = Files.getNameWithoutExtension(file.getName());
            files.put(fileName, file);
        }

        List<ObjectResult> objectsResult = new ArrayList<>();
        for (String fileName : files.keySet()) {
            File file = files.get(fileName);
            String type = objectMapper.readTree(file).get("@type").asText();
            @SuppressWarnings("unchecked")
            Class<T> toClass = (Class<T>) Class.forName(type);

            T object = objectMapper.readValue(file, toClass);

            objectsResult.add(new ObjectResult<T>()
                    .setFileName(fileName)
                    .setTargetObject(object)
                    .setClazz(toClass));
        }

        if (objectsResult.isEmpty())
            logger.warn(String.format("JSON files not found in folder [%s]", folder));

        return objectsResult;
    }

    class ObjectResult<P> {

        private String fileName;
        private P target;
        private Class<P> clazz;

        public ObjectResult<P> setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public ObjectResult<P> setTargetObject(P object) {
            this.target = object;
            return this;
        }

        public ObjectResult<P> setClazz(Class<P> clazz) {
            this.clazz = clazz;
            return this;
        }

        public String getFileName() {
            return fileName;
        }

        public P getTargetObject() {
            return target;
        }

        public Class<P> getClazz() {
            return clazz;
        }
    }

}
