package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "test Starter bot code")
public class sebastian_starter_bot_code extends LinearOpMode {
    private DcMotor intakeMotor;
    private boolean toggleintakeforward = false;
    private boolean toggleintakereverse = false;
    private CRServo servoLeft;
    private CRServo servoRight;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private double intakePower = 0;
    private double leftServoPower= 0;
    private double rightServoPower = 0;

    private double driveMotorMaxPower = 1.0;






    @Override
    public void runOpMode() {

        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        servoLeft= hardwareMap.get(CRServo.class, "sL");
        servoRight= hardwareMap.get(CRServo.class, "sR");
        leftDrive  = hardwareMap.get(DcMotor.class, "bLD");
        rightDrive = hardwareMap.get(DcMotor.class, "bRD");
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.xWasPressed()){
                toggleintakereverse = !toggleintakereverse;
                toggleintakeforward = false;
            }

            if (gamepad1.bWasPressed()){
                toggleintakeforward = !toggleintakeforward ;
                toggleintakereverse = false;
            }

            double Power;

            if (gamepad1.a || toggleintakereverse) {
                intakePower = -1;
                rightServoPower = -1;
                leftServoPower = 1;
            }
            else if (gamepad1.y || toggleintakeforward){
                intakePower = 1;
                rightServoPower = 1;
                leftServoPower = -1;
            }
            else {
                intakePower = 0;
                rightServoPower = 0;
                leftServoPower = 0;
            }


intakeMotor.setPower(intakePower);
servoLeft.setPower(leftServoPower);
servoRight.setPower(rightServoPower);

            //telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Intake", Power);
            //telemetry.addData("Status", "Initialized");
            //telemetry.update();

            // Initialize the hardware variables. Note that the strings used here as parameters
            // to 'get' must correspond to the names assigned during the robot configuration
            // step (using the FTC Robot Controller app on the phone).


            // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
            // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
            // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips


                double leftPower;
                double rightPower;


                double drive = -gamepad1.left_stick_y;
                double turn  =  gamepad1.right_stick_x;
                leftPower    = Range.clip(drive + turn, -driveMotorMaxPower, driveMotorMaxPower) ;
                rightPower   = Range.clip(drive - turn, -driveMotorMaxPower, driveMotorMaxPower) ;


                // Send calculated power to wheels
                leftDrive.setPower(leftPower);
                rightDrive.setPower(rightPower);

                // Show the elapsed game time and wheel power.
                telemetry.addData("Moving thingies", "left (%.2f), right (%.2f), Intake (%.2f), leftServo (%.2f), rightServo (%.2f)", leftPower, rightPower , intakePower , leftServoPower , rightServoPower);
                telemetry.update();
            }
        }
    }

