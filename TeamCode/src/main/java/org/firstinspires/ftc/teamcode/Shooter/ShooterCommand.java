package org.firstinspires.ftc.teamcode.Shooter;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Intake.IntakeSubsystem;

public class ShooterCommand extends CommandBase {
    ShooterSubsystem shooterSubsystem;
    IntakeSubsystem intakeSubsystem;
    public ShooterCommand(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem){
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.start();
        super.initialize();
    }

    @Override
    public void execute() {
        if (shooterSubsystem.getVelocity() >= (ShooterConfig.targetVelocity - 20)){
            intakeSubsystem.start();
        }
        else {
            intakeSubsystem.stop();
        }
        super.execute();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stop();
        intakeSubsystem.stop();
    }
}
