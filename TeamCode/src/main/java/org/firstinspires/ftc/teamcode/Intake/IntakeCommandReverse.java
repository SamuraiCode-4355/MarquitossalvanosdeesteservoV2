package org.firstinspires.ftc.teamcode.Intake;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;

public class IntakeCommandReverse extends CommandBase {
    IntakeSubsystem intakeSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private GamepadEx controller;

    public IntakeCommandReverse(IntakeSubsystem intakeSubsystem,ShooterSubsystem shooterSubsystem, GamepadEx mechanismController) {
        this.intakeSubsystem = intakeSubsystem;
        this.controller = mechanismController;
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(intakeSubsystem,shooterSubsystem);
    }

    @Override
    public void execute() {
        super.execute();
        shooterSubsystem.disablePID();
        intakeSubsystem.reverse();
        shooterSubsystem.setManualPower(-0.3);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        shooterSubsystem.enablePID();
        shooterSubsystem.setManualPower(0);
        intakeSubsystem.stop();
    }
    @Override
    public boolean isFinished() {
        return false;
    }

}
