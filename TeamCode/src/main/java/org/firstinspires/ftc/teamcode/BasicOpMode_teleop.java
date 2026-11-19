/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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


/*
 * This file contains a minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: teleop", group="Linear OpMode")
//@Disabled
public class BasicOpMode_teleop extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor bkLDrive = null;
    private DcMotor bkRDrive = null;
    private DcMotor ftLDrive = null;

    private AnalogInput count;
    private DcMotor ftRDrive = null;
    private DcMotor intake = null;
    private Servo intakeS = null;

    boolean toggle7 = false;
    double power = 1;
    int number = 0;
    private Servo sorterP = null;
    private Servo sorterN = null;
    boolean toggle = false;
    boolean toggle2 = false;
    boolean toggle3 = false;
    boolean toggle4 = false;

    private DcMotor shooterP = null;
    private DcMotor shooterN = null;

    boolean toggle5 = false;

    boolean toggle6 = false;
    boolean toggle8 = false;
    //@Override
    private static final double MAX_VOLTS = 3.3;
    private static final double MAX_DISTANCE_MM = 1000.0;


    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables.
        //
        //
        //Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        intake = hardwareMap.get(DcMotor.class, "intake");
        ftLDrive = hardwareMap.get(DcMotor.class, "fld");
        ftRDrive = hardwareMap.get(DcMotor.class, "frd");
        bkRDrive = hardwareMap.get(DcMotor.class, "brd");
        bkLDrive = hardwareMap.get(DcMotor.class, "bld");
        shooterP = hardwareMap.get(DcMotor.class, "pS");
        shooterN = hardwareMap.get(DcMotor.class, "nS");
        intakeS = hardwareMap.get(Servo.class, "InS1");
        sorterP = hardwareMap.get(Servo.class, "sorterP");
        sorterN = hardwareMap.get(Servo.class, "sorterN");
        count = hardwareMap.get(AnalogInput.class, "laserAnalogInput");

        bkLDrive.setDirection(DcMotor.Direction.FORWARD);
        bkRDrive.setDirection(DcMotor.Direction.FORWARD);
        ftLDrive.setDirection(DcMotor.Direction.REVERSE);
        ftRDrive.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);
        intakeS.setDirection(Servo.Direction.REVERSE);
        shooterP.setDirection(DcMotor.Direction.REVERSE);
        shooterN.setDirection(DcMotor.Direction.FORWARD);
        sorterP.setDirection(Servo.Direction.FORWARD);
        sorterN.setDirection(Servo.Direction.REVERSE);
        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            double volts = count.getVoltage();
            // Convert voltage to distance in millimeters (linear mapping)
            double distanceMM = (volts / MAX_VOLTS) * MAX_DISTANCE_MM;
            if (distanceMM < 250)
                telemetry.addData("count", "");


            if (distanceMM < 190) {

                if (toggle5 == true) {
                    number += 1;
                    toggle5 = false;
                    //   servo.setPosition(1);
                }
                if (toggle5 == false) ;

            } else {
                toggle5 = true;

            }

            if (number == 4) {

                intakeS.setPosition(0.2);
            }
            // todo if true cant be true again until it has been false.

            // Telemetry
            telemetry.addData("Voltage (V)", "%.3f", volts);
            telemetry.addData("Distance (mm)", "%.1f", distanceMM);
            telemetry.addData("Count", count);
            telemetry.update();

            // Set up a variable for each drive wheel to save power level for telemetr


            double bkLeftPower;
            double bkRightPower;
            double ftLeftPower;
            double ftRightPower;
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            ftLeftPower = Range.clip(drive + strafe + rotate, -0.9, 0.9);
            ftRightPower = Range.clip(drive - strafe - rotate, -0.9, 0.9);
            bkLeftPower = Range.clip(drive - strafe + rotate, -0.9, 0.9);
            bkRightPower = Range.clip(drive + strafe - rotate, -0.9, 0.9);

            if (gamepad1.rightTriggerWasPressed()) {
                toggle6 = !toggle6;
            }

            if (toggle6) {
                bkLeftPower = bkLeftPower / 2;
                bkRightPower = bkRightPower / 2;
                ftRightPower = ftRightPower / 2;
                ftLeftPower = ftLeftPower / 2;
            }


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


            if (gamepad2.dpadUpWasPressed()) {
                power += 0.05;
            }

            if (gamepad2.dpadDownWasPressed()) {
                power -= 0.05;
            }


            if (gamepad2.yWasPressed()) {
                power = 1;
            }


            if (gamepad2.leftBumperWasPressed()) {
                toggle2 = !toggle2;
            }
            if (toggle2) {
                shooterP.setPower(power);
            } else {
                shooterP.setPower(0);
            }


            if (gamepad2.left_trigger > 0) {
                sorterP.setPosition(0.2);
            } else {
                sorterP.setPosition(0.8);
            }


            if (gamepad2.aWasPressed()) {
                toggle = !toggle;
            }
            if (toggle) {
                intake.setPower(0.8);
            } else {
                intake.setPower(0);
            }

            if (gamepad2.rightBumperWasPressed()) {
                toggle3 = !toggle3;
            }

            if (toggle3) {
                shooterN.setPower(power);
            } else {
                shooterN.setPower(0);
            }

            if (gamepad2.right_trigger > 0) {
                sorterN.setPosition(0.2);
            } else {
                sorterN.setPosition(0.9);
            }

                if (gamepad2.leftStickButtonWasPressed()) {
                    toggle7 = !toggle7;
                }

                if (toggle7) {
                    intakeS.setPosition(0.8);
                }


                if (gamepad2.right_stick_button) {
                    toggle8 = !toggle8;
                }

                if (toggle8) {
                    intakeS.setPosition(0.5);


                }


                if (gamepad2.bWasPressed()) {
                    toggle6 = !toggle6;
                }

                if (toggle6) {
                    intakeS.setPosition(0.2);
                }


            }


        }
    }
