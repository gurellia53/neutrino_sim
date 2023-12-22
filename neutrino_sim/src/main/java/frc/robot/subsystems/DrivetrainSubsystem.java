package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;

public class DrivetrainSubsystem extends SubsystemBase {
    protected CANSparkMax m_left;
    protected CANSparkMax m_right;
    protected DifferentialDrive m_drive;

    protected DifferentialDriveOdometry m_odometry;
    protected Pose2d m_pose;

    protected RelativeEncoder m_encoder_left;
    protected RelativeEncoder m_encoder_right;

    private AHRS m_navX = new AHRS(SPI.Port.kMXP);

    public DrivetrainSubsystem() {
        m_left = new CANSparkMax(10, MotorType.kBrushless);
        m_right = new CANSparkMax(20, MotorType.kBrushless);
        m_encoder_left = m_left.getEncoder();
        m_encoder_right = m_right.getEncoder();
        m_encoder_left.setPositionConversionFactor(0.05);
        m_encoder_right.setPositionConversionFactor(0.05);
        m_drive = new DifferentialDrive(m_left, m_right);
        m_drive.setDeadband(0.1);

        m_pose = new Pose2d();
        m_odometry = new DifferentialDriveOdometry(Rotation(), m_encoder_left.getPosition(),
                m_encoder_right.getPosition());
    }

    @Override
    public void periodic() {
        m_pose = m_odometry.update(Rotation(),
                m_encoder_left.getPosition(),
                m_encoder_right.getPosition());
    }

    public void Drive(double throttle, double rotation) {
        m_drive.arcadeDrive(-throttle, -rotation);
    }

    private Rotation2d Rotation() {
        return Rotation2d.fromDegrees(-1 * m_navX.getYaw());
    }
}
