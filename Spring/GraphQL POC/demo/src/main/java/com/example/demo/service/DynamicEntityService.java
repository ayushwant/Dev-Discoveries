package com.example.demo.service;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class DynamicEntityService {

    public void generateEntities(String packageName) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            MetadataSources metadataSources = new MetadataSources(registry);
            MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();

            Collection<PersistentClass> entityBindings = metadataBuilder.build().getEntityBindings();

            for (PersistentClass persistentClass : entityBindings) {
                String className = persistentClass.getClassName();

                // Print the entity class name
                System.out.println("Entity Class: " + className);

                // Generate entity class source code dynamically
                StringBuilder entitySourceCode = new StringBuilder();
                entitySourceCode.append("package ").append(packageName).append(";\n\n");
                entitySourceCode.append("import javax.persistence.*;\n\n");
                entitySourceCode.append("@Entity\n");
                entitySourceCode.append("public class ").append(className).append(" {\n\n");

                // Process each property (column)
                for (Iterator it = persistentClass.getPropertyIterator(); it.hasNext(); ) {
                    Property property = (Property) it.next();
                    String propertyName = property.getName();
                    Column column = (Column) property.getColumnIterator().next();

                    // Print the property name and type
                    System.out.println("  Property: " + propertyName + " (Type: " + column.getSqlType() + " - "
                            +column.getName() + ")");

                    // Generate entity property source code dynamically
                    entitySourceCode.append("    @Column(name = \"").append(column.getName()).append("\")\n");
                    entitySourceCode.append("    private ").append(column.getName()).append(column.getSqlType())
                            .append(" ").append(propertyName).append(";\n\n");
                }

                entitySourceCode.append("}\n");

                // Print the dynamically generated entity class source code
                System.out.println("Generated Entity Source Code:\n" + entitySourceCode);

                // You can save the source code to a file or compile it at runtime if needed
            }
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
