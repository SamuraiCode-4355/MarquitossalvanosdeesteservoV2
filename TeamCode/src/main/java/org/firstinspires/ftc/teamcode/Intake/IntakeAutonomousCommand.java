package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;


public class IntakeAutonomousCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    GamepadEx gamepadEx;
    boolean crono = false;
    ElapsedTime timer;

    public IntakeAutonomousCommand(IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem, GamepadEx gamepadEx) {
        this.intakeSubsystem = intakeSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        this.gamepadEx = gamepadEx;
        timer = new ElapsedTime();
        addRequirements(intakeSubsystem,shooterSubsystem);
    }


    @Override
    public void initialize() {}

    @Override
    public void execute(){
        if (gamepadEx.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) < 0.1 && !crono){
            timer.reset();
            timer.startTime();
            crono = true;
        }
        if (crono){
            intakeSubsystem.reverse();
        }
        else {
            intakeSubsystem.start();
            shooterSubsystem.disablePID();
            shooterSubsystem.setManualPower(-0.3);
        }
    }
    @Override
    public void end(boolean interrupted) {
        timer.reset();
        intakeSubsystem.stop();
        shooterSubsystem.disablePID();
        shooterSubsystem.setManualPower(0);
        intakeSubsystem.stop();
        crono = false;
    }

    @Override
    public boolean isFinished() {
        return timer.milliseconds() >= 100 && crono;
    }

}