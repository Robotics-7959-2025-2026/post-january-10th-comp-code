package org.firstinspires.ftc.teamcode.Autonomous;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Adyn Red", preselectTeleOp = "TeleOp")

public class ManualAutoRed extends ManualAuto {
    @Override
    public Paths getPaths() {
        return Paths.red(pedro);
    }

    @Override
    public Pose getStartPose() {
        return Paths.redStartPose();
    }
}
