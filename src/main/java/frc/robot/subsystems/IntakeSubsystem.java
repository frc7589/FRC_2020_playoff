package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("myTable");

  public WPI_VictorSPX intaker = new WPI_VictorSPX(Constants.getCAN("intake wheel"));
  public WPI_VictorSPX spin = new WPI_VictorSPX(Constants.getCAN("spin"));

  public DoubleSolenoid leftArm;
  public DoubleSolenoid rightArm;
  

  /**
   * Create a intake subsystem.
   */
  public IntakeSubsystem() {
    leftArm = new DoubleSolenoid(Constants.getCAN("PCM"), Constants.getPCM("intake left forward"), Constants.getPCM("intake left backward"));
    rightArm = new DoubleSolenoid(Constants.getCAN("PCM"), Constants.getPCM("intake right forward"), Constants.getPCM("intake right backward"));
  }
}