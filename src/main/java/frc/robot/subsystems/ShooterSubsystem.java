package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("myTable");

  public WPI_VictorSPX lazySusan = new WPI_VictorSPX(Constants.getCAN("lazy susan"));
  public WPI_TalonSRX trigger = new WPI_TalonSRX(Constants.getCAN("shoot trigger"));
  public WPI_TalonSRX wheel = new WPI_TalonSRX(Constants.getCAN("shoot fire"));

  public DigitalInput susanLeft = new DigitalInput(Constants.getDIO("susan left"));
  public DigitalInput susanRight = new DigitalInput(Constants.getDIO("susan right"));

  /**
   * Create a shooter subsystem.
   */
  public ShooterSubsystem() {

  }
}