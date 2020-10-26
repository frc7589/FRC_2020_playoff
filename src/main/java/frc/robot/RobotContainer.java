/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem drive = new DriveSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final Compressor m_compressor = new Compressor(Constants.getCAN("PCM"));

  private final XboxController driver1 = new XboxController(Constants.getCTRL("player 1"));
  private final XboxController driver2 = new XboxController(Constants.getCTRL("player 2"));


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_compressor.start();
    m_compressor.setClosedLoopControl(true);

    drive.setDefaultCommand(
      new BaseTankDrive(
        drive,
        () -> driver1.getY(Hand.kLeft) * Constants.kDriveSpeed,
        () -> driver1.getY(Hand.kRight) * Constants.kDriveSpeed));
    shooter.setDefaultCommand(
      new ManualShoot(
        shooter, 
        () -> driver1.getBButton(), 
        () -> driver1.getTriggerAxis(Hand.kRight) > 0.8,
        () -> driver1.getBumper(Hand.kLeft),
        () -> driver1.getBumper(Hand.kRight)));
    intake.setDefaultCommand(
      new Intaking(
        intake, 
        () -> driver1.getYButtonPressed(),
        () -> driver1.getXButtonPressed()));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
