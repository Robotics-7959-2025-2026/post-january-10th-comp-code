package org.firstinspires.ftc.teamcode.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class Paths {
    public PathChain goShootPre = null;
    public PathChain goGrabOne = null;
    public PathChain goShootOne = null;
    public PathChain goGrabTwo = null;
    public PathChain goShootTwo = null;
    public PathChain goGrabThree = null;
    public PathChain goShootThree = null;
    public PathChain Path8 = null;

    // GOD I wish we had Rust construction semantics
    public static Paths blue(Follower follower) {
        Paths paths = new Paths();
        paths.goShootPre = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(22.942, 125.770),

                                new Pose(60.632, 83.164)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(140), Math.toRadians(135))

                .build();

        paths.goGrabOne = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(60.632, 83.164),
                                new Pose(52.855, 82.590),
                                new Pose(28.677, 83.164)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                .build();

        paths.goShootOne = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(28.677, 83.164),
                                new Pose(46.908, 83.164),
                                new Pose(60.632, 82.549)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                .build();

        paths.goGrabTwo = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(60.632, 82.549),
                                new Pose(65.548, 54.486),
                                new Pose(26.014, 59.812)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                .build();

        paths.goShootTwo = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(26.014, 59.812),

                                new Pose(61.246, 83.164)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                .build();

        paths.goGrabThree = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(61.246, 83.164),
                                new Pose(52.438, 21.098),
                                new Pose(60.836, 36.256),
                                new Pose(27.038, 35.027)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                .build();

        paths.goShootThree = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(27.038, 35.027),

                                new Pose(60.427, 83.778)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                .build();
        paths.Path8 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(60.427, 83.778),

                                new Pose(48.95874822190613, 71.51209103840681)
                        )
                ).setConstantHeadingInterpolation(Math.toRadians(135))

                .build();
        return paths;
    }

    public static Paths red(Follower follower) {
        Paths paths = new Paths();
        paths.goShootPre = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(121.058, 125.770),

                                new Pose(83.368, 83.164)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(45))

                .build();

        paths.goGrabOne = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(83.368, 83.164),
                                new Pose(91.145, 82.590),
                                new Pose(115.323, 83.164)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(0))

                .build();

        paths.goShootOne = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(115.323, 83.164),
                                new Pose(97.092, 83.164),
                                new Pose(83.368, 82.549)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))

                .build();

        paths.goGrabTwo = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(83.368, 82.549),
                                new Pose(78.452, 54.486),
                                new Pose(117.986, 59.812)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(0))

                .build();

        paths.goShootTwo = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(117.986, 59.812),

                                new Pose(82.754, 83.164)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))

                .build();

        paths.goGrabThree = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(82.754, 83.164),
                                new Pose(91.562, 21.098),
                                new Pose(83.164, 36.256),
                                new Pose(116.962, 35.027)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(0))

                .build();

        paths.goShootThree = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(116.962, 35.027),

                                new Pose(83.573, 83.778)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))

                .build();
        paths.Path8 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(83.573, 83.778),

                                new Pose(95.252, 72.536)
                        )
                ).setConstantHeadingInterpolation(Math.toRadians(45))

                .build();
        return paths;
    }
}
