package prakhar.system;

import io.dropwizard.configuration.UndefinedEnvironmentVariableException;
import org.apache.commons.lang3.text.StrLookup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileVariableLookup extends StrLookup<Object> {
    private final Properties variables;
    private final boolean strict;

    /**
     * Create a new instance with strict behavior.
     */
    public PropertyFileVariableLookup(String configPath) {
        this(configPath, true);
    }



    /**
     * Create a new instance.
     *
     *
     * @param configPath
     * @param strict {@code true} if looking up undefined environment variables should throw a
     *               {@link UndefinedEnvironmentVariableException}, {@code false} otherwise.
     * @throws UndefinedEnvironmentVariableException if the environment variable doesn't exist and strict behavior
     *                                               is enabled.
     */
    public PropertyFileVariableLookup(String configPath, boolean strict) {
        if (configPath == null) throw new IllegalArgumentException("null configPath");

        try {
            this.strict = strict;
            this.variables = new Properties();
            variables.load(new FileInputStream(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws UndefinedEnvironmentVariableException if the environment variable doesn't exist and strict behavior
     *                                               is enabled.
     */
    @Override
    public String lookup(String key) {
        final String value = variables.getProperty(key);

        if (value == null && strict) {
            throw new UndefinedEnvironmentVariableException("The environment variable '" + key
                    + "' is not defined; could not substitute the expression '${"
                    + key + "}'.");
        }

        return value;
    }
}
