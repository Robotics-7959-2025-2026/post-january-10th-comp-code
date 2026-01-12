package org.firstinspires.ftc.teamcode.Autonomous.Subsystems;


import static org.firstinspires.ftc.teamcode.Teleop.Motors.transfer;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.Powerable;
import dev.nextftc.hardware.powerable.SetPower;

public class Intake implements Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() { }

    private static MotorEx intakeMotor = new MotorEx("shooterMotor");

    public static final Command turnon = new InstantCommand(() -> {
        intakeMotor.setPower(0.7);
    });

    public static final Command turnoff = new InstantCommand(() -> {
        intakeMotor.setPower(0);
    });


}