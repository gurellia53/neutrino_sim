// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.REVPhysicsSim;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.WheelStop;
import frc.robot.commands.WheelSpin;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Wheel;

public class RobotContainer {

    private XboxController m_controller;
    private Drivetrain m_drivetrain;
    private Wheel m_wheel;
    private JoystickButton m_A;
    private JoystickButton m_B;

    public RobotContainer() {
        m_controller = new XboxController(0);
        m_A = new JoystickButton(m_controller, XboxController.Button.kA.value);
        m_B = new JoystickButton(m_controller, XboxController.Button.kB.value);
        m_drivetrain = new Drivetrain();
        m_wheel = new Wheel();

        configureBindings();
    }

    private void configureBindings() {
        m_drivetrain.setDefaultCommand(new DriveCommand(m_drivetrain, m_controller));
        m_wheel.setDefaultCommand(new WheelStop(m_wheel));

        m_A.whileTrue(new WheelSpin(m_wheel, 60));
        m_B.whileTrue(new WheelSpin(m_wheel, 120));
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }

    public void simulationInit() {
        m_drivetrain.simulationInit();
        m_wheel.simulationInit();
    }

    public void simulationPeriodic() {
        REVPhysicsSim.getInstance().run();
    }
}
