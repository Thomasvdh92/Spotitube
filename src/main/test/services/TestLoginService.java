package services;

import datasource.ITokenDAO;
import datasource.IOwnerDAO;
import domain.Token;
import domain.Owner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TestLoginService {

    @Mock
    private IOwnerDAO OwnerDAO;

    @Mock
    private ITokenDAO tokenDAO;

    @InjectMocks
    LoginService loginService;

    private Owner owner;
    private String Ownername, password;

    @Before
    public void setUp() throws Exception {
        loginService = new LoginService();
        initMocks(this);
        owner = mock(Owner.class);
        Ownername = "owner";
        password = "password";
        when(owner.getUsername()).thenReturn(Ownername);
        when(owner.getPassword()).thenReturn(password);
    }


    @Test
    public void testAthenticateCorrect() {

        when(OwnerDAO.read(Ownername)).thenReturn(owner);

        assert loginService.authenticate(Ownername, password) != null;
    }

    @Test
    public void testAuthenticateIncorrect() {
        when(OwnerDAO.read(Ownername)).thenReturn(null);

        assert loginService.authenticate(Ownername, password) == null;
    }

    @Test
    public void testLogin() {
        String body = "{'owner':'owner', 'password':'password'}";
        when(OwnerDAO.read(Ownername)).thenReturn(owner);
        Mockito.doNothing().when(tokenDAO).insert(any(Token.class));
        Response r = loginService.login(body);
        assert r.getStatus() == 200;
    }

    @Test
    public void testLoginIncorrect() {
        String body = "{'owner':'false', 'password':'flase'}";
        when(OwnerDAO.read(Ownername)).thenReturn(owner);
        Mockito.doNothing().when(tokenDAO).insert(any(Token.class));
        Response r = loginService.login(body);
        assert r.getStatus() == 401;
    }


}