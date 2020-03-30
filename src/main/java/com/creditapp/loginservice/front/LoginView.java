package com.creditapp.loginservice.front;

import com.creditapp.loginservice.domain.UserDto;
import com.creditapp.loginservice.service.PasswordService;
import com.creditapp.loginservice.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Arrays;

@Route("login")
public class LoginView extends VerticalLayout {

    private UserService userService = UserService.getInstance();
    private PasswordService passwordService = new PasswordService();

    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    // private Button registrationButton = new Button("Register");
    private Button loginButton = new Button("Login");
    private TextField loginField = new TextField("Login : ");
    private PasswordField passwordField = new PasswordField("Password: ");
    private Label loginViewLabel = new Label("Login Panel");

    private static String whoIsLoggedIn;

    private LoginView() {
        //  registrationButton.addClickListener(e -> register(registrationButton));
        loginButton.addClickListener(e -> login(loginButton));
        // horizontalLayout.add(registrationButton, loginButton);
        add(loginViewLabel, loginField, passwordField, horizontalLayout);
        setAlignItems(Alignment.CENTER);
    }

    public static void setWhoIsLoggedIn(String whoIsLoggedIn) {
        LoginView.whoIsLoggedIn = whoIsLoggedIn;
    }

    public static String getWhoIsLoggedIn() {
        return whoIsLoggedIn;
    }

    /* private void register(Button button) {
         button.getUI().ifPresent(ui -> ui.navigate("registration"));
     }
 */
    private void login(Button button) {
        for (UserDto userDto : userService.getUsers()) {
            if (userDto.getLogin().equals(loginField.getValue()) && !(loginField.getValue().equals("admin"))) {
                if (Arrays.equals(userDto.getHashKey(), passwordService.hashPassword(passwordField.getValue(), userDto.getSalt()))) {
                    setWhoIsLoggedIn(userDto.getLogin());
                    Notification.show("Logged In");
                    button.getUI().ifPresent(ui -> ui.navigate("main"));
                }
            } else if (userDto.getLogin().equals(loginField.getValue()) && (loginField.getValue().equals("admin"))) {
                if (Arrays.equals(userDto.getHashKey(), passwordService.hashPassword(passwordField.getValue(), userDto.getSalt()))) {
                    setWhoIsLoggedIn(userDto.getLogin());
                    button.getUI().ifPresent(ui -> ui.navigate("adminView"));
                }
            } else {
                Notification.show("The password or login is incorrect");
            }
        }
    }
}
