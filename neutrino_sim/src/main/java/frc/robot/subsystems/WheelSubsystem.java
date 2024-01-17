package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WheelSubsystem extends SubsystemBase {
    protected CANSparkMax m_motor;
    protected SparkPIDController m_pidController;
    protected RelativeEncoder m_encoder;

    public WheelSubsystem() {
        m_motor = new CANSparkMax(30, MotorType.kBrushless);
        m_encoder = m_motor.getEncoder();
        // m_encoder.setPositionConversionFactor(4096);

        m_pidController = m_motor.getPIDController();
        m_pidController.setFeedbackDevice(m_encoder);
        m_pidController.setP(10.0);
        m_pidController.setI(0.0);
        m_pidController.setD(0.0);
        m_pidController.setFF(0.0);
        m_pidController.setIZone(0.0);
        m_pidController.setOutputRange(.1, 1);
    }

    @Override
    public void periodic() {
        // System.out.println("Motor speed: " + m_motor.get() + " " + m_right.get());
    }

    public void Spin(double throttle) {
        // m_motor.setVoltage(throttle);
        m_pidController.setReference(throttle, CANSparkMax.ControlType.kVelocity);
    }

    public void Stop() {
        m_motor.setVoltage(0.0);
    }

}
