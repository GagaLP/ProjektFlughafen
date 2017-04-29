package It.fallmerayer.codingGmbH.projektFlughafen.Controller;

import It.fallmerayer.codingGmbH.projektFlughafen.Modell.Anwender;
import It.fallmerayer.codingGmbH.projektFlughafen.Modell.Benutzerprofil;
import It.fallmerayer.codingGmbH.projektFlughafen.Modell.BenutzerprofilSpeicher;
import It.fallmerayer.codingGmbH.projektFlughafen.Modell.NichtImSystemRegistriertException;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.CheckValidations;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.*;
import It.fallmerayer.codingGmbH.projektFlughafen.Utility.ViewNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Created by gabriel on 16.04.17.
 */

//Finished
public class AnmeldenRegistrierenController extends AbstractController {
    @FXML TextField vornameTxtField;
    @FXML TextField passwordTxtField;
    @FXML TextField passwordRegTxtField;
    @FXML TextField nummerDerIdentitaetskarteTxtField;
    @FXML TextField nachnameTxtField;
    @FXML TextField emailTxtField;
    @FXML TextField benutzernameTxtField;
    @FXML TextField benutzernameRegTxtField;

    @FXML DatePicker geburtsdatumDatePicker;

    @FXML Button registrierenButt;
    @FXML Button anmeldenButt;

    @FXML
    private void handleAnmelden(){
        if (everythingCorrectInputAnm()){
            try {
                main.benutzerprofil = BenutzerprofilSpeicher.getInstance().anmelden(benutzernameTxtField.getText(), passwordTxtField.getText());
                if (main.lastController == ViewNavigation.STARTSCENE) {
                    main.selectView(ViewNavigation.ZWISCHENSCENE);
                } else {
                    main.selectView(ViewNavigation.ZAHLUNGSCENE);
                }
            } catch (NichtImSystemRegistriertException e) {
                main.openMessageDialog("Der Benutzer ist nicht im System");
            }
        }
    }

    @FXML
    private void handleRegistrieren(){
        if (everythingCorrectInputReg()){
            try {
                if (BenutzerprofilSpeicher.getInstance().containsBenutzername(benutzernameRegTxtField.getText())){
                    main.openMessageDialog("Der Benutzer ist schon vorhanden");
                } else {
                    BenutzerprofilSpeicher.getInstance().addBenutzerprofil(new Anwender(vornameTxtField.getText(), nachnameTxtField.getText(), emailTxtField.getText(), geburtsdatumDatePicker.getValue().atStartOfDay(), benutzernameRegTxtField.getText(), passwordRegTxtField.getText(), Integer.parseInt(nummerDerIdentitaetskarteTxtField.getText())));
                    main.benutzerprofil = BenutzerprofilSpeicher.getInstance().anmelden(benutzernameRegTxtField.getText(), passwordRegTxtField.getText());
                    if (main.lastController == ViewNavigation.STARTSCENE) {
                        main.selectView(ViewNavigation.ZWISCHENSCENE);
                    } else {
                        main.selectView(ViewNavigation.ZAHLUNGSCENE);
                    }
                }
            } catch (NichtImSystemRegistriertException e) {
                main.openMessageDialog("Der Benutzer ist nicht im System");
            }
        }
    }

    private boolean everythingCorrectInputReg(){
        try {
            CheckValidations.isValidString(vornameTxtField.getText());
            CheckValidations.isValidString(nachnameTxtField.getText());
            CheckValidations.isValidString(benutzernameRegTxtField.getText());
            CheckValidations.isValidString(passwordRegTxtField.getText());
            CheckValidations.isValidString(nummerDerIdentitaetskarteTxtField.getText());
            CheckValidations.isValidString(emailTxtField.getText());

            CheckValidations.isNameString(vornameTxtField.getText());
            CheckValidations.isNameString(nachnameTxtField.getText());
            CheckValidations.isEmail(emailTxtField.getText());
            CheckValidations.isValidPassword(passwordRegTxtField.getText());

            CheckValidations.isDate(geburtsdatumDatePicker.getValue());
            CheckValidations.isNumber(nummerDerIdentitaetskarteTxtField.getText());

        }catch (NichtsEingegebenException | KeineNummerException | KeinPasswordException | KeinNameException | KeineEmailException | KeinDatumException e){
            main.openMessageDialog(e.getMessage());
            return false;
        }
        return true;
    }

    private boolean everythingCorrectInputAnm(){
        try {
            CheckValidations.isValidString(benutzernameTxtField.getText());
            CheckValidations.isValidString(passwordTxtField.getText());

        }catch (NichtsEingegebenException e){
            main.openMessageDialog(e.getMessage());
            return false;
        }
        return true;
    }

}