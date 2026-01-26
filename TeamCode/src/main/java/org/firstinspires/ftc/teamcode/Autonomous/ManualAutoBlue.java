package org.firstinspires.ftc.teamcode.Autonomous;

import com.pedropathing.follower.Follower;

public class ManualAutoBlue extends ManualAuto {
    @Override
    public Paths getPaths() {
        return Paths.blue(pedro);
    }
}
