/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

/**
 *
 * @author Janis Tejero
 */
public class SpaceRunnerSubscene extends SubScene {

    private final String FONT_PATH = "src\\model\\resources\\kenvector_future.ttf";
    private final String BACKGROUND_IMAGE = "model/resources/yellow_panel.png";

    private boolean isHidden;

    public SpaceRunnerSubscene() {
        super(new AnchorPane(), 600, 400);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutY(180);
        setLayoutX(1024);

    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDelay(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(-675);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }

        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
