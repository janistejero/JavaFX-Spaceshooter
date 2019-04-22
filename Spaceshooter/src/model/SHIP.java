/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Janis Tejero
 */
public enum SHIP {
    BLUE("view/resources/shipchooser/playerShip1_blue.png", "view/resources/shipchooser/playerLife1_blue.png"),
    GREEN("view/resources/shipchooser/playerShip1_green.png", "view/resources/shipchooser/playerLife1_green.png"),
    ORANGE("view/resources/shipchooser/playerShip1_orange.png", "view/resources/shipchooser/playerLife1_orange.png"),
    RED("view/resources/shipchooser/playerShip1_red.png", "view/resources/shipchooser/playerLife1_red.png");
    
    private final String urlShip;
    private final String urlLife;
    
    private SHIP(String urlShip, String urlLife){
        this.urlShip = urlShip;
        this.urlLife = urlLife;
    }
    
    public String getURL(){
        return this.urlShip;
    }
    
    public String getURLLife(){
        return urlLife;
    }
}
