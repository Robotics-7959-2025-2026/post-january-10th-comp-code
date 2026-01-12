package org.firstinspires.ftc.teamcode.Autonomous.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public  class Transfer implements Subsystem {
    public static final Transfer INSTANCE = new Transfer();
    private Transfer() { }

    private static MotorEx transfer = new MotorEx("transfer");

    public static final Command turnon = new InstantCommand(() -> {
        transfer.setPower(0.7);
    });

    public static final Command turnoff = new InstantCommand(() -> {
        transfer.setPower(0);
    });

}