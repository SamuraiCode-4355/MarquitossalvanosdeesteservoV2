package org.firstinspires.ftc.teamcode.Intake;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;


public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private GamepadEx controller;


    public IntakeCommand(IntakeSubsystem intakeSubsystem,ShooterSubsystem shooterSubsystem, GamepadEx mechanismController) {
        this.intakeSubsystem = intakeSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.controller = mechanismController;
        addRequirements(intakeSubsystem);
    }


    @Override
    public void initialize() {
    }
    @Override
    public void execute(){
        shooterSubsystem.disablePID();
        intakeSubsystem.start();
        shooterSubsystem.setManualPower(-0.3);
    }
    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.enablePID();
        shooterSubsystem.setManualPower(0);
        intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
