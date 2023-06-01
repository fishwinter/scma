package com.company.scma.service.impl;

import com.company.scma.service.validateservice.CommonValidateService;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Iterator;
import java.util.Set;

@Service
public class CommonValidateServiceImpl implements CommonValidateService {
    @Override
    public boolean validateAnnotation(Object obj) {
        Set<ConstraintViolation<Object>> validate = Validation.buildDefaultValidatorFactory().getValidator().validate(obj);
        Iterator<ConstraintViolation<Object>> iterator = validate.iterator();
        while (iterator.hasNext()){
            return false;
        }
        return true;
    }
}
