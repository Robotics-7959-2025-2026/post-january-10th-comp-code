package org.firstinspires.ftc.teamcode.Teleop;

import static org.firstinspires.ftc.teamcode.Teleop.Motors.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.arcrobotics.ftclib.controller.PIDFController;


//We are so back
@TeleOp(name = "PIDFlywheelTuning")
public class PIDFlywheelTuning extends LinearOpMode {

    public double highVelocity = 1200;

    public double lowVelocity = 900;

    double curTargetVelocity = highVelocity;
    double F = 14;
    double P = 0.3;
    private double nominalVoltage = 12.5;
    private double desiredPower = 1;
    private double batteryVoltage, correctedPower;
    double[] stepSizes = {10, 1, 0.1, 0.01, 0.001};

    int stepIndex = 1;

    @Override
    public void runOpMode() {
        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        VoltageSensor battery = hardwareMap.voltageSensor.iterator().next();
        batteryVoltage = battery.getVoltage();

        Motors.init(this.hardwareMap);
        telemetry.addLine("init complete and ready for tuning");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            telemetry.update();
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            if(gamepad1.yWasPressed()){
                if(curTargetVelocity == highVelocity){
                    curTargetVelocity = lowVelocity;
                }else{
                    curTargetVelocity = highVelocity;
                }
            }

            if(gamepad1.bWasPressed()){
                stepIndex = (stepIndex + 1) % stepSizes.length;
            }

            if(gamepad1.dpadLeftWasPressed()){
                F-= stepSizes[stepIndex];
            }
            if(gamepad1.dpadRightWasPressed()){
                F+= stepSizes[stepIndex];
            }

            if(gamepad1.dpadUpWasPressed()){
                P+= stepSizes[stepIndex];
            }
            if(gamepad1.dpadDownWasPressed()){
                P-= stepSizes[stepIndex];
            }

            PIDFCoefficients pidfCoefficients2 = new PIDFCoefficients(P, 0, 0, F);
            shooterMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients2);
            shooterMotor3.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients2);

            double adjustedVelocity = adjustPowerForVoltage(curTargetVelocity, nominalVoltage, batteryVoltage);

            shooterMotor2.setVelocity(adjustedVelocity);
            shooterMotor3.setVelocity(adjustedVelocity);

            double curVelocity = shooterMotor3.getVelocity();
            double error = curTargetVelocity - curVelocity;

            if(gamepad1.left_trigger>0.2){
                transfer.setPower(1);
                correctedPower = desiredPower * (nominalVoltage / Math.max(batteryVoltage, 1.0));
                correctedPower = Math.max(-1.0, Math.min(correctedPower, 1.0));
                intakeMotor.setPower(correctedPower);
            }else{
                transfer.setPower(0);
                intakeMotor.setPower(0.0);
            }

            //p = 9.7, f = 1,
            telemetry.addData("Target velocity", curTargetVelocity);
            telemetry.addData("Current Velocity", "%.2f", curVelocity);
            telemetry.addData("Error", "%.2f", error);
            telemetry.addLine("-----------------------------");
            telemetry.addData("Tuning P", "%.4f (D pad U/D", P);
            telemetry.addData("Tuning F", "%.4f (D pad L/R", F);
            telemetry.addData("Step Sizes", "%.4f (D button)", stepSizes[stepIndex]);
            telemetry.update();

        }
    }

    public double adjustPowerForVoltage(double desiredPower, double nominalVoltage, double batteryVoltage) {
        double correctedPower = desiredPower * (nominalVoltage / Math.max(batteryVoltage, 1.0));
        return Math.max(-1.0, Math.min(correctedPower, 1.0));
    }
}