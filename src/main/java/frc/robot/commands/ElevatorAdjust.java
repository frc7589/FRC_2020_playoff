package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorAdjust extends CommandBase {
    private final ElevatorSubsystem m_elevatorSubsystem;
    private final DriveSubsystem m_driveSubsystem;
    private final DoubleSupplier m_liftL;
    private final DoubleSupplier m_liftR;
    private final DoubleSupplier m_LiftUp;
    private final DoubleSupplier m_LiftDown;

    public ElevatorAdjust(ElevatorSubsystem elevatorSubsystem, DriveSubsystem driveSubsystem, DoubleSupplier liftL, DoubleSupplier liftR, 
                   DoubleSupplier liftUp, DoubleSupplier liftDown) {
        m_elevatorSubsystem = elevatorSubsystem;
        m_driveSubsystem = driveSubsystem;
        m_liftL = liftL;
        m_liftR = liftR;
        m_LiftUp = liftUp;
        m_LiftDown = liftDown;
        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void execute() {
        double lift_speed = SmartDashboard.getNumber("Lifters Speed", Constants.kLiftSpeed);

        if (Math.abs(m_liftL.getAsDouble()) > 0.1 || Math.abs(m_liftR.getAsDouble()) > 0.1 ||
            m_LiftUp.getAsDouble() > 0.1 || m_LiftDown.getAsDouble() > 0.1) {
            if (Math.abs(m_liftL.getAsDouble()) > 0.1) {
                m_elevatorSubsystem.lifter_l.set(lift_speed * m_liftL.getAsDouble());
            }
            if (Math.abs(m_liftR.getAsDouble()) > 0.1) {
                m_elevatorSubsystem.lifter_r.set(lift_speed * m_liftR.getAsDouble());
            }
            
            if (m_LiftUp.getAsDouble() > 0.1) {
                m_elevatorSubsystem.lifter.set(lift_speed * m_LiftUp.getAsDouble());
            }
            if (m_LiftDown.getAsDouble() > 0.1) {
                m_elevatorSubsystem.lifter.set(-lift_speed * m_LiftDown.getAsDouble());
            }
        }
        else {
            m_elevatorSubsystem.lifter_l.set(0);
            m_elevatorSubsystem.lifter_r.set(0);
        }
        m_driveSubsystem.leftSide.set(0);
        m_driveSubsystem.rightSide.set(0);
    }
}
