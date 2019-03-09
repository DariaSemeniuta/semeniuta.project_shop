package com.semeniuta.validators;

import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceImplTest {

    @Mock
    private ClientService clientService;
    @Mock
    private ProductService productService;
    @Mock
    private OrderService orderService;
    private ValidationService validationService = new ValidationServiceImpl(clientService,productService,orderService);


    @Test(expected = BusinessExceptions.class)
    public void validateAgeMoreThenAllow() throws BusinessExceptions {
        int age =300;
        validationService.validateAge(age);
    }
    @Test
    public void validateAgeCorrectValue() throws BusinessExceptions {
        int age =25;
        validationService.validateAge(age);
    }
    


}