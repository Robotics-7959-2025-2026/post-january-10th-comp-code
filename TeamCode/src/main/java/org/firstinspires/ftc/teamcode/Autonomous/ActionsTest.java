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

@Autonomous(name = "Actions Test", preselectTeleOp = "TeleOp")
public class ActionsTest extends NextFTCOpMode {

    public ActionsTest() {
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

    }

    @Override
    public void onStartButtonPressed() {
        new SequentialGroup(
                Intake.turnon,
                Transfer.turnon,
                Shooter.INSTANCE.togglePeriodicOn,
                Shooter.turnon,
                new Delay(5),
                Shooter.turnoff,
                Intake.turnoff,
                Transfer.turnoff
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

        public Paths(Follower follower) {
            goShootPre = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(22.942, 125.770),

                                    new Pose(70.873, 82.549)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(140), Math.toRadians(135))

                    .build();

            goGrabOne = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(70.873, 82.549),
                                    new Pose(59.000, 83.000),
                                    new Pose(26.834, 83.778)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                    .build();

            goShootOne = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(26.834, 83.778),
                                    new Pose(61.861, 73.536),
                                    new Pose(70.669, 82.139)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                    .build();

            goGrabTwo = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(70.669, 82.139),
                                    new Pose(82.754, 53.053),
                                    new Pose(29.292, 59.812)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                    .build();

            goShootTwo = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(29.292, 59.812),

                                    new Pose(71.283, 82.549)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                    .build();

            goGrabThree = follower.pathBuilder().addPath(
                            new BezierCurve(
                                    new Pose(71.283, 82.549),
                                    new Pose(68.211, 19.869),
                                    new Pose(67.186, 39.329),
                                    new Pose(29.087, 36.051)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))

                    .build();

            goShootThree = follower.pathBuilder().addPath(
                            new BezierLine(
                                    new Pose(29.087, 36.051),

                                    new Pose(71.283, 81.935)
                            )
                    ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))

                    .build();
        }
    }



}