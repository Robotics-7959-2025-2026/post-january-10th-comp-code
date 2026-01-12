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

@Autonomous(name = "Keir FTC 12 ball blue", preselectTeleOp = "TeleOp")
public class TwelveBallAutoBlue extends NextFTCOpMode {

    public TwelveBallAutoBlue() {
        addComponents(
                new SubsystemComponent(Intake.INSTANCE, Shooter.INSTANCE, Transfer.INSTANCE),
                new PedroComponent(Constants::createFollower)
        );
    }

    private Paths paths;

    @Override
    public void onInit() {
        paths = new Paths(PedroComponent.follower());
        PedroComponent.follower().setStartingPose(new Pose(21.712660028449505, 126.17923186344238, Math.toRadians(150)));
        Intake.turnoff.schedule();
        Transfer.turnoff.schedule();
    }

    // When i wrote this code, only me and god knew how it works.
    //Now only god knows!
    //Best of luck if youre trying to debug this
    //Hours wasted count: 8
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
                                    new Pose(22.942, 125.770),

                                    new Pose(60.632, 83.164)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(140), Math.toRadians(135))

                    .build();

            goGrabOne = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(60.632, 83.164),
                                    new Pose(52.855, 82.590),
                                    new Pose(28.677, 83.164)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                    .build();

            goShootOne = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(28.677, 83.164),
                                    new Pose(46.908, 83.164),
                                    new Pose(60.632, 82.549)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                    .build();

            goGrabTwo = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(60.632, 82.549),
                                    new Pose(65.548, 54.486),
                                    new Pose(26.014, 59.812)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                    .build();

            goShootTwo = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(26.014, 59.812),

                                    new Pose(61.246, 83.164)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                    .build();

            goGrabThree = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(61.246, 83.164),
                                    new Pose(52.438, 21.098),
                                    new Pose(60.836, 36.256),
                                    new Pose(27.038, 35.027)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                    .build();

            goShootThree = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(27.038, 35.027),

                                    new Pose(60.427, 83.778)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                    .build();
            Path8 = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(60.427, 83.778),

                                    new Pose(48.95874822190613, 71.51209103840681)
                            )
                    ).setConstantHeadingInterpolation(Math.toRadians(135))

                    .build();
        }
    }

}