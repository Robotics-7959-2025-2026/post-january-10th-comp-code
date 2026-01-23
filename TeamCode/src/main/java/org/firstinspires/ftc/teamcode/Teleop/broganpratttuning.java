package org.firstinspires.ftc.teamcode.Teleop;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
public class broganpratttuning extends OpMode {
    public DcMotorEx flywheelMotor;
    public DcMotorEx flywheelMotor2;

    public double highVelocity = 1240;
    public double lowVelocity = 900;

    double curTargetVelocity = highVelocity;

    double F = 14.02;
    double P = 0.6;

    double[] stepSizes = {10.0, 1.0, 0.1, 0.01, 0.001,0.0001};

    int stepIndex = 1;

    @Override

    public void init() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class,"shooterMotor2");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        flywheelMotor2 = hardwareMap.get(DcMotorEx.class,"shooterMotor3");
        flywheelMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor2.setDirection(DcMotorSimple.Direction.REVERSE);


        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0,0,F);


        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        flywheelMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);


        telemetry.addLine("init complete");

    }

    @Override

    public void loop() {
        //get our gamepad commands then we will set target velocity then we will update telemetry

        if (gamepad1.yWasPressed()) {
            if (curTargetVelocity == highVelocity) {
                curTargetVelocity = lowVelocity;
            } else { curTargetVelocity = highVelocity; }

        }

        if (gamepad1.bWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;

        }

        if (gamepad1.dpadLeftWasPressed()) {
            F -= stepSizes[stepIndex];
        }
        if (gamepad1.dpadRightWasPressed()) {
            F += stepSizes[stepIndex];
        }

        if (gamepad1.dpadUpWasPressed()) {
            P += stepSizes[stepIndex];
        }
        if (gamepad1.dpadDownWasPressed()) {
            P -= stepSizes[stepIndex];
        }

        // new PIDF coefficient
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0,0,F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        flywheelMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        //set velocity
        flywheelMotor.setVelocity(curTargetVelocity);
        flywheelMotor2.setVelocity(curTargetVelocity);

        double curVelocity = flywheelMotor.getVelocity();
        double shooter2velocity = flywheelMotor2.getVelocity();
        double error = curTargetVelocity + curVelocity;


        telemetry.addData("Target velocity", curTargetVelocity);
        telemetry.addData("Current Velocity", "%.2f", curVelocity);
        telemetry.addData("Shooter 2 Velocity","%.2f",shooter2velocity);
        telemetry.addData("Error", "%.2f", error);
        telemetry.addLine("-----------------------------");
        telemetry.addData("Tuning P", "%.4f (D pad U/D", P);
        telemetry.addData("Tuning F", "%.4f (D pad L/R", F);
        telemetry.addData("Step Sizes", "%.4f (D button)", stepSizes[stepIndex]);
        telemetry.update();





    }
}
