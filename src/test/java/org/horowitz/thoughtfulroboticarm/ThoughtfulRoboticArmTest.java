/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.horowitz.thoughtfulroboticarm;

import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit tests for ThoughtfulRoboticArm class
 * @author Charles Eric Horowitz
 */
@DisplayName("Stack selection tests")
public class ThoughtfulRoboticArmTest {
  
    private static double getStrictlyPositiveNonZeroNonWholeDouble(Random random) {
        return random.nextDouble() * (1.0 - Double.MIN_VALUE) + Double.MIN_VALUE;
    }
    
    /**
     * Test of main method, of class ThoughtfulRoboticArm.
     */
    @Test
    @DisplayName("Select stack via main 🥳")
    public void testMain() {
        Random r = new Random();
        for(int i = 0; i < r.nextInt(); i++) {
            String[] args = {
                Double.toString(r.nextDouble() * Math.abs(r.nextInt(125))), 
                Double.toString(r.nextDouble() * Math.abs(r.nextInt(125))), 
                Double.toString(r.nextDouble() * Math.abs(r.nextInt(125))),
                Double.toString(r.nextDouble() * Math.abs(r.nextInt(125)))
            };
        
            assertDoesNotThrow(() -> ThoughtfulRoboticArm.main(args));
        }
    }

    /**
     * Test of main method, of class ThoughtfulRoboticArm.
     */
    @Test
    @DisplayName("Select stack via main with invalid numbers 🤮")
    public void testMain_invalidNumber() {
        String[] args = {"-123", "1", "1", "1"};
        assertThrows(IllegalArgumentException.class, () -> ThoughtfulRoboticArm.main(args));
    }

    /**
     * Test of main method, of class ThoughtfulRoboticArm.
     */
    @Test
    @DisplayName("Select stack via main with non-numeric args 🤮")
    public void testMain_nonNumericArg() {
        String[] args = {"width", "1", "1", "1"};
        assertThrows(IllegalArgumentException.class, () -> ThoughtfulRoboticArm.main(args));
    }

    /**
     * Test of sort method, of class ThoughtfulRoboticArm.
     */
    @Test
    @DisplayName("Sort packages to STANDARD stack 🥳")
    public void testSort_StandardStack() {
        Random r = new Random();
        for(int i = 0; i < r.nextInt(); i++) {
            double mass = ThoughtfulRoboticArm.MASS_THRESHOLD * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double width = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double length = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double height = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            
            if(width * length * mass > ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3) {
                i--;
                continue;
            }
            
            assertEquals(ThoughtfulRoboticArm.Stack.STANDARD.name(), ThoughtfulRoboticArm.sort(width, length, height, mass));
        }
    }
    
    /**
     * Test of sort method, of class ThoughtfulRoboticArm.
     */
    @Test
    @DisplayName("Sort packages to SPECIAL stack 🥳")
    public void testSort_SpecialStack() {
        Random r = new Random();
        
        // test heavy, non-bulky package
        for(int i = 0; i < r.nextInt(); i++) {
            double mass = ThoughtfulRoboticArm.MASS_THRESHOLD + getStrictlyPositiveNonZeroNonWholeDouble(r);
            double width = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double length = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double height = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            
            if(width * length * mass > ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3) {
                i--;
                continue;
            }
            
            assertEquals(ThoughtfulRoboticArm.Stack.SPECIAL.name(), ThoughtfulRoboticArm.sort(width, length, height, mass));
        }

        // test bulky, light package
        for(int i = 0; i < r.nextInt(); i++) {
            double mass = ThoughtfulRoboticArm.MASS_THRESHOLD * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double width = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM + r.nextDouble();
            double length = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double height = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            
            if(width * length * mass > ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3) {
                i--;
                continue;
            }
            
            assertEquals(ThoughtfulRoboticArm.Stack.SPECIAL.name(), ThoughtfulRoboticArm.sort(width, length, height, mass));
        }

        for(int i = 0; i < r.nextInt(); i++) {
            double mass = ThoughtfulRoboticArm.MASS_THRESHOLD * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double width = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double length = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM + r.nextDouble();
            double height = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            
            if(width * length * mass > ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3) {
                i--;
                continue;
            }
            
            assertEquals(ThoughtfulRoboticArm.Stack.SPECIAL.name(), ThoughtfulRoboticArm.sort(width, length, height, mass));
        }

        for(int i = 0; i < r.nextInt(); i++) {
            double mass = ThoughtfulRoboticArm.MASS_THRESHOLD * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double width = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double length = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double height = ThoughtfulRoboticArm.DIMENTIONAL_THRESHHOLD_CM + r.nextDouble();
            
            if(width * length * mass > ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3) {
                i--;
                continue;
            }
            
            assertEquals(ThoughtfulRoboticArm.Stack.SPECIAL.name(), ThoughtfulRoboticArm.sort(width, length, height, mass));
        }

        // test extra-bulky, light package
        for(int i = 0; i < r.nextInt(); i++) {
            double mass = ThoughtfulRoboticArm.MASS_THRESHOLD * getStrictlyPositiveNonZeroNonWholeDouble(r);
            double width = ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3 / 3 + r.nextDouble();
            double length = ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3 / 3 + r.nextDouble();
            double height = ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3 / 3 + r.nextDouble();
            
            assertEquals(ThoughtfulRoboticArm.Stack.SPECIAL.name(), ThoughtfulRoboticArm.sort(width, length, height, mass));
        }
    }

    /**
     * Test of sort method, of class ThoughtfulRoboticArm.
     */
    @Test
    @DisplayName("Sort packages to REJECTED stack 🥳")
    public void testSort_RejectedStack() {
        Random r = new Random();
        for(int i = 0; i < r.nextInt(); i++) {
            double mass = ThoughtfulRoboticArm.MASS_THRESHOLD + r.nextDouble();
            double width = ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3 / 3 + r.nextDouble();
            double length = ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3 / 3 + r.nextDouble();
            double height = ThoughtfulRoboticArm.VOLUME_THRESHOLD_CM3 / 3 + r.nextDouble();
            
            assertEquals(ThoughtfulRoboticArm.Stack.REJECTED.name(), ThoughtfulRoboticArm.sort(width, length, height, mass));
        }
    }    
}
