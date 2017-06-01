package db.sample.registration;
import db.sample.factoryPattern.CreatorMongoDBUserDAO;
import db.sample.factoryPattern.CreatorMySQLHibernateUserDAO;
import db.sample.factoryPattern.CreatorMySQLUserDAO;
import db.sample.main.Main;
import db.sample.IDAO.IUserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class RegController
{
    private String firstName, lastName, login, password, phone, email;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldLastName;
    @FXML
    private TextField txtFieldLogin;
    @FXML
    private PasswordField passFieldPass;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private Pane successRegPane;
    @FXML
    private Label errorLabel;
    @FXML
    private RadioButton mySqlBtn;
    @FXML
    private RadioButton hibernateBtn;



    public void onOkClicked()
    {
        if(passFieldPass.getText().length() >= 1 && txtFieldLastName.getText().length() >= 1 &&
                txtFieldLogin.getText().length() >= 6 && passFieldPass.getText().length() >= 7 &&
                checkPass(passFieldPass.getText())) {
            firstName = txtFieldName.getText();
            lastName = txtFieldLastName.getText();
            login = txtFieldLogin.getText();
            password = passFieldPass.getText();
            phone = phoneField.getText();
            email = emailField.getText();

            Client client = (new Client.Builder(firstName, lastName, login, password).
                    setPhone(phone).
                    setEmail(email).build());


            try {
                if(mySqlBtn.isSelected())
                    Main.creatorUserDao = new CreatorMySQLUserDAO();
                if(hibernateBtn.isSelected())
                    Main.creatorUserDao = new CreatorMySQLHibernateUserDAO();
                else
                    Main.creatorUserDao = new CreatorMongoDBUserDAO();
                IUserDAO dao = Main.creatorUserDao.factoryUserDAO();
                if(dao.insertUser(client) == true)
                successRegPane.setVisible(true);
                else System.out.println("Ошибка при занесении данных в базу");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else errorLabel.setVisible(true);
    }

    private boolean checkPass(String pass)
    {
        boolean hasNumber = false;
        boolean hasUpperCase = false;
        char[] charPass = pass.toCharArray();
        for(char cymbol:charPass)
        {
            if(Character.isDigit(cymbol))
                hasNumber = true;
            else if(Character.isUpperCase(cymbol))
                hasUpperCase = true;
            if(hasNumber && hasUpperCase)
                return true;
        }
        return false;
    }
    public void onOkBtnClicked()
    {
        successRegPane.setVisible(false);
        Registration.prStage.close();
    }
}
