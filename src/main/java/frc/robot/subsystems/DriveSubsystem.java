package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private WPI_VictorSPX leftF = new WPI_VictorSPX(Constants.getCAN("drive_lf"));
  private WPI_TalonSRX leftB = new WPI_TalonSRX(Constants.getCAN("drive_lb"));
  private WPI_VictorSPX rightF = new WPI_VictorSPX(Constants.getCAN("drive_rf"));
  private WPI_TalonSRX rightB = new WPI_TalonSRX(Constants.getCAN("drive_rb"));
  public SpeedControllerGroup leftSide = new SpeedControllerGroup(leftF, leftB);
  public SpeedControllerGroup rightSide = new SpeedControllerGroup(rightF, rightB);
  public DifferentialDrive drive = new DifferentialDrive(rightSide, leftSide);

  /**
   * Create a basic subsystem for base drive, require 4 VictorSPX motor controller. Only tank 
   * drive and arcade drive are available now. No PID supported.
   */
  public DriveSubsystem() {
    leftSide.setInverted(false);
    rightSide.setInverted(false);
  }
}