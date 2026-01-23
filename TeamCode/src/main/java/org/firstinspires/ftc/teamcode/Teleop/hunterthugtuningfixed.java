package org.firstinspires.ftc.teamcode.tuningOpModes;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.subsystem.util.newPIDFController;

@Configurable
@TeleOp
public class hunterthugtuningfixed extends OpMode {

    private newPIDFController controller;
    private DcMotorEx motor;
    private DcMotorEx motor2;
    public static double targetVelocity, velocity;
    public static double P,I,kV,kS;
    @Override
    public void init() {
        //TODO: Set motor name and direction
        telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
        motor = hardwareMap.get(DcMotorEx.class, "shooterMotor2");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2 = hardwareMap.get(DcMotorEx.class, "shooterMotor3");
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);
        controller = new newPIDFController(P,I,0.0, 0.0); // PIDF, Feedforward (kV, kA, kS)
    }

    @Override
    public void loop() {
        telemetry.addData("TargetVel", targetVelocity);
        telemetry.addData("CurrentVel", velocity);
        controller.setPIDF(P,I, 0.0, 0);
        controller.setFeedforward(kV,0,kS);
        velocity = motor.getVelocity();
        motor.setPower(controller.calculate(targetVelocity - velocity,targetVelocity,0));
        motor2.setPower(controller.calculate(targetVelocity - velocity,targetVelocity,0));
    }
}