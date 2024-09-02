package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.GetTransactionHistoryDTO;
import com.globant.application.dto.TransactionHistoryDTO;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exchange.Transaction;
import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author erillope
 */
public class TransactionHistoryController implements Initializable{

    @FXML
    private VBox historyBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetTransactionHistoryDTO dto = new GetTransactionHistoryDTO(FxmlApp.authenticationService.getSignedUserDTO().getId());
        try{
            TransactionHistoryDTO history = FxmlApp.walletService.getHistory(dto);
            for (int i = 0; i < history.getHistory().size(); i++){
                historyBox.getChildren().add(new Label(history.getHistory().get(i).toString()));
            }
        }
        catch(DomainException e){FxmlApp.showErrorMessage(e);}
    }

    @FXML
    private void returnMenu(ActionEvent event) throws IOException {
        FxmlApp.setRoot("mainMenu");
    }
}
