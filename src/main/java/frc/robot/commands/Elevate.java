package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class Elevate extends CommandBase {
    private final ElevatorSubsystem m_elevatorSubsystem;
    private final DoubleSupplier m_lift;
    private final DoubleSupplier m_liftLoose;
    private final BooleanSupplier m_elevatorUp;
    private final BooleanSupplier m_elevatorDown;

    public Elevate(ElevatorSubsystem elevatorSubsystem, DoubleSupplier lift, DoubleSupplier liftLoose, 
                   BooleanSupplier elevatorUp, BooleanSupplier elevatorDown) {
        m_elevatorSubsystem = elevatorSubsystem;
        m_lift = lift;
        m_liftLoose = liftLoose;
        m_elevatorUp = elevatorUp;
        m_elevatorDown = elevatorDown;
        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void execute() {
        double lift_speed = SmartDashboard.getNumber("Lifters Speed", Constants.kLiftSpeed);
        double elevate_speed = SmartDashboard.getNumber("Elevator Speed", Constants.kElevateSpeed);

        m_elevatorSubsystem.lifter.set(lift_speed*(m_lift.getAsDouble()-m_liftLoose.getAsDouble()));

        if (m_elevatorUp.getAsBoolean()) {
            m_elevatorSubsystem.elevator.set(elevate_speed);
        }
        else if (m_elevatorDown.getAsBoolean()) {
            m_elevatorSubsystem.elevator.set(-elevate_speed);
        }
        else {
            m_elevatorSubsystem.elevator.set(0);
        }
    }
}
