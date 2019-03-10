package com.semeniuta.services.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Product;
import com.semeniuta.services.ProductService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;

    private ProductService productService;

    private Product product;


    @Before
    public void init(){
        productService = new ProductServiceImpl(productDao);
        product = new Product("name",new BigDecimal(122.3));
    }

    @Test
    public void addProductSuccessTest() {
        //given
        Mockito.when(productDao.addProduct(product)).thenReturn(true);
        //when
        boolean result = productService.addProduct(product.getName(), product.getPrice());
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void addProductFailedTest() {
        //given
        Mockito.when(productDao.addProduct(product)).thenReturn(false);
        //when
        boolean result = productService.addProduct(product.getName(), product.getPrice());
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void showProductsIfExistTest() {
        //given
        List<Product> products = new ArrayList<>();
        for (int i=0; i<5; i++){
            Product newProduct = new Product(product.getName()+i, product.getPrice());
            products.add(newProduct);
        }
        Mockito.when(productDao.getAllProducts()).thenReturn(products);
        //when
        List<Product> result = productService.showProducts();
        //then
        Assert.assertEquals(products, result);
    }


    @Test
    public void showProductsIfNoExistTest() {
        //given
        Mockito.when(productDao.getAllProducts()).thenReturn(null);
        //when
        List<Product> result = productService.showProducts();
        //then
        Assert.assertNull(result);
    }

    @Test
    public void editProductSuccessTest() {
        //given
        long id=12l;
        Mockito.when(productDao.editProduct(id,product.getName(),product.getPrice())).thenReturn(true);
        //when
        boolean result = productService.editProduct(id, product.getName(), product.getPrice());
        //then
        Assert.assertTrue(result);
    }


    @Test
    public void editProductFailedTest() {
        //given
        long id=12l;
        Mockito.when(productDao.editProduct(id,product.getName(),product.getPrice())).thenReturn(false);
        //when
        boolean result = productService.editProduct(id, product.getName(), product.getPrice());
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void deleteProductSuccessTest() {
        //given
        long id=12l;
        Mockito.when(productDao.deleteProduct(id)).thenReturn(true);
        //when
        boolean result = productService.deleteProduct(id);
        //then
        Assert.assertTrue(result);
    }


    @Test
    public void deleteProductFailedTest() {
        //given
        long id=12l;
        Mockito.when(productDao.deleteProduct(id)).thenReturn(false);
        //when
        boolean result = productService.deleteProduct(id);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void isProductExistForExistentProductTest() {
        //given
        long id = 12l;
        Mockito.when(productDao.findProduct(id)).thenReturn(product);
        //when
        boolean result = productService.isProductExist(id);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void isProductExistForNonExistentProductTest() {
        //given
        long id = 12l;
        Mockito.when(productDao.findProduct(id)).thenReturn(null);
        //when
        boolean result = productService.isProductExist(id);
        //then
        Assert.assertFalse(result);
    }

    @After
    public void tearDown(){
        productService = null;
    }

}