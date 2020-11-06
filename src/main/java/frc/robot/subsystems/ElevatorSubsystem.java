package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

  public WPI_VictorSPX lifter_l = new WPI_VictorSPX(Constants.getCAN("lifter_l"));
  public WPI_VictorSPX lifter_r = new WPI_VictorSPX(Constants.getCAN("lifter_r"));
  public WPI_VictorSPX elevator = new WPI_VictorSPX(Constants.getCAN("elevator"));

  public SpeedControllerGroup lifter = new SpeedControllerGroup(lifter_l, lifter_r);

  public ElevatorSubsystem() {
    lifter_l.setInverted(true);
  }
}