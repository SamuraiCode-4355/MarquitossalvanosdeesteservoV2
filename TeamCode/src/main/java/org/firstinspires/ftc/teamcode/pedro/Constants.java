package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(
            c -> {
                c.frontLeftName.set("FLMotor");
                c.backLeftName.set("BLMotor");
                c.frontRightName.set("FRMotor");
                c.backRightName.set("BRMotor");

                c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
                c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
            }
    );

    public static PinpointConfig localizerConfig = new PinpointConfig(
            c -> {
                c.name.set("pinpoint");
                c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
                c.xPodOffset.set(-3.4165);
                c.yPodOffset.set(3.3400); //2.2420772041861468)
                c.xPodOffset.set(-3.4);
                c.yPodOffset.set(3.2
                ); //2.2420772041861468)
                c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
                c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
                c.globalDistanceUnit.set(DistanceUnit.INCH);
                c.offsetUnits.set(DistanceUnit.INCH);            }
    );

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.27046362801032836);
                Controller secondaryTranslationalForward = Controller.proportional(0.09992908362857579);
                Controller primaryTranslationalLateral = Controller.proportional(0.3612704950141833);
                Controller secondaryTranslationalLateral = Controller.proportional(0.13347979458232614);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.017786448524994395));
                c.brake.set(Controller.proportionalFeedforward(0.015118481246245236));

                c.headingFeedback.set(Controller.proportional(4.089268689757313));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.0390271541242976, 0.004485986694540785));

                c.linearBrakeCoefficients.set(Matrix.diag(0.07805064257279086, 0.046849230132295965));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.001096859902068301, 0.0017021014753849434));

                c.maxAchievableForwardVelocity.set(59.538295109231434);
                c.maxAchievableStrafeVelocity.set(47.62846929093583);
                c.naturalForwardDeceleration.set(34.575086846472104);
                c.naturalStrafeDeceleration.set(66.99496362295167);
            }
    );


    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}