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

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  public static final double INTAKE_SPEED = 0.8;
  private static final double OUTTAKE_SPEED = -0.4;
  public TalonSRX intakeMotor = new TalonSRX(INTAKE_MOTOR_ID);
  public DoubleSolenoid intakePiston = new DoubleSolenoid(PCM_ID, INTAKE_PISTON_FORWARD_ID, INTAKE_PISTON_REVERSE_ID);

  public Intake() {

  }
  // Pneumatics
  public enum IntakePistonState {
    OPEN(1), CLOSED(0);

    private int state;

    IntakePistonState(int state) {
      this.state = state;
    }

    public int getState() {
      return this.state;
    }
  }
  public enum IntakeOuttakeState {
    INTAKE(1), OUTTAKE(0), OFF(2);

    private int state;

    IntakeOuttakeState(int state) {
      this.state = state;
    }

    public int getState() {
      return this.state;
    }
  }

  private volatile IntakePistonState pistonState = IntakePistonState.CLOSED;
  private volatile IntakeOuttakeState intakeOuttakeState = IntakeOuttakeState.OFF;
  private static final DoubleSolenoid.Value PISTON_OPEN_VALUE = DoubleSolenoid.Value.kForward;
  private static final DoubleSolenoid.Value PISTON_CLOSED_VALUE = DoubleSolenoid.Value.kReverse;
  

  public void openIntakePiston() {
    this.pistonState = IntakePistonState.OPEN;
    this.intakePiston.set(PISTON_OPEN_VALUE);
  }

  public void closeIntakePiston() {
    this.pistonState = IntakePistonState.CLOSED;
    this.intakePiston.set(PISTON_CLOSED_VALUE);
  }

  public IntakePistonState getPistonState() {
    return this.pistonState;
  }

  public void toggleIntakePiston() {
    if (this.pistonState == IntakePistonState.OPEN) {
        closeIntakePiston();
    }
    if (this.pistonState == IntakePistonState.CLOSED) {
        openIntakePiston();
    }
  }

  public void intakeEnabled() {
    this.intakeMotor.set(ControlMode.PercentOutput, INTAKE_SPEED);
    this.IntakeOuttakeState = IntakeOuttakeState.INTAKE;
  }
  
  public void outtakeEnabled() {
    this.intakeMotor.set(ControlMode.PercentOutput, OUTTAKE_SPEED);
    this.IntakeOuttakeState = IntakeOuttakeState.OUTTAKE;
  }
  public void intakeDisabled() {
    this.intakeMotor.set(ControlMode.PercentOutput, 0);
    this.IntakeOuttakeState = IntakeOuttakeState.OFF;
  }

  public void deployIntake() {
    openIntakePiston();
    intakeEnabled();
  }

  public void deployOuttake() {
    openIntakePiston();
    outtakeEnabled();
  }

  public void retractIntake() {
    closeIntakePiston();
    intakeDisabled();
  }
    
  @Override
  public void periodic() {
    /
  }
}
