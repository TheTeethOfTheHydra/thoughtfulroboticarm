
package org.horowitz.thoughtfulroboticarm;

/**
 *
 * @author Charles Eric Horowitz
 */
public class ThoughtfulRoboticArm {

    public static enum Stack{STANDARD, SPECIAL, REJECTED};
    public static final double DIMENTIONAL_THRESHHOLD_CM = 150;
    public static final double VOLUME_THRESHOLD_CM3 = 1_000_000;
    public static final double MASS_THRESHOLD = 20;

    /**
     * Outputs a string naming the stack that a package is placed in based on its size and mass
     * @param args four floating-point numbers providing the width (cm), length (cm), height(cm), and mass (kg) of a package to be stacked
     */
    public static void main(String[] args) {
        if(args.length != 4) {
            throw new IllegalArgumentException("Incorrect arguments! Required: <widthInCm> <lengthInCm> <heightInCm> <massInKg>");
        }
        
        double width = 0;
        try {
            width = Double.parseDouble(args[0]);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect argument! Arg width must be a number!");
        }
            
        double length = 0;
        try {
            length = Double.parseDouble(args[2]);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect argument! Arg length must be a number!");
        }

        double height = 0;
        try {
            height = Double.parseDouble(args[2]);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect argument! Arg height must be a number!");
        }

        double mass = 0;
        try {
            mass = Double.parseDouble(args[3]);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect argument! Arg mass must be a number!");
        }
        
        String queue = sort(width, length, height, mass);
        System.out.println("Selected stack: " + queue);
    }
    
    /**
     * Selects the appropriate stack for a robotic arm to place a package into, given the package dimensions and mass
     * @param width package width in cm
     * @param length package length in cm
     * @param height package height in cm
     * @param mass package mass in kg
     * @return {@String} name of the stack to place the package in
     * @throws IllegalArgumentException  when any package measurement is not a non-zero positive real number
     */
    public static String sort(final double width, final double length, final double height, final double mass)
            throws IllegalArgumentException
    {
        if(!Double.isFinite(width) || Double.isNaN(width) || width <= 0) {
            throw new IllegalArgumentException("Width must be a positive real number.");
        }
        
        if(!Double.isFinite(length) || Double.isNaN(length) || length <= 0) {
            throw new IllegalArgumentException("Length must be a positive real number.");
        }

        if(!Double.isFinite(height) || Double.isNaN(height) || height <= 0) {
            throw new IllegalArgumentException("Height must be a positive real number.");
        }

        if(!Double.isFinite(mass) || Double.isNaN(mass) || mass <= 0) {
            throw new IllegalArgumentException("Mass must be a positive real number.");
        }

        boolean isHeavy = mass >= MASS_THRESHOLD;
        
        boolean isVolumeBulky = (width * length * height) > VOLUME_THRESHOLD_CM3;
        
        boolean isDimentionallyBulky = (width >= DIMENTIONAL_THRESHHOLD_CM
                || length >= DIMENTIONAL_THRESHHOLD_CM
                || height >= DIMENTIONAL_THRESHHOLD_CM);
        
        if(isHeavy
                && (isVolumeBulky || isDimentionallyBulky))
            return Stack.REJECTED.name();
        
        if(isHeavy
                || isVolumeBulky
                || isDimentionallyBulky)
            return Stack.SPECIAL.name();
        
        return Stack.STANDARD.name();
    }
    
}
