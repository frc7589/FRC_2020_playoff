/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command[] m_teleopCommands;
  private Command[] m_autonomousCommands;
  private Command[] m_testCommands;

  private static RobotContainer m_robotContainer;
  private static Constants m_constants;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_constants = new Constants();
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommands = m_robotContainer.getAutonomousCommands();

    for (int i = 0; i < m_autonomousCommands.length; i++) {
      m_autonomousCommands[i].schedule();
    }
    
    m_robotContainer.compressor.start();
    m_robotContainer.compressor.setClosedLoopControl(true);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    m_teleopCommands = m_robotContainer.getTeleopCommands();

    for(int i = 0; i < m_teleopCommands.length; i++) {
      m_teleopCommands[i].schedule();
    }
    
    m_robotContainer.compressor.start();
    m_robotContainer.compressor.setClosedLoopControl(true);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if (m_robotContainer.getAimBot().isFinished()) {
      m_teleopCommands[2].schedule();
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    m_testCommands = m_robotContainer.getTestCommands();

    for (int i = 0; i < m_testCommands.length; i++) {
      m_testCommands[i].schedule();
    }
    
    m_robotContainer.compressor.start();
    m_robotContainer.compressor.setClosedLoopControl(true);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  /**
   * A simple getter method for RobotContainer.java
   * 
   * @return m_robotContainer
   */
  public static RobotContainer getRobotContainer() {
    return m_robotContainer;
  }

  /**
   * A simple getter method for Constant.java
   * 
   * @return m_Constant
   */
  public static Constants getConstants() {
    return m_constants;
  }
}
