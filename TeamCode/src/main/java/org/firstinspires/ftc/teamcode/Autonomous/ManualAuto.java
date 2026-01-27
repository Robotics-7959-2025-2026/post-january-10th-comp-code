package org.firstinspires.ftc.teamcode.Autonomous;

import com.arcrobotics.ftclib.util.Timing;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.Teleop.newPIDFController;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.concurrent.TimeUnit;

public abstract class ManualAuto extends LinearOpMode {
    public Paths paths = null;
    public Follower pedro = null;

    public DcMotorEx intake = null;
    public double intakeDesired = 0;
    public DcMotorEx shooter2 = null;
    public DcMotorEx shooter3 = null;
    // What are kD and kF, just wondering?
    public newPIDFController flywheelController =
            new newPIDFController(0.015, 0.0, 0.065067, 0.0);
    public double P = 0.015;

    // Velocity to use when shooting
    public double shooterHigh = 1240;
    // Velocity to use now
    public double shooterTarget = 0;
    public DcMotorEx transfer = null;
    public double transferHigh = 0.7;
    public double transferTarget = 0.0;

    VoltageSensor battery = null;
    double batteryVoltage = 0.0;

    public double nominalVoltage = 12.5;

    public abstract Paths getPaths();
    public abstract Pose getStartPose();

    @Override
    public void runOpMode() {
        pedro = Constants.createFollower(hardwareMap);
        paths = getPaths();
        pedro.setStartingPose(getStartPose());

        // Why? Can we change this pls
        intake = hardwareMap.get(DcMotorEx.class, "shooterMotor");
        shooter2 = hardwareMap.get(DcMotorEx.class, "shooterMotor2");
        shooter3 = hardwareMap.get(DcMotorEx.class, "shooterMotor3");
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");

        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter2.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter3.setDirection(DcMotorSimple.Direction.FORWARD);
        transfer.setDirection(DcMotorSimple.Direction.FORWARD);

        shooter2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // What do these mean?
        flywheelController.setFeedforward(
                0.00036, // kV
                0.0,    // kA (not needed for flywheel)
                0.065067     // kS
        );

        intake.setPower(0);
        shooter2.setPower(0);
        shooter3.setPower(0);
        transfer.setPower(0);

        battery = hardwareMap.voltageSensor.iterator().next();
        batteryVoltage = battery.getVoltage();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Started");
        telemetry.update();

        intakeDesired = 1.0;
        shooterTarget = shooterHigh;

        goTo(paths.goShootPre);

        // Shoot for 3s
        transferTarget = transferHigh;
        waitMillis(3000);

        transferTarget = 0.0;
        shooterTarget = 0.0;

        // Grab balls 4, 5, 6
        intakeDesired = 1.0;
        goTo(paths.goGrabOne);

        // Shoot
        intakeDesired = 0.0;
        shooterTarget = shooterHigh;
        goTo(paths.goShootOne);

        transferTarget = transferHigh;
        waitMillis(3000);

        transferTarget = 0.0;
        shooterTarget = 0.0;

        // Grab balls 7, 8, 9
        intakeDesired = 1.0;
        goTo(paths.goGrabTwo);

        // Shoot
        intakeDesired = 0.0;
        shooterTarget = shooterHigh;
        goTo(paths.goShootTwo);

        transferTarget = transferHigh;
        waitMillis(3000);

        transferTarget = 0.0;
        shooterTarget = 0.0;

        // Grab balls 10, 11, 12
        intakeDesired = 1.0;
        goTo(paths.goGrabThree);

        // Shoot
        intakeDesired = 0.0;
        shooterTarget = shooterHigh;
        goTo(paths.goShootThree);

        transferTarget = transferHigh;
        waitMillis(3000);

        transferTarget = 0.0;
        shooterTarget = 0.0;

        goTo(paths.Path8);
        waitMillis(200);

        telemetry.addData("Status", "Done");
    }

    public void waitMillis(long millis) {
        Timing.Timer timer = new Timing.Timer(millis, TimeUnit.MILLISECONDS);
        timer.start();
        while (timer.isTimerOn()) {
            update();
        }
    }

    public void goTo(PathChain loc) {
        pedro.followPath(loc);
        while (pedro.isBusy()) {
            update();
        }
    }

    public void update() {
        telemetry.update();
        batteryVoltage = battery.getVoltage();

        pedro.update();

        double kV = 0.00036;
        double I = 0;
        double kS = 0.065067;
        double cVel = shooter2.getVelocity();
        telemetry.addData("TargetVel", shooterTarget);
        telemetry.addData("CurrentVel", cVel);
        flywheelController.setPIDF(P, I, 0.0, 0.0);
        flywheelController.setFeedforward(kV, 0.0, kS);

        shooter2.setPower(flywheelController.calculate(shooterTarget - cVel, shooterTarget, 0.0));
        shooter3.setPower(flywheelController.calculate(shooterTarget - cVel, shooterTarget, 0.0));

        transfer.setPower(transferTarget);

        double corrected = intakeDesired * (nominalVoltage / Math.max(1.0, batteryVoltage));
        corrected = Math.max(-1.0, Math.min(1.0, corrected));
        intake.setPower(corrected);
    }
}
