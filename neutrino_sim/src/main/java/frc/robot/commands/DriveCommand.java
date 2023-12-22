package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveCommand extends Command {
    DrivetrainSubsystem m_drivetrain;
    XboxController m_controller;

    public DriveCommand(DrivetrainSubsystem drivetrain, XboxController controller) {
        m_drivetrain = drivetrain;
        m_controller = controller;
        addRequirements(m_drivetrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_drivetrain.Drive(m_controller.getLeftY(), m_controller.getLeftX());
    }
}
