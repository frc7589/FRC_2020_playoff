package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShoot extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;

    boolean toggle = false;
    NetworkTableInstance inst;
    NetworkTable RPiTable;

    boolean inPosition = false;

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
        toggle = !toggle;
    }
    

    @Override
    public void execute() {
        if (toggle) {
            centerX = RPiTable.getEntry("center X").getDouble(0)-320;
            centerY = RPiTable.getEntry("center Y").getDouble(0)-240;
            area = RPiTable.getEntry("area").getDouble(0);
            double pos = m_shooterSubsystem.lazySusan.getSelectedSensorPosition();
            
    
            if (area > 100) {
                double move = 0;
                if (centerX >= 20) move = Math.log10(centerX/5)/7+0.1;
                else if (centerX <= -20) move = -Math.log10(-centerX/5)/7-0.1;
                else move = centerX/360;
                
                if (pos > -300000 && pos < 300000) {
                    m_shooterSubsystem.lazySusan.set(move);
                }
                else if (pos <= -300000) {
                    m_shooterSubsystem.lazySusan.set(0.5);
                }
                else if (pos >= 300000) {
                    m_shooterSubsystem.lazySusan.set(-0.5);
                }
            }
            else {
                m_shooterSubsystem.lazySusan.set(0);
            }
    
            inPosition = (Math.abs(centerX) < 20);
            System.out.println(inPosition);
        }
    }

    @Override
    public boolean isFinished() {
        return inPosition;
    }
}


