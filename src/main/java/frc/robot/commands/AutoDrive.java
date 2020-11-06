package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDrive extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;

    double startTime;
    boolean done = false;

    public AutoDrive(DriveSubsystem driveSubsystem) {
        m_driveSubsystem = driveSubsystem;
        addRequirements(m_driveSubsystem);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        double driveSpeed = SmartDashboard.getNumber("Drive Speed", Constants.kDriveSpeed);
        m_driveSubsystem.drive.tankDrive(-driveSpeed, -driveSpeed);
        if (System.currentTimeMillis() - startTime > 1000) {
            m_driveSubsystem.drive.tankDrive(0, 0);
            done = true;
        } 
    }

    @Override
    public boolean isFinished() {
        return done;
    }
}
