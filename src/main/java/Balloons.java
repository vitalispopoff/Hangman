//  @formatter:off

import java.awt.Color;

class Balloons {

    private int 
        locationX, 
        locationY,
        directionX, 
        directionY;
    private Color
            color;

    int getLocationX() {
        return locationX;
    }

    int getLocationY() { return locationY; }

    int getDirectionX() {
        return directionX;
    }

    int getDirectionY() {
        return directionY;
    }

    Color getColor() {
        return color;
    }

    void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    void setColor(Color color) {
        this.color = color;
    }
}

//  @formatter:on