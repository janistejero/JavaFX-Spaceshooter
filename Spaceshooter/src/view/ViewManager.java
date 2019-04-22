/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.SHIP;
import model.ShipPicker;
import model.SpaceRunnerButton;
import model.SpaceRunnerSubscene;
import model.infoLabel;

/**
 *
 * @author Janis Tejero
 */
public class ViewManager {

    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    private SpaceRunnerSubscene creditSubscene;
    private SpaceRunnerSubscene helpSubscene;
    private SpaceRunnerSubscene scoreSubscene;
    private SpaceRunnerSubscene shipChooserscene;

    private SpaceRunnerSubscene sceneToHide;

    List<SpaceRunnerButton> menuButtons;

    List<ShipPicker> shipsList;

    private SHIP chosenShip;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubscene();
        createButtons();
        createBackground();
        createLogo();

    }

    private void showSubScene(SpaceRunnerSubscene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubscene() {
        createCreditsSubScene();
        createShipChooserSubScene();
        createHelpSubScene();
        createScoreSubscene();
    }

    private void createShipChooserSubScene() {
        shipChooserscene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(shipChooserscene);

        infoLabel chooseShipLabel = new infoLabel("Choose your ship");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);
        shipChooserscene.getPane().getChildren().add(chooseShipLabel);
        shipChooserscene.getPane().getChildren().add(createShipsToChoose());
        shipChooserscene.getPane().getChildren().add(createButtonToStart());
    }

    private void createCreditsSubScene() {
        creditSubscene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(creditSubscene);

        infoLabel creditLabel = new infoLabel("Credits");
        creditLabel.setLayoutX(110);
        creditLabel.setLayoutY(25);
        creditSubscene.getPane().getChildren().add(creditLabel);
    }

    private void createHelpSubScene() {
        helpSubscene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(helpSubscene);

        infoLabel helpLabel = new infoLabel("Help");
        helpLabel.setLayoutX(110);
        helpLabel.setLayoutY(25);
        helpSubscene.getPane().getChildren().add(helpLabel);
    }
    
    private void createScoreSubscene(){
        scoreSubscene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(scoreSubscene);
        
        infoLabel scoreLabel = new infoLabel("Score");
        scoreLabel.setLayoutX(110);
        scoreLabel.setLayoutY(25);
        scoreSubscene.getPane().getChildren().add(scoreLabel);
    }

    private HBox createShipsToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        shipsList = new ArrayList<>();
        for (SHIP ship : SHIP.values()) {
            ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (ShipPicker ship : shipsList) {
                        ship.setIsCircleChosen(false);
                    }
                    shipToPick.setIsCircleChosen(true);
                    chosenShip = shipToPick.getShip();
                }
            });
        }

        box.setLayoutX(300 - (118 * 2));
        box.setLayoutY(100);
        return box;
    }

    private SpaceRunnerButton createButtonToStart() {
        SpaceRunnerButton startButton = new SpaceRunnerButton("Start");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (chosenShip != null) {
                    GameViewManager gameManager = new GameViewManager();
                    gameManager.createNewGame(mainStage, chosenShip);
                }
            }

        });

        return startButton;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(SpaceRunnerButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createScoreButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() {
        SpaceRunnerButton startButton = new SpaceRunnerButton("Play");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(shipChooserscene);
            }
        });
    }

    private void createScoreButton() {
        SpaceRunnerButton scoreButton = new SpaceRunnerButton("Score");
        addMenuButton(scoreButton);

        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubscene);
            }
        });
    }

    private void createHelpButton() {
        SpaceRunnerButton helpButton = new SpaceRunnerButton("Help");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubscene);
            }
        });
    }

    private void createCreditsButton() {
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("Credits");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditSubscene);
            }

        });
    }

    private void createExitButton() {
        SpaceRunnerButton exitButton = new SpaceRunnerButton("Exit");
        addMenuButton(exitButton);
    }

    private void createBackground() {
        Image backgroundImage = new Image("/view/resources/purple.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo = new ImageView("/view/resources/logo.png");
        logo.setLayoutX(400);
        logo.setLayoutY(50);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);

    }

}
