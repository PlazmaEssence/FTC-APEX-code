package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

    @TeleOp(name = "starter_bot_code")
    public class starter_bot_code extends LinearOpMode {
        private DcMotor RightDrive;
        private boolean toggle = false;
        //private CRServo servo;
        //private boolan toggle2 = false;


        @Override
        public void runOpMode() {
            RightDrive = hardwareMap.get(DcMotor.class, "RightDrive");
            //servo= hardwareMap.get(CRServo.class, "servo");

            waitForStart();
            while (opModeIsActive()) {
                    RightDrive.setPower(gamepad1.left_stick_y);


                }
        }
    }




    //private CRServo rightServo = null;
//    private CRServo leftServo = null;