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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author erillope
 */
public class TransactionHistoryController implements Initializable{

    @FXML
    private VBox historyBox;
    @FXML
    private ImageView zundamonView;
    @FXML
    private Label message;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetTransactionHistoryDTO dto = new GetTransactionHistoryDTO(FxmlApp.authenticationService.getSignedUserDTO().getId());
        try{
            TransactionHistoryDTO history = FxmlApp.walletService.getHistory(dto);
            for (int i = 0; i < history.getHistory().size(); i++){
                VBox box = new VBox();
                box.getStyleClass().add("transaction-box");
                Label amountLabel = new Label("amout: "+history.getHistory().get(i).getAmount().toString());
                amountLabel.getStyleClass().add("my-label");
                Label cryptoNameLabel = new Label("cryptoName: "+history.getHistory().get(i).getCryptoName().toString());
                cryptoNameLabel.getStyleClass().add("my-label");
                Label typeLabel = new Label("type: "+history.getHistory().get(i).getType().toString());
                typeLabel.getStyleClass().add("my-label");
                Label priceLabel = new Label("price: "+history.getHistory().get(i).getPrice().toString());
                priceLabel.getStyleClass().add("my-label");
                box.getChildren().addAll(amountLabel, cryptoNameLabel, typeLabel, priceLabel);
                historyBox.getChildren().add(box);
            }
        }
        catch(DomainException e){FxmlApp.showErrorMessage(e);}
        message.setText("Here you can review\n"
                + "all your sales and\n"
                + "purchase transactions.");
    }

    @FXML
    private void returnMenu(MouseEvent event) throws IOException {
        FxmlApp.setRoot("mainMenu");
    }
    
    private void showError(DomainException e){
        message.setText(e.getMessage());
        zundamonView.setImage(new Image(FxmlApp.class.getResource("/gallery/zundamon2.png").toString()));
    }
}
