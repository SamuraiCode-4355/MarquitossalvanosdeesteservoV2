package org.firstinspires.ftc.teamcode.Shooter;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Intake.IntakeSubsystem;

public class ShooterAutonomousCommand extends CommandBase {
    ShooterSubsystem shooterSubsystem;
    IntakeSubsystem intakeSubsystem;
    public ShooterAutonomousCommand(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem){
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.start();


        super.initialize();
    }

    @Override
    public void execute() {
        if (shooterSubsystem.getVelocity() >= 2170){ //2100 - 2150
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
