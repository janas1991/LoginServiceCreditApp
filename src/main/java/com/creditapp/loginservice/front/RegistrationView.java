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

@Route("registration")
public class RegistrationView extends VerticalLayout {

    private PasswordService passwordService = new PasswordService();
    private UserService userService = UserService.getInstance();
    private HorizontalLayout horizontalLayout = new HorizontalLayout();
    private Label registerLabel = new Label("Registering a new user");
    private VerticalLayout verticalLayout;
    private TextField userLogin = new TextField("Login: ");
    private PasswordField userPassword = new PasswordField("Password: ");
    private PasswordField confirmUserPassword = new PasswordField("Confirm Password: ");
    private TextField email = new TextField("Email : ");
    private TextField freeFunds = new TextField("free Funds : ");
    private TextField userFirstName = new TextField("First Name: ");
    private TextField userLastName = new TextField("Last Name: ");
    private TextField salary = new TextField("Salary: ");
    private TextField montlyExpenses = new TextField("Montly Expenses: ");
    private Button register = new Button("Registration");
    private Button back = new Button("Back");

    private RegistrationView() {
        register.addClickListener(e -> proceedRegistration(register));
        back.addClickListener(e -> goBack(back));
        horizontalLayout.add(back, register);
        freeFunds.setEnabled(false);
        salary.addValueChangeListener(e -> fillInfreeFundsTextField());
        montlyExpenses.addValueChangeListener(e -> fillInfreeFundsTextField());
        add(registerLabel, userLogin, userPassword, confirmUserPassword, email, userFirstName, userLastName, salary, montlyExpenses, freeFunds, horizontalLayout);
        setAlignItems(Alignment.CENTER);
    }

    private void proceedRegistration(Button register) {
        if (userPassword.getValue().equals(confirmUserPassword.getValue())) {
            if (userService.getUsers().stream().anyMatch(user -> user.getLogin().equals(userLogin.getValue()))) {
                Notification.show("User exists");
            } else {
                byte[] tmpSalt = passwordService.salt();
                userService.addUser(new UserDto(userFirstName.getValue(), userLastName.getValue(), Long.parseLong(salary.getValue()), Long.parseLong(montlyExpenses.getValue()), userLogin.getValue(), passwordService.hashPassword(userPassword.getValue(), tmpSalt), tmpSalt, email.getValue(), Double.parseDouble(freeFunds.getValue())));
                Notification.show("Create new Account");
                register.getUI().ifPresent(ui -> ui.navigate("login"));
            }
        } else {
            Notification.show("Incorrect password");
        }
    }

    private void fillInfreeFundsTextField() {
        freeFunds.setValue(String.valueOf(Double.parseDouble(salary.getValue()) - Double.parseDouble(montlyExpenses.getValue())));
    }

    private void goBack(Button button) {
        register.getUI().ifPresent(ui -> ui.navigate("login"));
    }
}
