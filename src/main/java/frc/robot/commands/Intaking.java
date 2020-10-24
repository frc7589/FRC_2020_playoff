package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intaking extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;
    private final BooleanSupplier m_shootPressed;

    public Intaking(IntakeSubsystem intakeSubsystem, BooleanSupplier shootPressed) {
        m_intakeSubsystem = intakeSubsystem;
        m_shootPressed = shootPressed;
        
        addRequirements(m_intakeSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (m_shootPressed.getAsBoolean()) {
            m_intakeSubsystem.leftArm.set(Value.kForward);
            m_intakeSubsystem.rightArm.set(Value.kForward);
        }
        else {
            m_intakeSubsystem.leftArm.set(Value.kOff);
            m_intakeSubsystem.rightArm.set(Value.kOff);
        }
        
    }
}
