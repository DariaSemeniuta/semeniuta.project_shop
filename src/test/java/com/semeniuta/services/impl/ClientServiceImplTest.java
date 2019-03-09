package com.semeniuta.services.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.dao.impl.ClientDaoDBImpl;
import com.semeniuta.domain.Client;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {


    //private ClientDaoDBImpl clientDaoDB = new ClientDaoDBImpl(){
    @Mock
    private ClientDao clientDaoDB ;//= Mockito.mock(ClientDao.class);

    private ClientServiceImpl clientService;

    @Before
    public void init(){
          clientService = new ClientServiceImpl(clientDaoDB);
    }



    @Ignore
    @Test
    public void createClientWithFullParametersTest() {
        //given

        String name = "test";
        String surname = "test";
        int age = 43;
        String phone = "0671236544";
        String email = "test@tt.tt";
        Long id = 12l;
        Mockito.when(clientDaoDB.addClient(new Client(name,surname,age,email,phone))).thenReturn(id);
        //when
        boolean result = clientService.createClient(name,surname,age,email,phone);

        //then
        Assert.assertEquals(false, result);
    }

    @Test
    public void updateClientSuccessTest(){
        //given
        String name = "test";
        String surname = "test";
        int age = 43;
        String phone = "0671236544";
        String email = "test@tt.tt";
        Long id = 12l;
        Mockito.when(clientDaoDB.editClient(id, name,surname,age,email,phone)).thenReturn(true);
        //when
        boolean result = clientService.updateClient(id,name,surname,age,email,phone);
        //then
        Assert.assertTrue(result);

    }

    @Test
    public void updateClientFailedTest(){
        //given
        String name = "test";
        String surname = "test";
        int age = 43;
        String phone = "0671236544";
        String email = "test@tt.tt";
        Long id = 12l;
        Mockito.when(clientDaoDB.editClient(id, name,surname,age,email,phone)).thenReturn(false);
        //when
        boolean result = clientService.updateClient(id,name,surname,age,email,phone);
        //then
        Assert.assertFalse(result);

    }


    @After
    public void tearDown(){
        clientService=null;
    }
}