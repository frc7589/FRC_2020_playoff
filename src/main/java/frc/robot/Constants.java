package frc.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class Constants {

    private static HashMap<String, Integer> mapPWM; // the PWM port map
    private static HashMap<String, Integer> mapPCM; // the PCM port map
    private static HashMap<String, Integer> mapCAN; // the CAN port map
    private static HashMap<String, Integer> mapDIO; // the DIO port map
    private static HashMap<String, Integer> mapCTRL; // the controller port map

    // Subsystems constants
    // Base drive
    public final static double kDriveSpeed = 0.7;

    // Intake
    public final static double kIntakeSpeed = -0.55;
    public final static double kSpinCylinderSpeed = -0.426;   // voltage
    public final static double kSpinDelay = .67;              // second
    public final static double kSpinDurration = 0.23;       // second

    // Shooter
    public final static double kShootTrigger = 0.7;
    public final static double kSusanSpeed = 0.3;

    public final static double kManualShootFireSpeed = -0.4;
    public final static int kAutoShootSpeed = -60;

    // Elevator
    public final static double kElevateSpeed = 0.8; 
    public final static double kLiftSpeed = -0.8; 

    // PanelSpinner
    public final static double kArmSpeed = 0.3;
    public final static double kSpinSpeed = 0.3;




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
        setCAN("drive_lf", 5);
        setCAN("drive_rf", 0);
        setCAN("panel arm", 6);
        setCAN("panel spinner", 1);
        setCAN("spin", 7);
        setCAN("intake wheel", 8);
        setCAN("elevator", 4);
        setCAN("lifter_l", 3);
        setCAN("lifter_r", 2);
        setCAN("shoot trigger", 9);
        

        // map for CAN port for TalonSRX
        setCAN("shoot fire", 2);
        setCAN("lazy susan", 3);
        setCAN("drive_rb", 0);
        setCAN("drive_lb", 1);
        
        // map for PCM pneumatic devices
        setPCM("intake left forward", 0);
        setPCM("intake left backward", 1);
        setPCM("intake right forward", 2);
        setPCM("intake right backward", 3);

        // map for DIO
        setDIO("susan left", 0);
        setDIO("susan right", 1);

        SetSMD_DefaultValue();
    }

    private void SetSMD_DefaultValue() {
        SmartDashboard.putNumber("Drive Speed", kDriveSpeed);

        SmartDashboard.putNumber("Intake Speed", kIntakeSpeed);
        SmartDashboard.putNumber("Spin Cylinder Speed", kSpinCylinderSpeed);
        SmartDashboard.putNumber("Spin Delay", kSpinDelay);
        SmartDashboard.putNumber("Spin Durration", kSpinDurration);

        SmartDashboard.putNumber("Shoot Trigger Speed", kShootTrigger);
        SmartDashboard.putNumber("Susan Speed", kSusanSpeed);

        SmartDashboard.putNumber("Manual Shoot Speed", kManualShootFireSpeed);
        SmartDashboard.putNumber("Auto Shoot Speed", kAutoShootSpeed);

        SmartDashboard.putNumber("Elevator Speed", kElevateSpeed);
        SmartDashboard.putNumber("Lifters Speed", kLiftSpeed);

        SmartDashboard.putNumber("Arm Speed", kArmSpeed);
        SmartDashboard.putNumber("Spin Speed", kSpinSpeed);
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