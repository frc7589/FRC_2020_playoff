package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SpinnerSubsystem extends SubsystemBase {

    public WPI_VictorSPX arm = new WPI_VictorSPX(Constants.getCAN("panel arm"));
    public WPI_VictorSPX spinner = new WPI_VictorSPX(Constants.getCAN("panel spinner"));

    public SpinnerSubsystem() {
  }
}