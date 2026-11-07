package org.firstinspires.ftc.teamcode.Shooter;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class ShooterConfig {
    public static double kP = 0.005;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double kF = 0.00055;
    public static double targetVelocity = 590; // ticks/seg
}
