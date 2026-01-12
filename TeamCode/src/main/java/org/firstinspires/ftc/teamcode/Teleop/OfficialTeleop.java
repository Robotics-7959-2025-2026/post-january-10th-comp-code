package org.firstinspires.ftc.teamcode.Teleop;

import static org.firstinspires.ftc.teamcode.Teleop.Motors.*;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

//We are so back
@Configurable
@TeleOp(name = "7959 Teleop")
public class OfficialTeleop extends LinearOpMode {

    private double nominalVoltage = 12.5;
    private double desiredPower = 1;
    private double batteryVoltage, correctedPower;

    private double ctrlPow = 2.0;
    double error;
    double curVelocity;
    double curTargetVelocity = 1230;
    double farTargetVelocity = 2000;
    private static final double BASE_F = 14.5;
    double F = 14.5;
    double P = 0.5;
    private double rx;
    private boolean doorToggle = false;

    @Override
    public void runOpMode() {
        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        Motors.init(this.hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        VoltageSensor battery = hardwareMap.voltageSensor.iterator().next();
        batteryVoltage = battery.getVoltage();
        PIDFCoefficients pidfCoefficients1 = new PIDFCoefficients(P, 0, 0, F);
        shooterMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients1);
        shooterMotor3.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients1);
        waitForStart();
        while (opModeIsActive()) {
            telemetry.update();
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);
            batteryVoltage = battery.getVoltage();

            //boilerplate, dont touch
            rx = (gamepad1.right_stick_x / 4) * 3 ;

            rfMotor.setPower(Math.pow(gamepad1.left_stick_y + gamepad1.left_stick_x + rx, ctrlPow) * Math.signum(gamepad1.left_stick_y + gamepad1.left_stick_x + rx));
            lfMotor.setPower(Math.pow(gamepad1.left_stick_y - gamepad1.left_stick_x - rx, ctrlPow) * Math.signum(gamepad1.left_stick_y - gamepad1.left_stick_x - rx));
            rbMotor.setPower(Math.pow(gamepad1.left_stick_y - gamepad1.left_stick_x + rx, ctrlPow) * Math.signum(gamepad1.left_stick_y - gamepad1.left_stick_x + rx));
            lbMotor.setPower(Math.pow(gamepad1.left_stick_y + gamepad1.left_stick_x  - rx, ctrlPow) * Math.signum(gamepad1.left_stick_y + gamepad1.left_stick_x - rx));


            //hold left bumper to spin, then press the right bumper to shoot
            if (gamepad1.left_bumper) {
                correctedPower = desiredPower * (nominalVoltage / Math.max(batteryVoltage, 1.0));
                correctedPower = Math.max(-1.0, Math.min(correctedPower, 1.0));
                intakeMotor.setPower(correctedPower);
            } else {
                intakeMotor.setPower(0.0);
            }

            double scaledF = BASE_F * (12.6 / batteryVoltage);
            telemetry.addData("F", scaledF);
            telemetry.update();

            PIDFCoefficients pidfCoefficients2 = new PIDFCoefficients(P, 0, 0, scaledF);
            shooterMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients2);
            shooterMotor3.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients2);

            if(gamepad1.right_bumper){
                shooterMotor2.setVelocity(curTargetVelocity);
                shooterMotor3.setVelocity(curTargetVelocity);
            }else{
                shooterMotor2.setVelocity(0);
                shooterMotor3.setVelocity(0);
            }
            if(gamepad1.left_trigger>0.2){
                transfer.setPower(0.7);
            }else{
                transfer.setPower(0);
            }



            if (currentGamepad1.x && !previousGamepad1.x) {
                doorToggle = !doorToggle;
            }

            if(gamepad1.x){
                if (doorToggle) {
                    doorToucher.setPosition(0.3);
                }else{
                    doorToucher.setPosition(0);
                }
            }
            curVelocity = shooterMotor3.getVelocity();
            error = curTargetVelocity + curVelocity;

            telemetry.addData("Target velocity", curTargetVelocity);
            telemetry.addData("Current Velocity", "%.2f", curVelocity);
            telemetry.addData("Error", "%.2f", error);
            telemetry.update();

        }
    }
}