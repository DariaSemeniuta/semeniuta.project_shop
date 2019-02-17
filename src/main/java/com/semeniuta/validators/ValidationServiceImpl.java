package com.semeniuta.validators;

import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.ProductService;

public class ValidationServiceImpl implements ValidationService {

    private final ClientService clientService;
    private final ProductService productService;

    public ValidationServiceImpl(ClientService clientService, ProductService productService) {
        this.clientService = clientService;
        this.productService = productService;
    }

    @Override
    public void validateAge(int age) throws BusinessExceptions {
        if(age<0||age>200){
            throw new BusinessExceptions("Incorrect age!");
        }
    }

    @Override
    public  boolean readInt(String inputInt){
        try {
            new Integer(inputInt);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public void readPhone(String inputPhone) throws BusinessExceptions {
        String[] regex = {"^[+]\\d{12}", "^[0]\\d{9}", "^[+]\\d{1}[(]\\d{3}[)]\\d{3}\\d{2}\\d{2}", "^[(]\\d{3}[)]\\d{3}\\d{2}\\d{2}", "^[(]\\d{3}[)]\\d{3}[-]\\d{2}[-]\\d{2}", "^[+]\\d{1}[(]\\d{3}[)]\\d{3}[-]\\d{2}[-]\\d{2}", "^[+]\\d{1}[-]\\d{3}[-]\\d{3}[-]\\d{2}[-]\\d{2}", "^[0]\\d{2}[-]\\d{3}[-]\\d{2}[-]\\d{2}"};
        for(int i = 0; i < regex.length; ++i){
            if(inputPhone.matches(regex[i])){
                return;
            }
        }
        throw new BusinessExceptions("Invalid phone number!");
    }

    @Override
    public void readEmail(String inputEmail) throws BusinessExceptions {

        String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        if (!(inputEmail.matches(regex))){
            throw new BusinessExceptions("Invalid email address");
        }

    }

    @Override
    public void validateClient(String phone) throws BusinessExceptions {
        if(clientService.isClientExist(phone)){
            throw new BusinessExceptions("Client is already exist");
        }

    }

    @Override
    public void validateProductId(long id) throws BusinessExceptions {
        if(!(productService.isProductExist(id))){
            throw new BusinessExceptions("There is no such product!");
        }
    }

    //TODO: add validation for id  order
}
