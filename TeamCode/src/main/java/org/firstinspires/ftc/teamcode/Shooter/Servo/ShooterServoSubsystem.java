package org.firstinspires.ftc.teamcode.Shooter.Servo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.AbsoluteAnalogEncoder;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.motors.CRServoGroup;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoExGroup;

public class ShooterServoSubsystem extends SubsystemBase {
    private ServoEx servoCenter;
    public ShooterServoSubsystem(HardwareMap hwMap){
        servoCenter = new ServoEx(hwMap, "servoCenter");
        servoCenter.setInverted(true);
    }
    public void start(){
        servoCenter.set(0);
    }
    public void stop(){
        servoCenter.set(0.35);
    }

    @Override
    public void periodic() {
        super.periodic();
    }
}
