

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;




@TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")
//@Disabled
public class BasicOpMode_teleop extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor bkLDrive = null;
    private DcMotor bkRDrive = null;
    private DcMotor ftLDrive = null;
    private DcMotor ftRDrive = null;
    private DcMotor intake = null;
    private Servo intakeS1 = null;
    private int count = 0;
    private Servo sorter1 = null;
    private Servo sorter2 = null;
    boolean toggle = false;
    boolean toggle2 = false;
    boolean toggle3 = false;

    private AnalogInput counter;

    private static final double MAX_VOLTS = 3.3;
    private static final double MAX_DISTANCE_MM = 1000.0;
    private DcMotor shooter1 = null;
    private DcMotor shooter2 = null;

    boolean sense = false;
    //@Override

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        intake = hardwareMap.get(DcMotor.class, "intake");
        ftLDrive = hardwareMap.get(DcMotor.class, "FLD");
        ftRDrive = hardwareMap.get(DcMotor.class, "FRD");
        bkRDrive = hardwareMap.get(DcMotor.class, "BRD");
        bkLDrive = hardwareMap.get(DcMotor.class, "BLD");
        shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
        shooter2 = hardwareMap.get(DcMotor.class, "shooter2");
        intakeS1 = hardwareMap.get(Servo.class, "InS1");
        sorter1 = hardwareMap.get(Servo.class, "sort1");
        sorter2 = hardwareMap.get(Servo.class, "sort2");


        bkLDrive.setDirection(DcMotor.Direction.FORWARD);
        bkRDrive.setDirection(DcMotor.Direction.REVERSE);
        ftLDrive.setDirection(DcMotor.Direction.FORWARD);
        ftRDrive.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        intakeS1.setDirection(Servo.Direction.REVERSE);

        shooter1.setDirection(DcMotor.Direction.FORWARD);
        shooter2.setDirection(DcMotor.Direction.REVERSE);
        sorter1.setDirection(Servo.Direction.FORWARD);
        sorter2.setDirection(Servo.Direction.REVERSE);
        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            // Set up a variable for each drive wheel to save power level for telemetr
            double bkLeftPower;
            double bkRightPower;
            double ftLeftPower;
            double ftRightPower;


            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;
            double volts = counter.getVoltage();
            ftLeftPower = Range.clip(drive - strafe - rotate, -0.9, 0.9);
            ftRightPower = Range.clip(drive + strafe + rotate, -0.9, 0.9);
            bkLeftPower = Range.clip(drive + strafe - rotate, -0.9, 0.9);
            bkRightPower = Range.clip(drive - strafe + rotate, -0.9, 0.9);

            // Tank Mode uses one stick to control each wheel.
            // - requires no math,but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y;
            // rightPower = -gamepad1.right_stick_y;

            // Send calculated power to wheels
            bkLDrive.setPower(bkLeftPower);
            bkRDrive.setPower(bkRightPower);
            ftLDrive.setPower(ftLeftPower);
            ftRDrive.setPower(ftRightPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "front left (%.2f)," +
                            " front right (%.2f), back left (%.2f), back right (%.2f)",
                    ftLeftPower, ftLeftPower, bkLeftPower, bkRightPower);
            telemetry.update();

            if (gamepad1.leftTriggerWasPressed()) {
                toggle2 = !toggle2;
            }
            if (toggle2) {
                shooter1.setPower(0.5);
            } else {
                shooter1.setPower(0);
            }

            if (gamepad1.aWasPressed()) {
                toggle = !toggle;
            }
            if (toggle) {
                intake.setPower(0.5);
            } else {
                intake.setPower(0);
            }

            if (gamepad1.rightTriggerWasPressed()) {
                toggle3 = !toggle3;
            }

            if (toggle3) {
                shooter2.setPower(0.5);
            } else {
                shooter2.setPower(0);
            }

            double distanceMM = (volts / MAX_VOLTS) * MAX_DISTANCE_MM;


            if (distanceMM < 250) {

                if (sense == true) {
                    count += 1;
                    sense = false;
                    //   servo.setPosition(1);
                }
                if (sense == false) ;

            } else {
                sense = true;

            }

            if (count == 4) {
                intakeS1.setPosition(1);
            } else {
                intakeS1.setPosition(0);
            }


        }
    }
}