package gendhiramona.validation;

import gendhiramona.validation.extractor.DataIntegerValueExtractor;
import gendhiramona.validation.extractor.DataValueExtractor;
import gendhiramona.validation.extractor.EntryValueExtractorKey;
import gendhiramona.validation.extractor.EntryValueExtractorValue;
import jakarta.validation.*;
import jakarta.validation.executable.ExecutableValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;

public abstract class AbstractValidatorTest {

    protected ValidatorFactory validatorFactory;
    protected Validator validator;
    protected ExecutableValidator executableValidator;
    protected MessageInterpolator messageInterpolator;

    @BeforeEach
    void setUp() {
//        validatorFactory = Validation.buildDefaultValidatorFactory();
        validatorFactory = Validation.byDefaultProvider().configure()
                .addValueExtractor(new DataValueExtractor())
                .addValueExtractor(new EntryValueExtractorKey())
                .addValueExtractor(new EntryValueExtractorValue())
                .addValueExtractor(new DataIntegerValueExtractor())
                .buildValidatorFactory();
        validator = validatorFactory.getValidator();
        executableValidator = validator.forExecutables();
        messageInterpolator = validatorFactory.getMessageInterpolator();
    }

    @AfterEach
    void tearDown() {
        validatorFactory.close();
    }

    void validate(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        for (ConstraintViolation<Object> violation : violations) {
            System.out.println("Message: " + violation.getMessage());
            System.out.println("Leaf Bean: " + violation.getLeafBean());
            System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value: " + violation.getInvalidValue());
            System.out.println("Property Path: " + violation.getPropertyPath());
            System.out.println("=====================================");
        }
    }

    void validateWithException(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    void validateWithGroups(Object o, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o, groups);
        for (ConstraintViolation<Object> violation : violations) {
            System.out.println("Message: " + violation.getMessage());
            System.out.println("Leaf Bean: " + violation.getLeafBean());
            System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid Value: " + violation.getInvalidValue());
            System.out.println("Property Path: " + violation.getPropertyPath());
            System.out.println("=====================================");
        }
    }
}
