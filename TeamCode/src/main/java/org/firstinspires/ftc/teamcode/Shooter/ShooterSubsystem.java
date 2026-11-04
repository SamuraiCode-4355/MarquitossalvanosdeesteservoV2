package org.firstinspires.ftc.teamcode.Shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class ShooterSubsystem extends SubsystemBase {
    private final MotorEx shooterMotor;
    private final PIDFController pidController;
    private double targetVelocity = 0;
    private double manualPower = 0;
    private boolean pidEnabled = true;

    public ShooterSubsystem(HardwareMap hwMap) {
        shooterMotor = new MotorEx(hwMap, "ShooterMotor");
        shooterMotor.setInverted(false);
        shooterMotor.setRunMode(Motor.RunMode.RawPower);

        pidController = new PIDFController(0.01, 0, 0, 0.004);
        pidController.setTolerance(30);
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
        setTargetVelocity(570);
    }
    public void stop(){
        setTargetVelocity(0);
        manualPower = 0;
    }

    public double getVelocity() { return shooterMotor.getVelocity(); }
    public boolean atTarget()   { return pidEnabled && pidController.atSetPoint(); }

    @Override
    public void periodic() {
        if (!pidEnabled) {
            shooterMotor.set(Math.max(-1, Math.min(1, manualPower)));
            return;
        }

        if (targetVelocity == 0) {
            shooterMotor.set(0);
            return;
        }

        double out = pidController.calculate(getVelocity());
        shooterMotor.set(Math.max(-1, Math.min(1, out)));
    }
}
