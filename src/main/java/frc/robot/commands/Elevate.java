package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class Elevate extends CommandBase {
    private final ElevatorSubsystem m_elevatorSubsystem;
    private final BooleanSupplier m_lift_l;
    private final BooleanSupplier m_lift_r;
    private final BooleanSupplier m_liftLoose;
    private final BooleanSupplier m_elevatorUp;
    private final BooleanSupplier m_elevatorDown;

    public Elevate(ElevatorSubsystem elevatorSubsystem, BooleanSupplier lift_l, 
                   BooleanSupplier lift_r, BooleanSupplier liftLoose,
                   BooleanSupplier elevatorUp, BooleanSupplier elevatorDown) {
        m_elevatorSubsystem = elevatorSubsystem;
        m_lift_l = lift_l;
        m_lift_r = lift_r;
        m_liftLoose = liftLoose;
        m_elevatorUp = elevatorUp;
        m_elevatorDown = elevatorDown;
        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void execute() {
        double lift_speed = SmartDashboard.getNumber("Lifters Speed", Constants.kLiftSpeed);
        double elevate_speed = SmartDashboard.getNumber("Elevator Speed", Constants.kElevateSpeed);

        if (m_lift_l.getAsBoolean()) {
            m_elevatorSubsystem.lifter_l.set(lift_speed);
        }
        else {
            m_elevatorSubsystem.lifter_l.set(0);
        }
        if (m_lift_r.getAsBoolean()) {
            m_elevatorSubsystem.lifter_r.set(lift_speed);
        }
        else {
            m_elevatorSubsystem.lifter_r.set(0);
        }
        if (m_liftLoose.getAsBoolean()) {
            m_elevatorSubsystem.lifter.set(-lift_speed);
        }

        if (m_elevatorUp.getAsBoolean()) {
            m_elevatorSubsystem.elevator.set(elevate_speed);
        }
        else if (m_elevatorDown.getAsBoolean()) {
            m_elevatorSubsystem.elevator.set(-elevate_speed/3);
        }
        else {
            m_elevatorSubsystem.elevator.set(0);
        }
    }
}
