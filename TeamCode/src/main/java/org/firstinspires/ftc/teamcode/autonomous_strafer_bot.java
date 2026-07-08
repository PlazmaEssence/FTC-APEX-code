package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Robot: Auto Drive By Time", group="Robot")

    public class autonomous_strafer_bot extends LinearOpMode {
    private DcMotor bkLDrive = null;
    private DcMotor bkRDrive = null;
    private DcMotor ftLDrive = null;
    private DcMotor ftRDrive = null;

    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;

    @Override
    public void runOpMode() {

        ftLDrive = hardwareMap.get(DcMotor.class, "FLD");
        ftRDrive = hardwareMap.get(DcMotor.class, "FRD");
        bkRDrive = hardwareMap.get(DcMotor.class, "BRD");
        bkLDrive = hardwareMap.get(DcMotor.class, "BLD");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        bkLDrive.setDirection(DcMotor.Direction.FORWARD);
        bkRDrive.setDirection(DcMotor.Direction.REVERSE);
        ftLDrive.setDirection(DcMotor.Direction.FORWARD);
        ftRDrive.setDirection(DcMotor.Direction.REVERSE);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();

        // Step through each leg of the path, ensuring that the OpMode has not been stopped along the way.

        // Step 1:  Drive forward for 3 seconds
        bkLDrive.setPower(FORWARD_SPEED);
        bkRDrive.setPower(FORWARD_SPEED);
        ftRDrive.setPower(FORWARD_SPEED);
        ftLDrive.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        bkLDrive.setPower(0);
        bkRDrive.setPower(0);
        ftRDrive.setPower(0);
        ftLDrive.setPower(0);
    }
}