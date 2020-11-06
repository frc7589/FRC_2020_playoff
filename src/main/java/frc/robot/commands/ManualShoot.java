package frc.robot.commands;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualShoot extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;
    private final BooleanSupplier m_shootTrigger;
    private final BooleanSupplier m_shootFire;
    private final BooleanSupplier m_susanClock;
    private final BooleanSupplier m_susanCounter;
    private final BooleanSupplier m_backward;

    public ManualShoot(ShooterSubsystem shooterSubsystem, BooleanSupplier shootTrigger, BooleanSupplier shootFire,
                       BooleanSupplier susanClock, BooleanSupplier susanCounter, BooleanSupplier backward) {
        m_shooterSubsystem = shooterSubsystem;
        m_shootTrigger = shootTrigger;
        m_shootFire = shootFire;
        m_susanClock = susanClock;
        m_susanCounter = susanCounter;
        m_backward = backward;
        addRequirements(m_shooterSubsystem);
    }

    @Override
    public void initialize() {
        m_shooterSubsystem.lazySusan.setSelectedSensorPosition(0);
    }

    @Override
    public void execute() {
        double susanSpeed = SmartDashboard.getNumber("Susan Speed", Constants.kSusanSpeed);
        double shootTrigger = SmartDashboard.getNumber("Shoot Trigger Speed", Constants.kShootTrigger);
        double manualShootSpeed = SmartDashboard.getNumber("Manual Shoot Speed", Constants.kManualShootFireSpeed);
        double autoShootSpeed = SmartDashboard.getNumber("Auto Shoot Speed", Constants.kAutoShootSpeed);

        if (m_shootTrigger.getAsBoolean()) {
            m_shooterSubsystem.trigger.set(shootTrigger);
        }
        else {
            m_shooterSubsystem.trigger.set(0);
        }

        if (m_susanClock.getAsBoolean()) {m_shooterSubsystem.lazySusan.set(susanSpeed); }
        else if (m_susanCounter.getAsBoolean()) {m_shooterSubsystem.lazySusan.set(-susanSpeed); }
        else {m_shooterSubsystem.lazySusan.set(0); }

        if (m_shootFire.getAsBoolean()) {
            m_shooterSubsystem.wheel.set(manualShootSpeed);
            //m_shooterSubsystem.wheel.set(ControlMode.Velocity, autoShootSpeed);
        }
        else {
            m_shooterSubsystem.wheel.set(0);
        }

        if (m_backward.getAsBoolean()) {
            m_shooterSubsystem.wheel.set(-Constants.kManualShootFireSpeed/2);
            m_shooterSubsystem.trigger.set(-Constants.kShootTrigger/2);
        }
        //System.out.println(m_shooterSubsystem.wheel.getSelectedSensorVelocity());
    }
}
