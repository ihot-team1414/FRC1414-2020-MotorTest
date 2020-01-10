/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private final TalonFX leftMotor = new TalonFX(Constants.LEFT_MOTOR_ID);
  private final TalonFX rightMotor = new TalonFX(Constants.RIGHT_MOTOR_ID);
  private final TalonSRX talon = new TalonSRX(Constants.TALON_SRX_ID);

  public Drivetrain() {
    this.leftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    this.rightMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
  }

  public void arcadeDrive(double throttle, double turn) {
    this.leftMotor.set(ControlMode.PercentOutput, throttle + turn);
    this.rightMotor.set(ControlMode.PercentOutput, throttle - turn);
  }

  public double getLeftEncoder() {
    return this.leftMotor.getSelectedSensorPosition();
  }

  public double getRightEncoder() {
    return this.rightMotor.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Encoder", getLeftEncoder());
    SmartDashboard.putNumber("Right Encoder", getRightEncoder());
  }
}
