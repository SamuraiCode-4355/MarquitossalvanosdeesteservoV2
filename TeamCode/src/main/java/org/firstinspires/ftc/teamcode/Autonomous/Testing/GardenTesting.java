/* ============================================================= *
 *                 Turtle Tracer — Auto-Generated                *
 *                                                               *
 *  Version: 2.7.0.                                              *
 *  Copyright (c) 2026 Matthew Allen                             *
 *                                                               *
 *  THIS FILE IS AUTO-GENERATED — DO NOT EDIT MANUALLY.          *
 *  Changes will be overwritten when regenerated.                *
 * ============================================================= */

package org.firstinspires.ftc.teamcode.Autonomous.Testing;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.api.Paths.path;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "Garden Testing", group = "Autonomous")
@Configurable // Panels
public class GardenTesting extends OpMode {

  private TelemetryManager panelsTelemetry; // Panels Telemetry instance
  public Follower follower; // Pathing follower instance
  private final PoseFactory p = PoseFactory.degrees();
  private int pathState; // Current autonomous path state (state machine)
  private ElapsedTime pathTimer; // Timer for path state machine
  private Paths paths; // Paths defined in the Paths class

  @Override
  public void init() {
    panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
    // ...
    panelsTelemetry.debug("Status", "Initialized");
    panelsTelemetry.update(telemetry);

    follower = Constants.create(hardwareMap);
    follower.setPose(p.of(60.000, 10.000, 90.000));

    pathTimer = new ElapsedTime();
    paths = new Paths(follower); // Build paths
  }

  @Override
  public void loop() {
    follower.update(); // Update follower
    pathState = autonomousPathUpdate(); // Update autonomous state machine

    // Log values to Panels and Driver Station
    panelsTelemetry.debug("Path State", pathState);
    panelsTelemetry.debug("X", follower.pose().x());
    panelsTelemetry.debug("Y", follower.pose().y());
    panelsTelemetry.debug("Heading", follower.pose().heading());
    panelsTelemetry.update(telemetry);
  }

  public static class Paths {

    private static final PoseFactory p = PoseFactory.degrees();
    public Path DriveToShoot;
    public Path line2;
    public Path line3;
    public Path line4;
    public Path line5;
    public Path line6;
    public Path line7;

    public Paths(Follower follower) {
      DriveToShoot = line(
        p.of(60.000, 10.000, 0.0),
        p.of(60.000, 25.000, 0.0)
      ).linear(Math.toRadians(90), Math.toRadians(90));

      line2 = line(p.of(60.000, 25.000, 0.0), p.of(9.125, 17.594, 0.0)).linear(
        Math.toRadians(-90),
        Math.toRadians(-90)
      );

      line3 = line(p.of(9.125, 17.594, 0.0), p.of(60.000, 33.748, 0.0)).linear(
        Math.toRadians(-90),
        Math.toRadians(-90)
      );

      line4 = line(
        p.of(60.000, 33.748, 0.0),
        p.of(60.000, 107.242, 0.0)
      ).linear(Math.toRadians(-90), Math.toRadians(-90));

      line5 = line(
        p.of(60.000, 107.242, 0.0),
        p.of(60.000, 141.000, 0.0)
      ).linear(Math.toRadians(-90), Math.toRadians(-90));

      line6 = line(
        p.of(60.000, 133.076, 0.0),
        p.of(60.000, 118.549, 0.0)
      ).linear(Math.toRadians(-90), Math.toRadians(-90));

      line7 = line(
        p.of(60.000, 118.549, 0.0),
        p.of(13.5, 118.549, 0.0) //12.637
      ).linear(Math.toRadians(-90), Math.toRadians(-90));
    }
  }

  public int autonomousPathUpdate() {
    switch (pathState) {
      case 0:
        setPathState(1);
        break;
      case 1:
        if (pathTimer.milliseconds() > 4000) {
          setPathState(2);
        }

        break;
      case 2:
        follower.follow(paths.DriveToShoot);
        setPathState(3);
        break;
      case 3:
        if (!follower.isBusy()) {
          setPathState(4);
        }
        break;
      case 4:
        follower.hold(follower.pose().withHeading(-1.571));
        follower.algorithm().reset();
        setPathState(5);
        break;
      case 5:
        if (
          Math.abs(
            com.pedropathing.utils.Angle.error(
              follower.pose().heading(),
              -1.571
            )
          ) < 0.01
        ) {
          setPathState(6);
        }
        break;
      case 6:
        follower.follow(paths.line2);
        setPathState(7);
        break;
      case 7:
        if (!follower.isBusy()) {
          setPathState(8);
        }
        break;
      case 8:
        setPathState(9);
        break;
      case 9:
        if (pathTimer.milliseconds() > 4000) {
          setPathState(10);
        }
        break;
      case 10:
        follower.follow(paths.line3);
        setPathState(11);
        break;
      case 11:
        if (!follower.isBusy()) {
          setPathState(12);
        }
        break;
      case 12:
        follower.follow(paths.line4);
        setPathState(13);
        break;
      case 13:
        if (!follower.isBusy()) {
          setPathState(14);
        }
        break;
      case 14:
        follower.hold(follower.pose().withHeading(-1.571));
        follower.algorithm().reset();
        setPathState(15);
        break;
      case 15:
        if (
          Math.abs(
            com.pedropathing.utils.Angle.error(
              follower.pose().heading(),
              -1.571
            )
          ) < 0.01
        ) {
          setPathState(16);
        }
        break;
      case 16:
        follower.follow(paths.line5);
        setPathState(17);
        break;
      case 17:
        if (!follower.isBusy()) {
          setPathState(18);
        }
        break;
      case 18:
        setPathState(19);
        break;
      case 19:
        if (pathTimer.milliseconds() > 4000) {
          setPathState(20);
        }
        break;
      case 20:
        follower.follow(paths.line6);
        setPathState(21);
        break;
      case 21:
        if (!follower.isBusy()) {
          setPathState(22);
        }
        break;
      case 22:
        follower.follow(paths.line7);
        setPathState(23);
        break;
      case 23:
        if (!follower.isBusy()) {
          setPathState(24);
        }
        break;
      case 24:
        requestOpModeStop();
        pathState = -1;
        break;
    }
    return pathState;
  }

  public void setPathState(int pState) {
    pathState = pState;
    pathTimer.reset();
  }
}
