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

import java.sql.SQLException;

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
    private String username, password;

    @Before
    public void setUp() throws Exception {
        loginService = new LoginService();
        initMocks(this);
        owner = mock(Owner.class);
        username = "user";
        password = "password";
        when(owner.getUsername()).thenReturn(username);
        when(owner.getPassword()).thenReturn(password);
    }


    @Test
    public void testAthenticateCorrect() {

        when(OwnerDAO.read(username)).thenReturn(owner);

        assert loginService.authenticate(username, password) != null;
    }

    @Test
    public void testAuthenticateIncorrect() {
        when(OwnerDAO.read(username)).thenReturn(null);
        assert !loginService.authenticate(username, password);
    }

    @Test
    public void testLogin() throws SQLException {
        String body = "{'user':'user', 'password':'password'}";
        when(OwnerDAO.read(username)).thenReturn(owner);
        Mockito.doNothing().when(tokenDAO).insert(any(Token.class));
        Response r = loginService.login(body);
        assert r.getStatus() == 200;
    }

    @Test
    public void testLoginIncorrect() throws SQLException {
        String body = "{'user':'false', 'password':'false'}";
        when(OwnerDAO.read(username)).thenReturn(owner);
        Mockito.doNothing().when(tokenDAO).insert(any(Token.class));
        Response r = loginService.login(body);
        assert r.getStatus() == 401;
    }


}