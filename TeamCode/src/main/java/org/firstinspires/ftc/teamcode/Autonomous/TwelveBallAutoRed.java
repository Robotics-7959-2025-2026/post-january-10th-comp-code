package org.firstinspires.ftc.teamcode.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Autonomous.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Autonomous.Subsystems.Transfer;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;

@Autonomous(name = "Keir FTC 12 ball red", preselectTeleOp = "TeleOp")
public class TwelveBallAutoRed extends NextFTCOpMode {

    public TwelveBallAutoRed() {
        addComponents(
                new SubsystemComponent(Intake.INSTANCE, Shooter.INSTANCE, Transfer.INSTANCE),
                new PedroComponent(Constants::createFollower)
        );
    }

    private Paths paths;

    @Override
    public void onInit() {
        paths = new Paths(PedroComponent.follower());
        PedroComponent.follower().setStartingPose(new Pose(121.05832147937411, 125.76955903271693, Math.toRadians(40)));
    }

    @Override
    public void onStartButtonPressed() {
        new SequentialGroup(
                new ParallelGroup(
                        new FollowPath(paths.goShootPre),
                        Shooter.turnon,
                        Intake.turnon
                ),
                Transfer.turnon,
                new Delay(3),
                //balls have been shot, go pick up 4 5 and 6.
                Shooter.turnoff,
                Transfer.turnoff,
                Intake.turnon,
                new FollowPath(paths.goGrabOne),


                Intake.turnoff,
                Shooter.turnon,
                //4 5 and 6 are intaked.
                new FollowPath(paths.goShootOne),
                Intake.turnon,
                Transfer.turnon,
                new Delay(3),
                //balls have been shot, go pick up 7 8 and 9.
                Transfer.turnoff,
                Shooter.turnoff,
                Intake.turnon,
                new Delay(3),
                new ParallelGroup(
                        new FollowPath(paths.goGrabTwo)
                ),
                Shooter.turnon,
                new FollowPath(paths.goShootTwo),
                Transfer.turnon,
                new Delay(3),
                Transfer.turnoff,
                Shooter.turnoff,
                new Delay(0.2),
                Intake.turnon,
                // 10 11 12
                new ParallelGroup(
                        new FollowPath(paths.goGrabThree),
                        Shooter.turnon
                ),
                new FollowPath(paths.goShootThree),
                Transfer.turnon,
                new Delay(3),
                new FollowPath(paths.Path8),
                Transfer.turnoff,
                Shooter.turnoff,
                Intake.turnoff,
                new Delay(0.2)

        ).schedule();
    }

    @Override
    public void onUpdate() {
        PedroComponent.follower().update();
    }



    public static class Paths {
        public PathChain goShootPre;
        public PathChain goGrabOne;
        public PathChain goShootOne;
        public PathChain goGrabTwo;
        public PathChain goShootTwo;
        public PathChain goGrabThree;
        public PathChain goShootThree;
        public PathChain Path8;


        public Paths(Follower follower) {
            goShootPre = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(121.058, 125.770),

                                    new Pose(83.368, 83.164)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(45))

                    .build();

            goGrabOne = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(83.368, 83.164),
                                    new Pose(91.145, 82.590),
                                    new Pose(115.323, 83.164)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(0))

                    .build();

            goShootOne = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(115.323, 83.164),
                                    new Pose(97.092, 83.164),
                                    new Pose(83.368, 82.549)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))

                    .build();

            goGrabTwo = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(83.368, 82.549),
                                    new Pose(78.452, 54.486),
                                    new Pose(117.986, 59.812)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(0))

                    .build();

            goShootTwo = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(117.986, 59.812),

                                    new Pose(82.754, 83.164)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))

                    .build();

            goGrabThree = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(82.754, 83.164),
                                    new Pose(91.562, 21.098),
                                    new Pose(83.164, 36.256),
                                    new Pose(116.962, 35.027)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(0))

                    .build();

            goShootThree = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(116.962, 35.027),

                                    new Pose(83.573, 83.778)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))

                    .build();
            Path8 = follower.pathBuilder().addPath(
                    new BezierLine(
                            new Pose(83.573, 83.778),

                            new Pose(95.252, 72.536)
                    )
            ).setConstantHeadingInterpolation(Math.toRadians(45))

                    .build();

        }
    }

}