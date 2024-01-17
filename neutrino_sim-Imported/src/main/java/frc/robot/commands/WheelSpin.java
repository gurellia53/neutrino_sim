package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.WheelSubsystem;

public class WheelSpin extends Command {
    WheelSubsystem m_wheel;
    double m_speed;

    public WheelSpin(WheelSubsystem wheel, double speed) {
        m_wheel = wheel;
        m_speed = speed;
        addRequirements(m_wheel);
    }

    @Override
    public void initialize() {
        m_wheel.Spin(m_speed);
    }

    @Override
    public void execute() {
    }
}
