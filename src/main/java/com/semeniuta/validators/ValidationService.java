package com.semeniuta.validators;

import com.semeniuta.exceptions.BusinessExceptions;

public interface ValidationService {
    /**
     * Validate entered Age of client
     *
     * @param age - int - age of a client
     * @throws BusinessExceptions
     */
    void validateAge(int age) throws BusinessExceptions;

    /**
     * Validate entered value
     *
     * @param input - String
     * @return true if entered value is integer, otherwise - false
     */
    boolean readInt(String input);

    /**
     * Validate entered phone number
     *
     * @param inputPhone - String
     * @throws BusinessExceptions
     */
    void readPhone(String inputPhone) throws BusinessExceptions;

    /**
     * Validate entered email address
     *
     * @param inputEmail - String
     * @throws BusinessExceptions
     */
    void readEmail(String inputEmail) throws BusinessExceptions;

    /**
     * Validate client by phone number
     *
     * @param phone - String
     * @throws BusinessExceptions
     */
    void validateClient(String phone) throws BusinessExceptions;

    /**
     * Validate client id, check if entered id is exist
     *
     * @param id - long - client id
     * @throws BusinessExceptions
     */
    void validateClientId(long id) throws BusinessExceptions;

    /**
     * Validate product id, check if entered id is exist
     *
     * @param id - long - product id
     * @throws BusinessExceptions
     */
    void validateProductId(long id) throws BusinessExceptions;
}
