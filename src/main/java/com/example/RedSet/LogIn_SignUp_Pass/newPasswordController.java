package com.example.RedSet.LogIn_SignUp_Pass;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.MAIN;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class newPasswordController {

    @FXML
    private Button LogIn;

    @FXML
    private Button confirm;

    @FXML
    private PasswordField newpass;

    @FXML
    private PasswordField retypepass;
    String userpass;
    String email;
    @FXML
    void ButtonLogIn(MouseEvent event) throws IOException {
        Stage stage = (Stage) LogIn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/LogInSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.centerOnScreen();
    }


    void isValidPassword(String s) throws LoginSignupException {
        if(!textValidation.isValid(s, "^.{8,}$")) throw new LoginSignupException(s);
    }

    @FXML
    void confirmBtn(MouseEvent event) throws IOException {
        if(Objects.equals(newpass.getText(), retypepass.getText()) && !newpass.getText().isEmpty()){
            try {
                isValidPassword(newpass.getText());
                userpass = newpass.getText();
                File file = new File("randomNum.txt");
                Scanner sc = new Scanner(file);
                email = sc.nextLine();
                email = sc.nextLine();
                Connection connection = DBconnect.getConnect();
                String query = "UPDATE users SET password='" + userpass + "' WHERE email='" + email + "';";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                Stage stage = (Stage) confirm.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Log In");
                stage.setScene(scene);
                stage.centerOnScreen();
            } catch (LoginSignupException e) {
                System.out.println(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
