package org.firstinspires.ftc.teamcode.Teleop;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Autonomous.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Autonomous.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Autonomous.Subsystems.Transfer;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

@Configurable
@TeleOp(name = "NextFTC TeleOp Program Java")
public class NextFTCTeleopTest extends NextFTCOpMode {
    public NextFTCTeleopTest() {
        addComponents(
                new SubsystemComponent(Intake.INSTANCE, Shooter.INSTANCE, Transfer.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }
    TelemetryManager tm = PanelsTelemetry.INSTANCE.getTelemetry();

    private final MotorEx frontLeftMotor = new MotorEx("front_left_drive");
    private final MotorEx frontRightMotor = new MotorEx("front_right_drive").reversed();
    private final MotorEx backLeftMotor = new MotorEx("back_left_drive");
    private final MotorEx backRightMotor = new MotorEx("back_right_drive").reversed();;

    @Override
    public void onStartButtonPressed() {
        Command driverControlled = new MecanumDriverControlled(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY(),
                Gamepads.gamepad1().leftStickX().negate(),
                Gamepads.gamepad1().rightStickX().negate()
        );
        driverControlled.schedule();

        Gamepads.gamepad1().leftBumper()
                .whenBecomesTrue(Intake.turnon)
                .whenBecomesFalse(Intake.turnoff);

        Gamepads.gamepad1().leftTrigger().greaterThan(0.2)
                .whenBecomesTrue(Transfer.turnon)
                .whenBecomesFalse(Transfer.turnoff);

        Gamepads.gamepad1().rightBumper()
                .whenBecomesTrue((Shooter.INSTANCE.togglePeriodicOn).then(Shooter.turnon))
                .whenBecomesFalse(Shooter.INSTANCE.togglePeriodicOff);
                telemetry.addData("Velocity of shooter", Shooter.INSTANCE.returnVelocity());
                telemetry.update();
    }
}