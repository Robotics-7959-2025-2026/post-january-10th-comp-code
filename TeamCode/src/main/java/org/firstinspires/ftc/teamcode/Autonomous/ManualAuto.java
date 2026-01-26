package org.firstinspires.ftc.teamcode.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public abstract class ManualAuto extends OpMode {
    public Paths paths = null;
    public Follower pedro = null;

    public abstract Paths getPaths();

    @Override
    public void init() {
        pedro = Constants.createFollower(hardwareMap);
        paths = getPaths();
    }

    @Override
    public void loop() {

    }

}
