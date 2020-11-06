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
  public final DriveSubsystem drive = new DriveSubsystem();
  public final IntakeSubsystem intake = new IntakeSubsystem();
  public final ShooterSubsystem shooter = new ShooterSubsystem();
  public final ElevatorSubsystem elevator = new ElevatorSubsystem();
  public final SpinnerSubsystem spinner = new SpinnerSubsystem();
  public final Compressor compressor = new Compressor(Constants.getCAN("PCM"));

  private final XboxController driver1 = new XboxController(Constants.getCTRL("player 1")); // x0
  private final XboxController driver2 = new XboxController(Constants.getCTRL("player 2")); // x1

  private BaseTankDrive baseTankDrive = new BaseTankDrive(
    drive,
    () -> driver2.getY(Hand.kLeft),
    () -> driver2.getY(Hand.kRight)
  );

  private AutoDrive autoDrive = new AutoDrive(drive);

  private ManualShoot manualShoot = new ManualShoot(
    shooter, 
    () -> driver1.getBButton(), 
    () -> driver1.getTriggerAxis(Hand.kRight) > 0.8,
    () -> driver1.getBumper(Hand.kLeft),
    () -> driver1.getBumper(Hand.kRight),
    () -> driver1.getTriggerAxis(Hand.kLeft) > 0.8
  );

  private Intaking intaking = new Intaking(
    intake, 
    () -> driver1.getYButtonPressed(),
    () -> driver1.getXButtonPressed(),
    () -> driver1.getAButton(),
    () -> driver1.getBackButton()
  );

  private Elevate elevate = new Elevate(
    elevator, 
    () -> driver2.getTriggerAxis(Hand.kLeft) > 0.7,
    () -> driver2.getTriggerAxis(Hand.kRight) > 0.7, 
    () -> driver2.getAButton(),
    () -> driver2.getPOV() == 0,
    () -> driver2.getPOV() == 180
    );

  private SpinPanel spinPanel = new SpinPanel(
    spinner, 
    () -> driver2.getYButton(), 
    () -> driver2.getXButton(), 
    () -> driver2.getBButton()
    );

  private ElevatorAdjust testCommand = new ElevatorAdjust(
    elevator,
    drive,
    () -> driver1.getY(Hand.kLeft),
    () -> driver1.getY(Hand.kRight),
    () -> driver1.getTriggerAxis(Hand.kLeft),
    () -> driver1.getTriggerAxis(Hand.kRight)
  );

  private AutoShoot autoShoot = new AutoShoot(shooter);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(driver1, Button.kStart.value)
      .whenPressed(autoShoot);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command[] getAutonomousCommands() {
    Command[] commands = {autoDrive};
    return commands;
  }
  public Command[] getTestCommands() {
    Command[] commands = {testCommand};
    return commands;
  }
  public Command[] getTeleopCommands() {
    Command[] commands = {baseTankDrive, elevate, manualShoot, intaking, spinPanel};
    return commands;
  }
  public Command getAimBot() {
    return autoShoot;
  }
}
