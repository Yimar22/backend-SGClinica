package com.shelter.animalback.contract.api.animal;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import co.edu.icesi.emt.auth.application.controller.user.UserController;
import co.edu.icesi.emt.auth.application.service.user.UserService;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("SGClinicaUsers")
@PactBroker(
        url = "${PACT_BROKER_BASE_URL}",
        authentication = @PactBrokerAuth(token = "${PACT_BROKER_TOKEN}") )
@ExtendWith(MockitoExtension.class)
public class UserTest {

    // Declare controller and mocks
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    // Inject controller in context

    @BeforeEach
    public void changeContext(PactVerificationContext context) {
        MockMvcTestTarget testTarget = new MockMvcTestTarget();
        testTarget.setControllers(userController);
        context.setTarget(testTarget);
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("has users")
    public void listUsers() {
        User user = new User();
        user.setFullName("Diego Torre");
        user.setPassword("1234");
        user.setUsername("DiegoTor");
        List<User> users = new ArrayList<User>();
        users.add(user);
        Mockito.when(userService.getAll()).thenReturn(users);
    }

    @State("has user to delete")
    public void deleteUser() {
        doNothing().when(userService).deleteUser(anyLong());
    }

    @State("has user to update")
    public void updateUser() {
        User user = new User();
        user.setFullName("Diego Torre");
        user.setPassword("1234");
        user.setUsername("DiegoTor");
        when(userService.updateUser(any(String.class),any(String.class),any(String.class))).thenReturn(user);
    }
}