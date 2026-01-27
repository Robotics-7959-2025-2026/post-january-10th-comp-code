package org.firstinspires.ftc.teamcode.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

// Wow, Ayden is in the dictionary but not Adyn??
@Autonomous(name = "Adyn Blue", preselectTeleOp = "TeleOp")
public class ManualAutoBlue extends ManualAuto {
    @Override
    public Paths getPaths() {
        return Paths.blue(pedro);
    }

    @Override
    public Pose getStartPose() {
        return Paths.blueStartPose();
    }
}
