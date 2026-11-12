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
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Shooter.ShooterAutonomousCommand;
import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "shooterTesting", group = "Testing")
@Configurable // Panels
public class ShootnPark extends OpMode {

  private TelemetryManager panelsTelemetry; // Panels Telemetry instance
  public Follower follower; // Pathing follower instance
  private final PoseFactory p = PoseFactory.degrees();
  private int pathState; // Current autonomous path state (state machine)
  private ElapsedTime pathTimer; // Timer for path state machine
  private Paths paths; // Paths defined in the Paths class
  ShooterAutonomousCommand shooterAutonomousCommand;
  ShooterSubsystem shooterSubsystem;
  IntakeSubsystem intakeSubsystem;

  @Override
  public void init() {
    panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
    shooterSubsystem = new ShooterSubsystem(hardwareMap);
    intakeSubsystem = new IntakeSubsystem(hardwareMap);
    shooterAutonomousCommand = new ShooterAutonomousCommand(shooterSubsystem, intakeSubsystem);
    CommandScheduler.getInstance().reset();
    // ...
    panelsTelemetry.debug("Status", "Initialized");
    panelsTelemetry.update(telemetry);

    follower = Constants.create(hardwareMap);
    follower.setPose(p.of(60.000, 9.813, 90.000));

    pathTimer = new ElapsedTime();
    paths = new Paths(follower); // Build paths
  }

  @Override
  public void loop() {
    follower.update(); // Update follower
    CommandScheduler.getInstance().run();
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
    public Path line1;
    public Path line2;
    public Path line3;

    public Paths(Follower follower) {
      // Max speeds are set through Constants.foresightConfig (Pedro's "Modifying Constants").
      // If the build fails with "cannot find symbol ... foresightConfig", your Constants class needs a public static ForesightConfig foresightConfig that your Follower is built from.
      line1 = line(p.of(60.000, 9.813, 0.0), p.of(24.730, 36.713, 0.0))
        .constant(Math.toRadians(90))
        .with(Constants.foresightConfig.maxVelocityConstraint.at(20.0));

      line2 = line(p.of(24.730, 36.713, 0.0), p.of(24.730, 119.406, 0.0))
        .constant(Math.toRadians(90))
        .with(Constants.foresightConfig.maxVelocityConstraint.at(30.0));

      line3 = line(p.of(24.730, 119.406, 0.0), p.of(9.125, 119.406, 0.0))
        .constant(Math.toRadians(90))
        .with(Constants.foresightConfig.maxVelocityConstraint.at(10.0));
    }
  }

  public int autonomousPathUpdate() {
    switch (pathState) {
      case 0:
        CommandScheduler.getInstance().schedule(shooterAutonomousCommand);
        setPathState(1);
        break;
      case 1:
        if (pathTimer.milliseconds() < 4000) {
          CommandScheduler.getInstance().run();
        }
        else {
          shooterAutonomousCommand.cancel();
          shooterSubsystem.stop();
          setPathState(2);
        }
        break;
      case 2:
        follower.follow(paths.line1);
        setPathState(3);
        break;
      case 3:
        if (!follower.isBusy()) {
          setPathState(4);
        }
        break;
      case 4:
        follower.follow(paths.line2);
        setPathState(5);
        break;
      case 5:
        if (!follower.isBusy()) {
          setPathState(6);
        }
        break;
      case 6:
        follower.follow(paths.line3);
        setPathState(7);
        break;
      case 7:
        if (!follower.isBusy()) {
          setPathState(8);
        }
        break;
      case 8:
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
