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
    private CRServo servo;
    private boolean toggle2 = false;
    private CRServo servo2;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;






    @Override
    public void runOpMode() {
        Intakemotor = hardwareMap.get(DcMotor.class, "Intakemotor");
        servo= hardwareMap.get(CRServo.class, "servoLeft");
        servo2= hardwareMap.get(CRServo.class, "servoRight");
        waitForStart();
        while (opModeIsActive()) {
            double Power;
            if (gamepad2.bWasPressed()){
                toggle = !toggle;
            }

            if (gamepad2.a || toggle) {
                Power=1;
            //Intakemotor.setPower(1);
            } else {
                Power=0;
                //Intakemotor.setPower(0);
            }
            Intakemotor.setPower(Power);
            if (gamepad1.yWasPressed()) {
                toggle2 = !toggle2;

            if (toggle2) {
                servo.setPower(1);
                servo2.setPower(1);
            } else {
            servo.setPower(0);
                servo2.setPower(0);

                //telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.addData("Intakemotor", Power);
                    telemetry.addData("Status", "Initialized");
                    telemetry.update();

                    // Initialize the hardware variables. Note that the strings used here as parameters
                    // to 'get' must correspond to the names assigned during the robot configuration
                    // step (using the FTC Robot Controller app on the phone).
                    leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
                    rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

                    // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
                    // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
                    // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
                    leftDrive.setDirection(DcMotor.Direction.REVERSE);
                    rightDrive.setDirection(DcMotor.Direction.FORWARD);

                    // Wait for the game to start (driver presses START)
                    waitForStart();

                    // run until the end of the match (driver presses STOP)
                    while (opModeIsActive()) {

                        // Setup a variable for each drive wheel to save power level for telemetry
                        double leftPower;
                        double rightPower;

                        // Choose to drive using either Tank Mode, or POV Mode
                        // Comment out the method that's not used.  The default below is POV.

                        // POV Mode uses left stick to go forward, and right stick to turn.
                        // - This uses basic math to combine motions and is easier to drive straight.
                        double drive = -gamepad1.left_stick_y;
                        double turn  =  gamepad1.right_stick_x;
                        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
                        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

                        // Tank Mode uses one stick to control each wheel.
                        // - This requires no math, but it is hard to drive forward slowly and keep straight.
                        // leftPower  = -gamepad1.left_stick_y ;
                        // rightPower = -gamepad1.right_stick_y ;

                        // Send calculated power to wheels
                        leftDrive.setPower(leftPower);
                        rightDrive.setPower(rightPower);

                        // Show the elapsed game time and wheel power.
                        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
                        telemetry.update();
                    }
                }
            }

            }
            }
        }
