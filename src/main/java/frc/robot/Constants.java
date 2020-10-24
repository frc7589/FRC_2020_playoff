package frc.robot;

import java.util.HashMap;

public final class Constants {

    private static HashMap<String, Integer> mapPWM; // the PWM port map
    private static HashMap<String, Integer> mapPCM; // the PCM port map
    private static HashMap<String, Integer> mapCAN; // the CAN port map
    private static HashMap<String, Integer> mapDIO; // the DIO port map
    private static HashMap<String, Integer> mapCTRL; // the controller port map

    /* XBOX BUTTON MAP KEY:
    1 = A
    2 = B
    3 = X
    4 = Y
    5 = left bumper
    6 = right bumper
    7 = back
    8 = start
    9 = left stick click
    10 = right stick click
    */

    // Subsystems constants
    // Base drive
    public final static double kDriveSpeed = 0.7;
    public final static double kTurnSpeed = 0.6;

    // Shooter
    public final static double kShootTrigger = -0.2;
    public final static double kSusanSpeed = 0.3;

    // Elevator
    public final static double kElevatorSpeed = 1; 


    public Constants() {

        // Configure the button bindings
        mapPWM = new HashMap<>();
        mapPCM = new HashMap<>();
        mapCAN = new HashMap<>();
        mapDIO = new HashMap<>();
        mapCTRL = new HashMap<>();

        // map for Controls
        setCTRL("player 1", 0);
        setCTRL("player 2", 1);
        
        setCAN("PCM", 49);

        // map for CAN port for VictorSPX
        setCAN("drive_lf", 1);
        setCAN("drive_rf", 0);
        setCAN("panel arm", 8);
        setCAN("panel spinner", -1);
        setCAN("spin", 7);
        setCAN("intake wheel", 9); // not connected to controller
        setCAN("elevator", 2);
        setCAN("lifter_l", 4);
        setCAN("lifter_r", 5);
        

        // map for CAN port for TalonSRX
        setCAN("shoot fire", 4); // not connected to controller
        setCAN("lazy susan", 3);
        setCAN("drive_rb", 1);
        setCAN("drive_lb", 2);
        setCAN("shoot trigger", 9);
        
        // map for PCM pneumatic devices
        setPCM("intake left forward", 0);
        setPCM("intake left backward", 1);
        setPCM("intake right forward", 2);
        setPCM("intake right backward", 3);

        // map for DIO
        setDIO("susan left", 0);
        setDIO("susan right", 1);
    }

    /**
     * Method to set a PWM port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the port number
     */
    public static void setPWM(String name, int port) {
        mapPWM.put(name, port);
    }

    /**
     * Method to set a PCM port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the port number
     */
    public static void setPCM(String name, int port) {
        mapPCM.put(name, port);
    }

    /**
     * Method to set a CAN port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the port number
     */
    public static void setCAN(String name, int port) {
        mapCAN.put(name, port);
    }

    /**
     * Method to set a DIO port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the port number
     */
    public static void setDIO(String name, int port) {
        mapDIO.put(name, port);
    }

    /**
     * Method to set a CTRL port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the port number
     */
    public static void setCTRL(String name, int port) {
        mapCTRL.put(name, port);
    }

    /**
     * Method to get a PWM port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     */
    public static int getPWM(String name) {
        return mapPWM.get(name);
    }

    /**
     * Method to get a PCM port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     */
    public static int getPCM(String name) {
        return mapPCM.get(name);
    }

    /**
     * Method to get a CAN port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     */
    public static int getCAN(String name) {
        return mapCAN.get(name);
    }

    /**
     * Method to get a DIO port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     */
    public static int getDIO(String name) {
        return mapDIO.get(name);
    }

    /**
     * Method to get a controller port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     */
    public static int getCTRL(String name) {
        return mapCTRL.get(name);
    }
}