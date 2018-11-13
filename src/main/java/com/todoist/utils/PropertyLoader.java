package com.todoist.utils;

import com.google.common.base.Preconditions;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyLoader {

    private PropertyLoader() {
    }

    private final static FilenameFilter FILENAME_FILTER = (dir, name) -> name.endsWith(".properties");

    /**
     * System properties has higher priority (because they are passed from Jenkins)
     */
    public static Properties loadAll() {
        Properties system = loadSystemProperties();
        Properties mainConfigFile = loadFromMainConfigFile();
        Properties folders = loadFromFolder(mainConfigFile.getProperty("test_data_folder_properties"));

        folders.putAll(mainConfigFile);
        folders.putAll(system);
        return folders;
    }

    public static Properties loadSystemProperties() {
        final String DEFAULT = "{default}"; // properties in pom has 'default' value if not passed from jenkins
        Properties systemProperties = System.getProperties();
        Map<String, String> topProps;

        topProps = systemProperties.stringPropertyNames().stream()
                .filter(name -> !systemProperties.getProperty(name).equalsIgnoreCase(DEFAULT))
                .collect(Collectors.toMap(key -> key, systemProperties::getProperty));

//        topProps.forEach((n, v) -> logger.trace(String.format(">> %-25s = [%s]", n, v)));

        Properties topLevelProperties = new Properties();
        topLevelProperties.putAll(topProps);

        return topLevelProperties;
    }

    public static Properties loadFromMainConfigFile() {
        Properties properties = new Properties();
        URL props = ClassLoader.getSystemResource("config.properties");
        try {
            properties.load(props.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Loads a property list (key and element pairs) from the all *.property files from supplied folder. Subfolders are
     * not processed. WARNING: Files load order is not determined
     *
     * @param rootFolder pathname to folder from which properties will be loaded.
     * @return combined property list from all *.property files of the folder.
     * @throws IllegalArgumentException if {@code folder} is not a directory or property duplicated in any of files in
     *                                  this
     *                                  directory.
     */
    public static Properties loadFromFolder(String rootFolder) {
        File folder = Paths.get(rootFolder).toFile();
        Preconditions.checkArgument(folder.isDirectory(), "The [%s] is not a directory.", rootFolder);

        Properties properties = new Properties();
        for (File file : folder.listFiles(FILENAME_FILTER)) {
            Properties props = loadFromFile(file);

            Enumeration e = props.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                if (properties.containsKey(key)) {
                    throw new IllegalArgumentException(
                            String.format("Property with key [%s] duplicated in file [%s]", key, file));
                }
            }

            properties.putAll(props);
        }

        return properties;
    }

    public static Properties loadFromFile(File file) {

        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(file);
             Reader reader = new InputStreamReader(inputStream, "UTF-8")) {
            properties.load(reader);
        } catch (IOException ex) {
            throw new IllegalArgumentException(
                    String.format("Failed to load properties from file [%s].", file), ex);
        }

        return properties;
    }

}
