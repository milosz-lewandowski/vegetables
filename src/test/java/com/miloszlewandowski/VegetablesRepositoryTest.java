package com.miloszlewandowski;

import com.miloszlewandowski.micronaut.VegetableCommand;
import com.miloszlewandowski.micronaut.VegetableRepositoryImpl;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest(startApplication = false)
public class VegetablesRepositoryTest {

    @Inject
    VegetableRepositoryImpl vegetableRepository;

    @Test
    void methodsValidateBlankParameter() {
        Executable e = () -> vegetableRepository.create(new VegetableCommand(""));
        assertThrows(ConstraintViolationException.class, e);
    }
}
