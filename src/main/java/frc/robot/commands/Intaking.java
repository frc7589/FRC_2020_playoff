package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intaking extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;
    private final BooleanSupplier m_toggleIntake;
    private final BooleanSupplier m_toggleArm;
    private final BooleanSupplier m_spin;
    private final BooleanSupplier m_spinReversed;
    private boolean arm_out = false;
    private boolean intake_enabled = false;
    private boolean spinning = false;
    private double delayTimer = 0;
    private double spinTimer = 0; 

    private double prev_time;
    private double cur_time;
    private double deltaTime;


    public Intaking(IntakeSubsystem intakeSubsystem, BooleanSupplier toggleIntake,
                    BooleanSupplier toggleArm, BooleanSupplier spin,
                    BooleanSupplier spinReversed) {
        m_intakeSubsystem = intakeSubsystem;
        m_toggleIntake = toggleIntake;
        m_toggleArm = toggleArm;
        m_spin = spin;
        m_spinReversed = spinReversed;
        
        addRequirements(m_intakeSubsystem);
    }

    @Override
    public void initialize() {
        arm_out = false;
        intake_enabled = false;
        cur_time = System.currentTimeMillis();
        prev_time = System.currentTimeMillis();
        deltaTime = 0;
    }

    @Override
    public void execute() {
        double voltage = RobotController.getBatteryVoltage();
        double intakeSpeed = SmartDashboard.getNumber("Intake Speed", Constants.kIntakeSpeed);
        double spinSpeed = SmartDashboard.getNumber("Spin Cylinder Voltage", Constants.kSpinCylinderVoltage)/voltage;
        double spinDelay = SmartDashboard.getNumber("Spin Delay", Constants.kSpinDelay);
        double spinDur = SmartDashboard.getNumber("Spin Durration", Constants.kSpinDurration);

        cur_time = System.currentTimeMillis();
        deltaTime = cur_time - prev_time;

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
            m_intakeSubsystem.intaker.set(intakeSpeed);
        }
        else {
            m_intakeSubsystem.intaker.set(0);
        }

        if (m_spin.getAsBoolean()) {
            if (spinning) {
                spinTimer += deltaTime/1000;
                m_intakeSubsystem.spin.set(spinSpeed);
            }
            else {
                delayTimer += deltaTime/1000;
                m_intakeSubsystem.spin.set(0);
            }
            if (spinTimer > spinDur) {
                spinning = false;
                spinTimer = 0;
            }
            else if (delayTimer > spinDelay) {
                spinning = true;
                delayTimer = 0;
            }
        }
        else if (m_spinReversed.getAsBoolean()){
            m_intakeSubsystem.spin.set(-spinSpeed/2);
        }
        else {
            m_intakeSubsystem.spin.set(0);
        }

        prev_time = cur_time;
    }
}
