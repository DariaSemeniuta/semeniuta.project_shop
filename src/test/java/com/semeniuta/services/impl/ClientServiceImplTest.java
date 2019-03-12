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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {


    //private ClientDaoDBImpl clientDaoDB = new ClientDaoDBImpl(){
    @Mock
    private ClientDao clientDaoDB;//= Mockito.mock(ClientDao.class);

    private ClientServiceImpl clientService;

    @Before
    public void init() {
        clientService = new ClientServiceImpl(clientDaoDB);
    }

    @Test
    public void createClientWithFullParametersTest() {
        //given

        String name = "test";
        String surname = "test";
        int age = 43;
        String phone = "0671236544";
        String email = "test@tt.tt";
        Long id = 12l;
        Client client = new Client(name, surname, age, email, phone);
        Mockito.when(clientDaoDB.addClient(client)).thenReturn(id);
        //when
        boolean result = clientService.createClient(name, surname, age, email, phone);

        //then
        Assert.assertEquals(true, result);
    }

    @Test
    public void createClientWasNotCreatedInDbTest() {
        //given

        String name = "test";
        String surname = "test";
        int age = 43;
        String phone = "0671236544";
        String email = "test@tt.tt";
        Long id = 0l;
        Client client = new Client(name, surname, age, email, phone);
        Mockito.when(clientDaoDB.addClient(client)).thenReturn(id);
        //when
        boolean result = clientService.createClient(name, surname, age, email, phone);

        //then
        Assert.assertEquals(false, result);
    }

    @Test
    public void updateClientSuccessTest() {
        //given
        String name = "test";
        String surname = "test";
        int age = 43;
        String phone = "0671236544";
        String email = "test@tt.tt";
        Long id = 12l;
        Mockito.when(clientDaoDB.editClient(id, name, surname, age, email, phone)).thenReturn(true);
        //when
        boolean result = clientService.updateClient(id, name, surname, age, email, phone);
        //then
        Assert.assertTrue(result);

    }

    @Test
    public void updateClientFailedTest() {
        //given
        String name = "test";
        String surname = "test";
        int age = 43;
        String phone = "0671236544";
        String email = "test@tt.tt";
        Long id = 12l;
        Mockito.when(clientDaoDB.editClient(id, name, surname, age, email, phone)).thenReturn(false);
        //when
        boolean result = clientService.updateClient(id, name, surname, age, email, phone);
        //then
        Assert.assertFalse(result);

    }

    @Test
    public void deleteClientSuccessTest() {
        //given
        Long id = 13l;
        Mockito.when(clientDaoDB.deleteClient(id)).thenReturn(true);
        //when
        boolean result = clientService.deleteClient(id);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void deleteClientFailedTest() {
        //given
        Long id = 13l;
        Mockito.when(clientDaoDB.deleteClient(id)).thenReturn(false);
        //when
        boolean result = clientService.deleteClient(id);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void showClientsTest() {
        //given
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Client client = new Client(i, "test" + i, "test", 25, "te" + i + "t@tt.tt", "067123445" + i);
            clients.add(client);
        }
        Mockito.when(clientDaoDB.getAllClient()).thenReturn(clients);
        //when
        List<Client> result = clientService.showClients();
        //then
        Assert.assertEquals(clients, result);
    }

    @Test
    public void showClientsNullTest() {
        //given
        Mockito.when(clientDaoDB.getAllClient()).thenReturn(null);
        //when
        List<Client> result = clientService.showClients();
        //then
        Assert.assertNull(result);
    }

    @Test
    public void showClientInfoTest() {
        //given
        long id = 12l;
        Client client = new Client(id, "test", "test", 25, "test@tt.tt", "067123445");
        Mockito.when(clientService.showClientInfo(id)).thenReturn(client);
        //when
        Client result = clientService.showClientInfo(id);
        //then
        Assert.assertEquals(client, result);
    }


    @Test
    public void showClientInfoNullTest() {
        //given
        long id = 12l;
        Mockito.when(clientService.showClientInfo(id)).thenReturn(null);
        //when
        Client result = clientService.showClientInfo(id);
        //then
        Assert.assertNull(result);
    }

    @Test
    public void isClientExistForExistentClientTest() {
        //given
        String phone = "067123445";
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            Client client = new Client(i, "test" + i, "test", 25, "te" + i + "t@tt.tt", phone + i);
            clients.add(client);
        }
        Mockito.when(clientDaoDB.getAllClient()).thenReturn(clients);
        //when
        boolean result = clientService.isClientExist(phone + 1);
        //then
        Assert.assertTrue(result);

    }

    @Test
    public void isClientExistForNonExistentClientTest() {
        //given
        String phone = "067123445";
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            Client client = new Client(i, "test" + i, "test", 25, "te" + i + "t@tt.tt", phone + i);
            clients.add(client);
        }
        Mockito.when(clientDaoDB.getAllClient()).thenReturn(clients);
        //when
        boolean result = clientService.isClientExist(phone + 9);
        //then
        Assert.assertFalse(result);

    }

    @Test
    public void isIdExistForExistentClientTest() {
        //given
        long id = 13l;
        Mockito.when(clientDaoDB.findClient(id)).thenReturn(new Client(id, null, null, 1, null, null));
        //when
        boolean result = clientService.isIdExist(id);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void isIdExistForNonExistentClientTest() {
        //given
        long id = 13l;
        Mockito.when(clientDaoDB.findClient(id)).thenReturn(null);
        //when
        boolean result = clientService.isIdExist(id);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void getClientEmailByPhoneForExistentClientTest() {
        //given
        String phone = "067123445";
        String email = "tt@tt.t";
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            Client client = new Client(i, "test" + i, "test", 25, email + i, phone + i);
            clients.add(client);
        }
        Mockito.when(clientDaoDB.getAllClient()).thenReturn(clients);
        //when
        int suf = 2;
        String result = clientService.getClientEmailByPhone(phone + suf);
        //then
        Assert.assertEquals(email + suf, result);
    }

    @Test
    public void getClientEmailByPhoneForNonExistentClientTest() {
        //given
        String phone = "0671234456";
        Mockito.when(clientDaoDB.getAllClient()).thenReturn(null);
        //when
        String result = clientService.getClientEmailByPhone(phone);
        //then
        Assert.assertNull(result);
    }

    @Test
    public void getClientIdTest() {
        //when
        long result = clientService.getClientId();
        //then
        Assert.assertEquals(0, result);
    }

    @After
    public void tearDown() {
        clientService = null;
    }
}