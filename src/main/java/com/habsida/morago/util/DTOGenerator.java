package com.habsida.morago.util;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DTOGenerator {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String entityPackage = "com.habsida.morago.model.entity";
        String dtoPackage = "com.habsida.morago.model.dto";
        String sourcePath = "src/main/java/";
        String entityPath = sourcePath + entityPackage.replace('.', '/');
        String dtoPath = sourcePath + dtoPackage.replace('.', '/');

        // Create DTO directory if it doesn't exist
        Files.createDirectories(Paths.get(dtoPath));

        // Get all entity classes
        List<String> entityClasses = Files.walk(Paths.get(entityPath))
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString().replace(".java", ""))
                .collect(Collectors.toList());

        // Generate DTO for each entity class
        for (String className : entityClasses) {
            generateDTO(entityPackage, dtoPackage, className, entityPath, dtoPath);
        }
    }

    public static void generateDTO(String entityPackage, String dtoPackage, String className, String entityPath, String dtoPath) throws ClassNotFoundException, IOException {
        Class<?> clazz = Class.forName(entityPackage + "." + className);
        String dtoClassName = className + "DTO";
        StringBuilder sb = new StringBuilder();

        // Read the entity file to extract import statements
        List<String> lines = Files.readAllLines(Paths.get(entityPath + "/" + className + ".java"));
        List<String> importStatements = lines.stream()
                .filter(line -> line.startsWith("import "))
                .collect(Collectors.toList());

        sb.append("package ").append(dtoPackage).append(";\n\n");
        sb.append("import lombok.Data;\n");
        importStatements.forEach(importStatement -> sb.append(importStatement).append("\n"));
        sb.append("\n@Data\n");
        sb.append("public class ").append(dtoClassName).append(" {\n");

        for (Field field : clazz.getDeclaredFields()) {
            sb.append("    private ").append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(";\n");
        }

        sb.append("}\n");

        // Write the DTO file
        FileWriter fileWriter = new FileWriter(dtoPath + "/" + dtoClassName + ".java");
        fileWriter.write(sb.toString());
        fileWriter.close();

        System.out.println(dtoClassName + " generated successfully.");
    }
}
