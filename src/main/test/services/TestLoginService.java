package services;

import nl.han.ica.oose.dea.spotitube.datasource.ITokenDAO;
import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;
import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.domain.Token;
import nl.han.ica.oose.dea.spotitube.exceptions.ApplicationException;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import nl.han.ica.oose.dea.spotitube.services.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void setUp() {
        loginService = new LoginService();
        initMocks(this);
        username = "user";
        password = "password";
        owner = new Owner(username, password);
    }



    @Test
    public void testLogin() throws ApplicationException, EntityNotFoundException, SQLException {
        Owner o = new Owner("user", "password");
        when(OwnerDAO.read(username)).thenReturn(owner);
        Mockito.doNothing().when(tokenDAO).insert(any(Token.class));
        Response r = loginService.login(o);
        assert r.getStatus() == 200;
    }

    @Test
    public void testLoginIncorrect() throws SQLException, EntityNotFoundException {
        Owner o = new Owner("user", "false");
        when(OwnerDAO.read("user")).thenReturn(owner);
        Mockito.doNothing().when(tokenDAO).insert(any(Token.class));
        ApplicationException e = assertThrows(ApplicationException.class, () -> loginService.login(o));
        assert e.getMessage().equals("Invalid credentials");
        assert e.getStatus().getStatusCode() == 401;
    }

    @Test
    public void testCatchException() throws EntityNotFoundException {
        when(OwnerDAO.read(owner.getUser())).thenThrow(EntityNotFoundException.class);
        ApplicationException e = assertThrows(ApplicationException.class, () -> loginService.login(owner));
        assert e.getMessage().equals("Invalid credentials");
        assert e.getStatus().getStatusCode() == 401;
    }
}