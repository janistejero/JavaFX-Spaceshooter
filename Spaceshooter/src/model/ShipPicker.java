/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Janis Tejero
 */
public class ShipPicker extends VBox{
    
    private ImageView circleImage;
    private ImageView shipImage;
    
    private String circleNotChosen = "view/resources/shipchooser/grey_circle.png";
    private String circleChosen = "view/resources/shipchooser/grey_boxTick.png";
    
    private SHIP ship;
    
    private boolean isCircleChosen;
    
    public ShipPicker(SHIP ship){
        circleImage = new ImageView(circleNotChosen);
        shipImage = new ImageView(ship.getURL());
        this.ship = ship;
        isCircleChosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(shipImage);
    }
    
    public SHIP getShip(){
        return ship;
    }
    
    public boolean getIsCircleChosen(){
        return isCircleChosen;
    }
    
    public void setIsCircleChosen(boolean isCircleChosen){
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? circleChosen : circleNotChosen;
        circleImage.setImage(new Image(imageToSet));
    }
}
