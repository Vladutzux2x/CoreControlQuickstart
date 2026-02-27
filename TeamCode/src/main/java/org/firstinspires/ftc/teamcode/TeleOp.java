package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon;
import com.qulcomm.robotcore.exception;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class TeleOp extends OpMode {

    DcMotor motor0;
    DcMotor motor1;

    Servo servo0;
    Servo servo1;
    
@Override
    public void runOpMode () {


        motor0 = hardwareMap.get(DcMotor.class, "motor0");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");

        servo0 = hardwareMap.get(Servo.class, "servo0");
        servo1 = hardwareMap.get(Servo.class, "servo1");

        servo0.setPosition(0.5);
        servo1.setPosition(1.0);

        servo0.setSpeed(1);
        servo1.setSpeed(0.5);

        motor0.setDirection(DcMotorSimple.Direction.REVERSE);
        motor1.setDirection(DcMotorSimple.Direction.FORWARD);

    }

@Override
    public void init_loop() {

    }


@Override
    public void loop() {   
    }


}
