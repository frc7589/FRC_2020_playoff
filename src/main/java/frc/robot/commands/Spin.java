package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class Spin extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;

    long startTime;
    boolean spiningState; // Whether spin or stop.

    public Spin(IntakeSubsystem intakeSubsystem) {
        m_intakeSubsystem = intakeSubsystem;

        addRequirements(m_intakeSubsystem);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        spiningState = true;
    }

    @Override
    public void execute() {
        if (System.currentTimeMillis() - startTime >= 500) {
            startTime = System.currentTimeMillis();
            spiningState = !spiningState;
        }
        
        if (spiningState) {m_intakeSubsystem.spin.set(-0.25);}
        else {m_intakeSubsystem.spin.set(-0);}
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.spin.set(0);
    }
}
