package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveCommand extends Command {
    DrivetrainSubsystem m_drivetrain;
    CommandXboxController m_controller;

    public DriveCommand(DrivetrainSubsystem drivetrain, CommandXboxController controller) {
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
