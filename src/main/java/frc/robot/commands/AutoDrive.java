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
        m_driveSubsystem.drive.tankDrive(-driveSpeed*2/3, -driveSpeed*2/3);
        if (System.currentTimeMillis() - startTime > 1400) {
            m_driveSubsystem.leftSide.set(0);
            m_driveSubsystem.rightSide.set(0);
            done = true;
        } 
    }

    @Override
    public boolean isFinished() {
        return done;
    }
}
