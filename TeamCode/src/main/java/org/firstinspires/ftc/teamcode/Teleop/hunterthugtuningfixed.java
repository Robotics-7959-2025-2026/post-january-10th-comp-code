package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "hunterthugtuningfixed")
public class hunterthugtuningfixed extends LinearOpMode {
    public DcMotorEx flywheelMotor;
    public DcMotorEx flywheelMotor2;

    public double highVelocity = 1500;
    public double lowVelocity = 900;

    double curTargetVelocity = highVelocity;

    double F = 0;
    double P = 0;

    double[] stepSizes = {10.0, 1.0, 0.1, 0.01, 0.001};

    int stepIndex = 1;


    @Override

    public void runOpMode() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "shooterMotor2");
        flywheelMotor2 = hardwareMap.get(DcMotorEx.class,"shooterMotor3");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        while (opModeIsActive()) {

        }
    }
}