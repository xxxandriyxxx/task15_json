package com.epam.model.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

import java.io.File;
import java.io.IOException;

public class JSONValidator {

    public static boolean validate(File jsonFile, File schemaFile)
            throws IOException, ProcessingException {
        final JsonNode data = JsonLoader.fromFile(jsonFile);
        final JsonNode schema = JsonLoader.fromFile(schemaFile);
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonValidator validator = factory.getValidator();
        ProcessingReport report = validator.validate(schema, data);
        return report.isSuccess();
    }

}
