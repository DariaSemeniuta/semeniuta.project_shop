package com.semeniuta.validators;

import com.semeniuta.exceptions.BusinessExceptions;

public interface ValidationService {
    void validateAge(int age) throws BusinessExceptions;

    boolean readInt(String input);

    void readPhone(String inputPhone) throws BusinessExceptions;

    void readEmail(String inputEmail) throws BusinessExceptions;

    void validateClient(String phone) throws BusinessExceptions;

    void validateClientId(long id) throws BusinessExceptions;

    void validateProductId(long id) throws BusinessExceptions;
}
