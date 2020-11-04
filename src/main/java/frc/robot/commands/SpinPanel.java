package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SpinnerSubsystem;

public class SpinPanel extends CommandBase {
    private final SpinnerSubsystem m_spinnerSubsystem;
    private final BooleanSupplier m_spin;
    private final BooleanSupplier m_armUp;
    private final BooleanSupplier m_armDown;

    public SpinPanel(SpinnerSubsystem spinnerSubsystem, BooleanSupplier spin,
                   BooleanSupplier spinUp, BooleanSupplier spinDown) {
        m_spinnerSubsystem = spinnerSubsystem;
        m_spin = spin;
        m_armUp = spinUp;
        m_armDown = spinDown;
        addRequirements(spinnerSubsystem);
    }

    @Override
    public void execute() {
        double arm_speed = SmartDashboard.getNumber("Arm Speed", Constants.kArmSpeed);
        double spin_speed = SmartDashboard.getNumber("Spin Speed", Constants.kSpinSpeed);

        if (m_armUp.getAsBoolean()) {
            m_spinnerSubsystem.arm.set(arm_speed);
        }
        else if (m_armDown.getAsBoolean()) {
            m_spinnerSubsystem.arm.set(-arm_speed);
        }
        else {
            m_spinnerSubsystem.arm.set(0);
        }

        if (m_spin.getAsBoolean()) {
            m_spinnerSubsystem.spinner.set(spin_speed);
        }
        else {
            m_spinnerSubsystem.spinner.set(0);
        }
    }
}
