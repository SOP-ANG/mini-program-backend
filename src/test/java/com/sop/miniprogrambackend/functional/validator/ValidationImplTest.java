package com.sop.miniprogrambackend.functional.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationImplTest {

    @Autowired
    private ValidationImpl validation;

    @NotBlank(message = "姓名不能为空")
    @Size(max = 10)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Test
    public void validate() {
        ValidationImplTest validationImplTest1 = new ValidationImplTest();
        ValidationResult validationResult1 = validation.validate(validationImplTest1);
        assertFalse(validationResult1.isPassed());

        ValidationImplTest validationImplTest2 = new ValidationImplTest();
        validationImplTest2.setName("test validate");
        ValidationResult validationResult2 = validation.validate(validationImplTest2);
        assertFalse(validationResult2.isPassed());

        ValidationImplTest validationImplTest3 = new ValidationImplTest();
        validationImplTest3.setName("validate");
        ValidationResult validationResult3 = validation.validate(validationImplTest3);
        System.out.println(validationResult3);
        assertTrue(validationResult3.isPassed());
    }
}