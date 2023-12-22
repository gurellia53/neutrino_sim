// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.REVPhysicsSim;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {

    private CommandXboxController m_controller;
    private Drivetrain m_drivetrain;
    private Wheel m_wheel;

    public RobotContainer() {
        m_controller = new CommandXboxController(0);
        m_drivetrain = new Drivetrain();
        m_wheel = new Wheel();

        configureBindings();
    }

    private void configureBindings() {
        m_drivetrain.setDefaultCommand(new DriveCommand(m_drivetrain, m_controller));
        m_wheel.setDefaultCommand(new WheelStop(m_wheel));

        m_controller.a().whileTrue(new WheelSpin(m_wheel, 60));
        m_controller.b().whileTrue(new WheelSpin(m_wheel, 120));
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
