package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualShoot extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;
    private final BooleanSupplier m_shootTrigger;
    private final BooleanSupplier m_shootFire;
    private final BooleanSupplier m_susanClock;
    private final BooleanSupplier m_susanCounter;

    public ManualShoot(ShooterSubsystem shooterSubsystem, BooleanSupplier shootTrigger, BooleanSupplier shootFire,
                       BooleanSupplier susanClock, BooleanSupplier susanCounter) {
        m_shooterSubsystem = shooterSubsystem;
        m_shootTrigger = shootTrigger;
        m_shootFire = shootFire;
        m_susanClock = susanClock;
        m_susanCounter = susanCounter;
        addRequirements(m_shooterSubsystem);
    }

    @Override
    public void execute() {
        if (m_shootTrigger.getAsBoolean()) {
            m_shooterSubsystem.trigger.set(Constants.kShootTrigger);
        }
        else {m_shooterSubsystem.trigger.set(0);}

        if (m_susanClock.getAsBoolean()) {
            m_shooterSubsystem.lazySusan.set(Constants.kSusanSpeed);
        }
        if (m_susanCounter.getAsBoolean()) {
            m_shooterSubsystem.lazySusan.set(-Constants.kSusanSpeed);
        }
        System.out.println(m_shooterSubsystem.lazySusan.getSelectedSensorPosition());
        System.out.println(m_shooterSubsystem.wheel.getSelectedSensorVelocity());
    }
}
