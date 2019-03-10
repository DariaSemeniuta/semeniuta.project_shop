package com.semeniuta.validators;

import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import org.junit.Assert;
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

    @Test(expected = NumberFormatException.class)
    public void validateAgeNotIntegerValue() throws BusinessExceptions {
        String age ="25k";
        validationService.validateAge(Integer.parseInt(age));
    }

    @Test
    public void readIntWithCorrectValueTest(){
        //given
        String value = "123";
        //when
        boolean result = validationService.readInt(value);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void readIntWithIncorrectValueTest(){
        //given
        String value = "12w3";
        //when
        boolean result = validationService.readInt(value);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void readIntWithSpecSymbolsValueTest(){
        //given
        String value = "1 23";
        //when
        boolean result = validationService.readInt(value);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void readIntWithLongValueTest(){
        //given
        String value = "123l";
        //when
        boolean result = validationService.readInt(value);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void readPhoneWithCountryCodeAnd067OperatorTest() throws BusinessExceptions{
        //given
        String phone = "+380677896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }

    @Test
    public void readPhoneWithCountryCodeAnd097OperatorTest() throws BusinessExceptions{
        //given
        String phone = "+380977896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }


    @Test
    public void readPhoneWithCountryCodeAnd050OperatorTest() throws BusinessExceptions{
        //given
        String phone = "+380507896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }


    @Test
    public void readPhoneWithoutCountryCodeAnd067OperatorTest() throws BusinessExceptions{
        //given
        String phone = "0677896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }


    @Test
    public void readPhoneWithoutCountryCodeAnd097OperatorTest() throws BusinessExceptions{
        //given
        String phone = "0977896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }


    @Test
    public void readPhoneWithoutCountryCodeAnd050OperatorTest() throws BusinessExceptions{
        //given
        String phone = "0507896655";
        //when
        validationService.readPhone(phone);
        //then there are no exception
    }

    @Test
    public void readPhoneWithCountryCodeLongerThenAllowTest() throws BusinessExceptions{
        //given
        String phone = "+30507896655";
        //when
        try {
            validationService.readPhone(phone);
            Assert.assertFalse(true);
        }
        //then
        catch (BusinessExceptions e ){
            Assert.assertEquals("Invalid phone number!", e.getMessage());
        }
    }

    @Test
    public void readPhoneWithoutCountryCodeLongerThenAllowTest() throws BusinessExceptions{
        //given
        String phone = "0507896655";
        //when
        try {
            validationService.readPhone(phone);
        }
        //then
        catch (BusinessExceptions e ){
            Assert.assertEquals("Invalid phone number!", e.getMessage());
        }
    }

}