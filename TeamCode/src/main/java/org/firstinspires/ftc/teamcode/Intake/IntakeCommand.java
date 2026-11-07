package org.firstinspires.ftc.teamcode.Intake;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;


public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final ShooterSubsystem shooterSubsystem;


    public IntakeCommand(IntakeSubsystem intakeSubsystem,ShooterSubsystem shooterSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(intakeSubsystem,shooterSubsystem);
    }


    @Override
    public void initialize() {}

    @Override
    public void execute(){
        intakeSubsystem.start();
        shooterSubsystem.disablePID();
        shooterSubsystem.setManualPower(-0.3);
    }
    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
        shooterSubsystem.disablePID();
        shooterSubsystem.setManualPower(0);
        intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
