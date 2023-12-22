package frc.robot.subsystems;

import com.revrobotics.REVPhysicsSim;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Wheel extends WheelSubsystem {
    Mechanism2d m_mech = new Mechanism2d(3, 3);
    MechanismRoot2d m_root = m_mech.getRoot("chassis", 2, 2);
    MechanismLigament2d m_wheel_ligament;

    FlywheelSim m_flywheel_sim;
    double m_last_position_rev = 0.0;

    public Wheel() {
        m_wheel_ligament = m_root.append(new MechanismLigament2d("wheel", 1, 0));
        m_flywheel_sim = new FlywheelSim(DCMotor.getNEO(1), 1.0, 0.002);
        SmartDashboard.putData("Mech2d", m_mech);
    }

    public void simulationInit() {
        REVPhysicsSim.getInstance().addSparkMax(m_motor, DCMotor.getNEO(1));
    }

    @Override
    public void simulationPeriodic() {
        double motor_volts = m_motor.getAppliedOutput() * m_motor.getBusVoltage();
        m_flywheel_sim.setInputVoltage(motor_volts);
        m_flywheel_sim.update(0.02);

        double rev_per_s = m_flywheel_sim.getAngularVelocityRPM();
        System.out.println("flywheel velocity (RPM) " + m_flywheel_sim.getAngularVelocityRPM());
        m_last_position_rev = m_last_position_rev + rev_per_s * 0.02;
        m_wheel_ligament.setAngle(m_last_position_rev * 6);
    }

}
