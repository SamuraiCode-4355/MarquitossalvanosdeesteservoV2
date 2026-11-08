package org.firstinspires.ftc.teamcode.Shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class ShooterSubsystem extends SubsystemBase {
    private final MotorEx shooterMotor1; //DOWN
    private final MotorEx shooterMotor2; //UP
    private final PIDFController pidController;
    private double targetVelocity = ShooterConfig.targetVelocity;
    private double manualPower = 0;
    private boolean pidEnabled = false;

    public ShooterSubsystem(HardwareMap hwMap) {
        shooterMotor1 = new MotorEx(hwMap, "ShooterMotorDOWN");
        shooterMotor1.setInverted(false);
        shooterMotor1.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        shooterMotor1.setRunMode(Motor.RunMode.RawPower);

        shooterMotor2 = new MotorEx(hwMap, "ShooterMotorUP");
        shooterMotor2.setInverted(true);
        shooterMotor2.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        shooterMotor2.setRunMode(Motor.RunMode.RawPower);

        pidController = new PIDFController(ShooterConfig.kP, ShooterConfig.kI, ShooterConfig.kD, ShooterConfig.kF);
    }

    public void setTargetVelocity(double tps) {
        targetVelocity = tps;
        pidController.setSetPoint(tps);
    }

    public void setManualPower(double power) {
        manualPower = power;
    }

    public void disablePID() {
        pidEnabled = false;
    }

    public void enablePID() {
        pidEnabled = true;
        pidController.reset();
    }

    public void start(){
        setTargetVelocity(ShooterConfig.targetVelocity);
        manualPower = 0.95;
    }
    public void stop(){
        setTargetVelocity(0);
        manualPower = 0;
    }

    public double getVelocity() { return shooterMotor1.getVelocity(); }
    public boolean atTarget()   { return pidEnabled && pidController.atSetPoint(); }

    @Override
    public void periodic() {
        double power;

        if (!pidEnabled) {
            power = Math.max(-1, Math.min(1, manualPower));
        } else if (targetVelocity == 0) {
            power = 0;
        } else {
            double out = pidController.calculate(getVelocity());
            power = Math.max(-1, Math.min(1, out));
        }

        shooterMotor1.set(power);
        shooterMotor2.set(power);
    }
}
