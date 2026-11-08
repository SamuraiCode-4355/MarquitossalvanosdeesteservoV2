package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;

public class IntakeCommandReverse extends CommandBase {
    IntakeSubsystem intakeSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    ElapsedTime intakeTimer;
    boolean finish = false;

    public IntakeCommandReverse(IntakeSubsystem intakeSubsystem,ShooterSubsystem shooterSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        intakeTimer = new ElapsedTime();
        intakeTimer.reset();
        addRequirements(intakeSubsystem,shooterSubsystem);
    }

    @Override
    public void execute() {
        super.execute();
        if (intakeTimer.milliseconds() <= 100){
            intakeSubsystem.reverse();
        }
        else {
            intakeSubsystem.stop();
            finish = true;
        }
//        shooterSubsystem.disablePID();
//        intakeSubsystem.reverse();
//        shooterSubsystem.setManualPower(-0.3);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        finish = false;
//        shooterSubsystem.enablePID();
//        shooterSubsystem.setManualPower(0);
//        intakeSubsystem.stop();
    }
    @Override
    public boolean isFinished() {
        return false;
    }

}
