package com.yejunfeng.validator;

import com.yejunfeng.PO.StudentPO;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidate implements Validator {

    public boolean supports(Class<?> aClass) {
        return StudentPO.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {

    }

}
