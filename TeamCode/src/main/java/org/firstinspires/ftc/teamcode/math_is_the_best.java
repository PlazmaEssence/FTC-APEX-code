package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Math_is_the_best")

public class math_is_the_best extends LinearOpMode {

    private CRServo servoLeft;
    private CRServo servoRight;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    private DcMotor intakeMotor;

    @Override
    public void runOpMode() {

        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        servoLeft = hardwareMap.get(CRServo.class, "sL");
        servoRight = hardwareMap.get(CRServo.class, "sR");
        leftDrive = hardwareMap.get(DcMotor.class, "bLD");
        rightDrive = hardwareMap.get(DcMotor.class, "bRD");


        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        servoLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        servoRight.setDirection(DcMotorSimple.Direction.FORWARD);


        waitForStart();
        while (opModeIsActive()) {

            if (gamepad2.xWasPressed()) {
                diveforward(538);
                turn(269);
                diveforward(269);

                intakeMotor.setPower(1);
                servoLeft.setPower(1);
                servoRight.setPower(1);

            } else {
                intakeMotor.setPower(0);
                servoLeft.setPower(0);
                servoRight.setPower(0);
            }

            double leftPower;
            double rightPower;


            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -0.5, 0.5);
            rightPower = Range.clip(drive - turn, -0.5, 0.5);


            // Send calculated power to wheels
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
        }
    }


    public void diveforward(double ticks) {
        int newTarget;
        double speed = 0.8;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newTarget = leftDrive.getCurrentPosition() + (int) (ticks);
            leftDrive.setTargetPosition(newTarget);
            newTarget = rightDrive.getCurrentPosition() + (int) (ticks);
            rightDrive.setTargetPosition(newTarget);

            leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            leftDrive.setPower(Math.abs(speed));
            rightDrive.setPower(Math.abs(speed));
        }
    }

    public void turn(double ticks) {
        int newTarget;
        double speed =0.5;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newTarget = leftDrive.getCurrentPosition() + (int) (ticks);
            leftDrive.setTargetPosition(newTarget);
            newTarget = rightDrive.getCurrentPosition() - (int) (ticks);
            rightDrive.setTargetPosition(newTarget);

            leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            leftDrive.setPower(Math.abs(speed));
            rightDrive.setPower(Math.abs(speed));
        }
    }
}

