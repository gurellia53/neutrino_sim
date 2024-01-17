package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.WheelSubsystem;

public class WheelStop extends Command {
    WheelSubsystem m_wheel;

    public WheelStop(WheelSubsystem wheel) {
        m_wheel = wheel;
        addRequirements(m_wheel);
    }

    @Override
    public void initialize() {
        m_wheel.Stop();
    }

    @Override
    public void execute() {
    }
}
