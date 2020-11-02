package frc.robot.subsystems;

import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("myTable");

  public WPI_TalonSRX lazySusan = new WPI_TalonSRX(Constants.getCAN("lazy susan"));
  public WPI_VictorSPX trigger = new WPI_VictorSPX(Constants.getCAN("shoot trigger"));
  public WPI_TalonSRX wheel = new WPI_TalonSRX(Constants.getCAN("shoot fire"));

  public DigitalInput susanLeft = new DigitalInput(Constants.getDIO("susan left"));
  public DigitalInput susanRight = new DigitalInput(Constants.getDIO("susan right"));

  private double[] SusanPID_Values = {0.001, 0.05, 0, 0};
  private double[] WheelPID_Values = {1, 0.1, 0, 0.1};

  /**
   * Create a shooter subsystem.
   */
  public ShooterSubsystem() {
    lazySusan.setInverted(true);
    
    lazySusan.setSelectedSensorPosition(0);
    lazySusan.config_kF(0, SusanPID_Values[0]);
    lazySusan.config_kP(0, SusanPID_Values[1]);
    lazySusan.config_kI(0, SusanPID_Values[2]);
    lazySusan.config_kD(0, SusanPID_Values[3]);
    
    wheel.setSelectedSensorPosition(0);
    
    wheel.config_kF(0, WheelPID_Values[0]);
    wheel.config_kP(0, WheelPID_Values[1]);
    wheel.config_kI(0, WheelPID_Values[2]);
    wheel.config_kD(0, WheelPID_Values[3]);
  }
}