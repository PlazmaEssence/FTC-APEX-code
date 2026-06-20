package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "AmazingRoyalBlueWaffle")
public class AmazingRoyalBlueWaffleDuncans extends LinearOpMode {
    private DcMotor Intakemotor;
    private boolean toggle = false;
    private CRServo servoLeft;
    private CRServo servoRight;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private double IntakePower = 0;
    private double leftServoPower= 0;
    private double rightServoPower = 0;






    @Override
    public void runOpMode() {

        Intakemotor = hardwareMap.get(DcMotor.class, "Intakemotor");
        servoLeft= hardwareMap.get(CRServo.class, "servoLeft");
        servoRight= hardwareMap.get(CRServo.class, "servoRight");
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {
            double Power;
            if (gamepad1.bWasPressed()){
                toggle = !toggle;
            }

            if (gamepad1.y || toggle) {

                rightServoPower = 1;
                leftServoPower = 1;
                IntakePower = 1;
                Intakemotor.setPower(1);
                servoLeft.setPower(1);
                servoRight.setPower(1);
            } else {
                rightServoPower = 0;
                leftServoPower = 0;
                IntakePower = 0;
                Intakemotor.setPower(0);
                servoLeft.setPower(0);
                servoRight.setPower(0);
            }



            //telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Intakemotor", Power);
            //telemetry.addData("Status", "Initialized");
            //telemetry.update();

            // Initialize the hardware variables. Note that the strings used here as parameters
            // to 'get' must correspond to the names assigned during the robot configuration
            // step (using the FTC Robot Controller app on the phone).


            // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
            // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
            // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
            leftDrive.setDirection(DcMotor.Direction.REVERSE);
            rightDrive.setDirection(DcMotor.Direction.FORWARD);

            // Wait for the game to start (driver presses START)
            waitForStart();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {

                // Set up a variable for each drive wheel to save power level for telemetry
                double leftPower;
                double rightPower;

                double drive = -gamepad1.left_stick_y;
                double turn  =  gamepad1.right_stick_x;
                leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
                rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;



                // Send calculated power to wheels
                leftDrive.setPower(leftPower);
                rightDrive.setPower(rightPower);

                // Show the elapsed game time and wheel power.
                telemetry.addData("Moving thingies", "left (%.2f), right (%.2f) Intake (%.2f) leftServo (%.2f) rightServo (%.2f)", leftPower, rightPower , IntakePower , leftServoPower , rightServoPower);
                telemetry.update();
            }
        }
    }

}
