package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intaking extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;
    private final BooleanSupplier m_toggleIntake;
    private final BooleanSupplier m_toggleArm;
    private boolean arm_out = false;
    private boolean intake_enabled = false;


    public Intaking(IntakeSubsystem intakeSubsystem, BooleanSupplier toggleIntake, BooleanSupplier toggleArm) {
        m_intakeSubsystem = intakeSubsystem;
        m_toggleIntake = toggleIntake;
        m_toggleArm = toggleArm;
        
        addRequirements(m_intakeSubsystem);
    }

    @Override
    public void initialize() {
        arm_out = false;
        intake_enabled = false;
    }

    @Override
    public void execute() {
        if (m_toggleArm.getAsBoolean()) arm_out = !arm_out;
        if (arm_out) {
            m_intakeSubsystem.leftArm.set(Value.kForward);
            m_intakeSubsystem.rightArm.set(Value.kForward);
        }
        else {
            m_intakeSubsystem.leftArm.set(Value.kReverse);
            m_intakeSubsystem.rightArm.set(Value.kReverse);
        }
        
        if (m_toggleIntake.getAsBoolean()) intake_enabled = !intake_enabled;
        if (intake_enabled) {
            m_intakeSubsystem.intaker.set(Constants.kIntakeSpeed);
        }
        else {
            m_intakeSubsystem.intaker.set(0);
        }
    }
}
