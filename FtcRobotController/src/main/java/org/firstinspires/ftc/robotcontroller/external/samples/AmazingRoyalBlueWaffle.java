package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "AmazingRoyalBlueWaffle")
public class AmazingRoyalBlueWaffle extends LinearOpMode {
    private DcMotor motor;
    private boolean toggle = false;
    //private CRServo servo;
    //private boolean toggle2 = false;


    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "Intakemotor");
        //servo= hardwareMap.get(CRServo.class, "servo");

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.bWasPressed()){
                toggle = !toggle;
            }

            if (gamepad2.a || toggle) {
                motor.setPower(1);
            } else {
                motor.setPower(0);
            }

//            //if (gamepad1.yWasPressed()) {
//                toggle2 = !toggle2;
//
//            //if (toggle2) {
//                servo.setPower(1);
//            } else {
//                servo.setPower(0);

            }
            }
        }
   // }
//}
