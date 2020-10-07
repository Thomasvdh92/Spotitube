package services;

import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;
import nl.han.ica.oose.dea.spotitube.datasource.ITokenDAO;
import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.domain.Token;
import nl.han.ica.oose.dea.spotitube.exceptions.ApplicationException;
import nl.han.ica.oose.dea.spotitube.services.LoginService;
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

        assert loginService.authenticate(Ownername, password);
    }

    @Test
    public void testAuthenticateIncorrect() {
        when(OwnerDAO.read(Ownername)).thenReturn(null);

        assert !loginService.authenticate(Ownername, password);
    }

    @Test
    public void testLogin() throws ApplicationException {
        String body = "{'user':'owner', 'password':'password'}";
        when(OwnerDAO.read(Ownername)).thenReturn(owner);
        Mockito.doNothing().when(tokenDAO).insert(any(Token.class));
        Response r = loginService.login(body);
        assert r.getStatus() == 200;
    }

    @Test
    public void testLoginIncorrect() throws ApplicationException {
        String body = "{'user':'false', 'password':'false'}";
        when(OwnerDAO.read(Ownername)).thenReturn(owner);
        Mockito.doNothing().when(tokenDAO).insert(any(Token.class));
        Response r = loginService.login(body);
        assert r.getStatus() == 401;
    }


}