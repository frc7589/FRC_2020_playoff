package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class BaseTankDrive extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;
    private final DoubleSupplier m_rightSide;
    private final DoubleSupplier m_leftSide;

    public BaseTankDrive(DriveSubsystem driveSubsystem, DoubleSupplier rightSide, DoubleSupplier LeftSide) {
        m_driveSubsystem = driveSubsystem;
        m_rightSide = rightSide;
        m_leftSide = LeftSide;
        addRequirements(m_driveSubsystem);
    }

    @Override
    public void execute() {
        double driveSpeed = SmartDashboard.getNumber("Drive Speed", Constants.kDriveSpeed);

        m_driveSubsystem.drive.tankDrive(m_leftSide.getAsDouble() * driveSpeed, 
                                         m_rightSide.getAsDouble() * driveSpeed);
    }
}
