package org.firstinspires.ftc.teamcode.Shooter;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Intake.IntakeSubsystem;

public class ShooterCommand extends CommandBase {
    ShooterSubsystem shooterSubsystem;
    IntakeSubsystem intakeSubsystem;
    ElapsedTime reverseIntake;
    GamepadEx gamepadEx;
    public ShooterCommand(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, GamepadEx gamepadEx){
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.gamepadEx = gamepadEx;
        reverseIntake = new ElapsedTime();
        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    @Override
    public void initialize() {
        reverseIntake.reset();
        reverseIntake.startTime();
        shooterSubsystem.start();


        super.initialize();
    }

    @Override
    public void execute() {
        if (gamepadEx.isDown(GamepadKeys.Button.LEFT_BUMPER)){
            intakeSubsystem.start();
        }
        else {
            intakeSubsystem.stop();
        }

        if (shooterSubsystem.getVelocity() >= 2170){ //2100 - 2150
            intakeSubsystem.start();
        }
        else {
            intakeSubsystem.stop();
        }
        //if (shooterSubsystem.getVelocity() >= (ShooterConfig.targetVelocity - 20)) {
        //    intakeSubsystem.start();
        //}
        //else {
        //    intakeSubsystem.stop();
        //}
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
