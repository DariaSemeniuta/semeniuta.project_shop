package com.semeniuta.validators;

import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceImplTest {

    @Mock
    private ClientService clientService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderService orderService;

    private ValidationService validationService;

    @Before
    public void init(){
        validationService = new ValidationServiceImpl(clientService, productService, orderService);
    }


    @Test(expected = BusinessExceptions.class)
    public void validateAgeMoreThenAllow() throws BusinessExceptions {
        int age = 300;
        validationService.validateAge(age);
    }

    @Test
    public void validateAgeCorrectValue() throws BusinessExceptions {
        int age = 25;
        validationService.validateAge(age);
    }

    @Test(expected = NumberFormatException.class)
    public void validateAgeNotIntegerValue() throws BusinessExceptions {
        String age = "25k";
        validationService.validateAge(Integer.parseInt(age));
    }

    @Test(expected = BusinessExceptions.class)
    public void validateAgeNegativeValueTest() throws BusinessExceptions {
        String age = "-25";
        validationService.validateAge(Integer.parseInt(age));
    }

    @Test
    public void readIntWithCorrectValueTest() {
        //given
        String value = "123";
        //when
        boolean result = validationService.readInt(value);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void readIntWithIncorrectValueTest() {
        //given
        String value = "12w3";
        //when
        boolean result = validationService.readInt(value);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void readIntWithSpecSymbolsValueTest() {
        //given
        String value = "1 23";
        //when
        boolean result = validationService.readInt(value);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void readIntWithLongValueTest() {
        //given
        String value = "123l";
        //when
        boolean result = validationService.readInt(value);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void readPhoneWithCountryCodeAnd067OperatorTest() throws BusinessExceptions {
        //given
        String phone = "+380677896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }

    @Test
    public void readPhoneWithCountryCodeAnd097OperatorTest() throws BusinessExceptions {
        //given
        String phone = "+380977896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }


    @Test
    public void readPhoneWithCountryCodeAnd050OperatorTest() throws BusinessExceptions {
        //given
        String phone = "+380507896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }


    @Test
    public void readPhoneWithoutCountryCodeAnd067OperatorTest() throws BusinessExceptions {
        //given
        String phone = "0677896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }


    @Test
    public void readPhoneWithoutCountryCodeAnd097OperatorTest() throws BusinessExceptions {
        //given
        String phone = "0977896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }


    @Test
    public void readPhoneWithoutCountryCodeAnd050OperatorTest() throws BusinessExceptions {
        //given
        String phone = "0507896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }

    @Test(expected = BusinessExceptions.class)
    public void readPhoneWithCountryCodeLongerThenAllowTest() throws BusinessExceptions {
        //given
        String phone = "+305078966525";
        //when
        validationService.readPhone(phone);
        //then
    }

    @Test(expected = BusinessExceptions.class)
    public void readPhoneWithoutCountryCodeLongerThenAllowTest() throws BusinessExceptions {
        //given
        String phone = "05078966551";
        //when
        validationService.readPhone(phone);
        //then
    }

    @Test(expected = BusinessExceptions.class)
    public void readPhoneWithIncorrectCountryCodeTest() throws BusinessExceptions {
        //given
        String phone = "+780501112233";
        //when
        validationService.readPhone(phone);
        //then
    }

    @Test(expected = BusinessExceptions.class)
    public void readPhoneWithIncorrectOperatorCodeTest() throws BusinessExceptions {
        //given
        String phone = "+380661112233";
        //when
        validationService.readPhone(phone);
        //then
    }

    @Test(expected = BusinessExceptions.class)
    public void readPhoneWithIncorrectValueTest() throws BusinessExceptions {
        //given
        String phone = "+adaww";
        //when
        validationService.readPhone(phone);
        //then
    }

    @Test
    public void readEmailCorrectValueTest() throws BusinessExceptions {
        //given
        String email = "test123@ee.com";
        //when
        validationService.readEmail(email);
        //then
        //there are no exceptions
    }

    @Test(expected = BusinessExceptions.class)
    public void readEmailIncorrectValueTest() throws BusinessExceptions {
        //given
        String email = "test123.com";
        //when
        validationService.readEmail(email);
        //then

    }

    @Test(expected = BusinessExceptions.class)
    public void readEmailIncorrectValueWithoutDomainTest() throws BusinessExceptions {
        //given
        String email = "test123@gmail";
        //when
        validationService.readEmail(email);
        //then
    }

    @Test(expected = BusinessExceptions.class)
    public void validateClientIfClientExistTest() throws BusinessExceptions {
        //given
        String phone = "+380501234455";
        Mockito.when(clientService.isClientExist(phone)).thenReturn(true);
        //when
        validationService.validateClient(phone);
        //then

    }


    @Test
    public void validateClientIfClientDoesNotExistTest() throws BusinessExceptions {
        //given
        String phone = "+380501234455";
        Mockito.when(clientService.isClientExist(phone)).thenReturn(false);
        //when
        validationService.validateClient(phone);
        //then
        //there are n exceptions
    }

    @Test
    public void validateProductIdIfProductExistentTest() throws BusinessExceptions {
        //given
        long id = 2l;
        Mockito.when(productService.isProductExist(id)).thenReturn(true);
        //when
        validationService.validateProductId(id);
        //then
        //there are no exception
    }

    @Test(expected = BusinessExceptions.class)
    public void validateProductIdIfProductNonExistentTest() throws BusinessExceptions {
        //given
        long id = 2l;
        Mockito.when(productService.isProductExist(id)).thenReturn(false);
        //when
        validationService.validateProductId(id);
        //then
    }

    @Test
    public void validateClientIdIfClientExistentTest() throws BusinessExceptions {
        //given
        long id = 2l;
        Mockito.when(clientService.isIdExist(id)).thenReturn(true);
        //when
        validationService.validateClientId(id);
        //then
        //there are no exception
    }

    @Test(expected = BusinessExceptions.class)
    public void validateClientIdIfClientNonExistentTest() throws BusinessExceptions {
        //given
        long id = 2l;
        Mockito.when(clientService.isIdExist(id)).thenReturn(false);
        //when
        validationService.validateClientId(id);
        //then
    }

    @Test
    public void validateOrderIdIfOrderExistentTest() throws BusinessExceptions {
        //given
        long id = 2l;
        Mockito.when(orderService.isOrderExist(id)).thenReturn(true);
        //when
        validationService.validateOrderId(id);
        //then
        //there are no exception
    }

    @Test(expected = BusinessExceptions.class)
    public void validateOrderIdIfOrderNonExistentTest() throws BusinessExceptions {
        //given
        long id = 2l;
        Mockito.when(orderService.isOrderExist(id)).thenReturn(false);
        //when
        validationService.validateOrderId(id);
        //then
    }

    @Test
    public void validatePriceCorrectValueTest() throws BusinessExceptions {
        //given
        String price = "123.3";
        //when
        validationService.validatePrice(price);
        //then
        //there are no exceptions
    }


    @Test(expected = BusinessExceptions.class)
    public void validatePriceIncorrectValueTest() throws BusinessExceptions {
        //given
        String price = "123.3l";
        //when
        validationService.validatePrice(price);
        //then
        //exception
    }

    @After
    public void tearDown(){
        validationService = null;
    }


}