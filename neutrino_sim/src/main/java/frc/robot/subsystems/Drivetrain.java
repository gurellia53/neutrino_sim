package frc.robot.subsystems;

import com.revrobotics.REVPhysicsSim;

import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.hal.simulation.SimDeviceDataJNI;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotController;

public class Drivetrain extends DrivetrainSubsystem {

    // Create the simulation model of our drivetrain.
    static final double KvLinear = 2.4661;
    static final double KaLinear = 0.5174;
    static final double KvAngular = 2.0;
    static final double KaAngular = 0.7;
    DifferentialDrivetrainSim m_driveSim = new DifferentialDrivetrainSim(
            LinearSystemId.identifyDrivetrainSystem(KvLinear, KaLinear, KvAngular, KaAngular),
            DCMotor.getNEO(2), // 2 NEO motors on each side of the drivetrain.
            8.0, // 8.0:1 gearing reduction.
            0.7112, // The track width is 0.7112 meters.
            Units.inchesToMeters(3), // The robot uses 3" radius wheels.
            null);
    private Field2d m_field = new Field2d();

    public Drivetrain() {
        SmartDashboard.putData("Field", m_field);
    }

    public void simulationInit() {
        REVPhysicsSim.getInstance().addSparkMax(m_left, DCMotor.getNEO(1));
        REVPhysicsSim.getInstance().addSparkMax(m_right, DCMotor.getNEO(1));
    }

    @Override
    public void simulationPeriodic() {
        // update motors because REV is incapable
        m_left.setVoltage(m_left.get());
        m_right.setVoltage(m_right.get());

        // drive sim
        m_driveSim.setInputs(m_left.get() * RobotController.getInputVoltage(),
                m_right.get() * RobotController.getInputVoltage());
        m_driveSim.update(0.02);

        // navx gyro sim
        int dev = SimDeviceDataJNI.getSimDeviceHandle("navX-Sensor[0]");
        SimDouble angle = new SimDouble(SimDeviceDataJNI.getSimValueHandle(dev, "Yaw"));
        angle.set(-m_driveSim.getHeading().getDegrees());

        // update the field2d
        m_field.setRobotPose(m_odometry.getPoseMeters());
    }
}
