package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShoot extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;

    boolean facing_center = false;

    NetworkTableInstance inst;
    NetworkTable RPiTable;
    double centerX;
    double centerY;
    double area;

    public AutoShoot(ShooterSubsystem shooterSubsystem) {
        m_shooterSubsystem = shooterSubsystem;
        addRequirements(m_shooterSubsystem);
        inst = NetworkTableInstance.getDefault();
        RPiTable = inst.getTable("RPi");
    }

    @Override
    public void initialize() {
        
    }
    

    @Override
    public void execute() {
        centerX = RPiTable.getEntry("center X").getDouble(0)-320;
        centerY = RPiTable.getEntry("center Y").getDouble(0)-240;
        area = RPiTable.getEntry("area").getDouble(0);

        if (area > 100) {
            double pos = m_shooterSubsystem.lazySusan.getSelectedSensorPosition();
            double move = 0;
            if (centerX >= 30) move = Math.log10(centerX/5)/5;
            else if (centerX <= -30) move = -Math.log10(-centerX)/7;
            
            if (pos > -300000 && pos < 350000) {
                m_shooterSubsystem.lazySusan.set(move);
            }
            else if (pos <= -300000) {
                m_shooterSubsystem.lazySusan.set(0.5);
            }
            else if (pos >= 350000) {
                m_shooterSubsystem.lazySusan.set(-0.5);
            }
        }
        else {
            m_shooterSubsystem.lazySusan.set(0);
        }
    }
}


