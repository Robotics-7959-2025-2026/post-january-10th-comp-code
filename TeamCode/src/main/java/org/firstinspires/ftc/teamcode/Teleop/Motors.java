package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Motors {

    public static DcMotorEx lfMotor, rfMotor, lbMotor, rbMotor, shooterMotor3, transfer, intakeMotor, shooterMotor2;
    public static Servo doorToucher;

    public static void init(HardwareMap hardwareMap) {
        lfMotor = hardwareMap.get(DcMotorEx.class, "front_left_drive");
        rfMotor = hardwareMap.get(DcMotorEx.class, "front_right_drive");
        lbMotor = hardwareMap.get(DcMotorEx.class, "back_left_drive");
        rbMotor = hardwareMap.get(DcMotorEx.class, "back_right_drive");;
        intakeMotor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
        shooterMotor2 = hardwareMap.get(DcMotorEx.class, "shooterMotor2");
        shooterMotor3 = hardwareMap.get(DcMotorEx.class, "shooterMotor3");
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");

        doorToucher = hardwareMap.get(Servo.class, "door");

        lfMotor.setDirection(DcMotor.Direction.FORWARD);
        rfMotor.setDirection(DcMotor.Direction.REVERSE);
        lbMotor.setDirection(DcMotor.Direction.FORWARD);
        rbMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterMotor2.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterMotor3.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public static void encoderPrep(){
        rbMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rfMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lfMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lbMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lfMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rfMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lbMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rbMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public static void runToPosition(){
        lfMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lbMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rfMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rbMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void resetEncoders(){
        lfMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lbMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rbMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rfMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public static void runEncoders(){
        rbMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rfMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lfMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lbMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    /*
        public static void getCurrentPos(String motor){
        switch(motor){
            case(motor.equals("Lf"))

        }
    }
     */

    public static void setTargetPosition(int pos){
        rbMotor.setTargetPosition(pos);
        rfMotor.setTargetPosition(pos);
        lfMotor.setTargetPosition(pos);
        lbMotor.setTargetPosition(pos);
    }
    public static void setTargetPositions(int LF, int RF, int LB, int RB){
        rbMotor.setTargetPosition(RB);
        rfMotor.setTargetPosition(RF);
        lfMotor.setTargetPosition(LF);
        lbMotor.setTargetPosition(LB);
    }
    public static void runWithoutEncoder(){
        lfMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lbMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rfMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rbMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}